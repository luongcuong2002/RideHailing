const driverModel = require("../../models/driverModel");
const userModel = require("../../models/userModel");
const TripBookingRecordModel = require("../../models/TripBookingRecordModel");
const BillModel = require("../../models/billModel");

module.exports = function (io) {
  io.of("/booking").on("connection", (socket) => {
    // tìm tài xế
    socket.on("find-a-driver", async (data) => {
      try {
        const {
          phoneNumber,
          driverSocketIds,
          pickupAddress,
          destinationAddress,
          distanceInKilometers,
          durationInMinutes,
          minutesToDriverArrival,
          kilometersToDriverArrival,
          paymentMethod,
          noteForDriver,
          cost,
          travelMode,
        } = data;

        let userInfo = await userModel.findOne({ phoneNumber });

        if (!userInfo) {
          console.log("User not found");
          return;
        }

        let socketIds = driverSocketIds.map(function (element) {
          return element.socketId;
        });

        let tripBookingRecord = new TripBookingRecordModel({
          pickupAddress: JSON.parse(pickupAddress),
          destinationAddress: JSON.parse(destinationAddress),
          distanceInKilometers,
          durationInMinutes,
          minutesToDriverArrival,
          kilometersToDriverArrival,
          paymentMethod,
          noteForDriver,
          socketIdDriversReceived: socketIds,
          cost,
          userId: userInfo._id,
          travelMode,
          status: "PENDING",
        });

        await tripBookingRecord.save();

        console.log("Trip booking record created: " + tripBookingRecord);

        socketIds.map((e) => {
          io.of("/booking")
            .to(e)
            .emit("send-requesting-a-ride-to-drivers", {
              id: tripBookingRecord._id,
              pickupAddress,
              destinationAddress,
              distanceInKilometers,
              durationInMinutes,
              minutesToDriverArrival,
              kilometersToDriverArrival,
              paymentMethod,
              noteForDriver,
              cost,
              userInfo: {
                userName: userInfo.userName,
                phoneNumber: userInfo.phoneNumber,
              },
            });
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("driver-accept-request", async (data) => {
      try {
        const { id } = data;

        let driverAccepted = await driverModel.findOne({ socketId: socket.id });

        let tripBookingRecord = await TripBookingRecordModel.findOne({
          _id: id,
        });

        if (!tripBookingRecord || tripBookingRecord.status !== "PENDING") {
          socket.emit("notify-accept-request", {
            id: tripBookingRecord._id,
            success: false,
          });
          console.log("Trip booking record not found: " + id);
          return;
        }

        tripBookingRecord = await TripBookingRecordModel.findOneAndUpdate(
          { _id: id },
          {
            status: "DRIVER_ACCEPTED",
            driverId: driverAccepted._id,
          },
          { new: true }
        );

        let userRequested = await userModel.findOne({
          _id: tripBookingRecord.userId,
        });

        console.log(
          "Trip booking record updated DRIVER_ACCEPTED: " +
            tripBookingRecord._id
        );

        let driverAcceptResponse = {
          tripId: tripBookingRecord._id,
          driverInfo: {
            id: driverAccepted._id,
            driverName: driverAccepted.driverName,
            phoneNumber: driverAccepted.phoneNumber,
            driverAvatar: driverAccepted.driverAvatar,
            licensePlate: driverAccepted.licensePlate,
            vehicleBrand: driverAccepted.vehicleBrand,
            travelMode: driverAccepted.travelMode,
            currentLatitude: driverAccepted.currentLatitude,
            currentLongitude: driverAccepted.currentLongitude,
            totalRating: driverAccepted.totalRating,
          },
          pickupAddress: tripBookingRecord.pickupAddress.address,
          pickupLatitude: tripBookingRecord.pickupAddress.latitude,
          pickupLongitude: tripBookingRecord.pickupAddress.longitude,
          destinationAddress: tripBookingRecord.destinationAddress.address,
          destinationLatitude: tripBookingRecord.destinationAddress.latitude,
          destinationLongitude: tripBookingRecord.destinationAddress.longitude,
          minutesToDriverArrival: tripBookingRecord.minutesToDriverArrival,
        };

        console.log(driverAcceptResponse);

        io.of("/booking")
          .to(userRequested.socketId)
          .emit("notify-accept-request", driverAcceptResponse);

        socket.emit("notify-accept-request", {
          id: tripBookingRecord._id,
          success: true,
        });

        // notify to drivers
        tripBookingRecord.socketIdDriversReceived.map((e) => {
          if (e !== socket.id) {
            io.of("/booking").to(e).emit("notify-delete-trip-record", {
              id: tripBookingRecord._id,
            });
          }
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("driver-decline-request", async (data) => {});

    socket.on("driver-arrived-at-pickup", async (data) => {
      const { id } = data;

      let tripBookingRecord = await TripBookingRecordModel.findOne({
        _id: id,
      });

      if (
        !tripBookingRecord ||
        tripBookingRecord.status !== "DRIVER_ACCEPTED"
      ) {
        socket.emit("notify-arrived-at-pickup", {
          id: tripBookingRecord._id,
          success: false,
        });
        console.log("Trip booking record not found: " + id);
        return;
      }

      tripBookingRecord = await TripBookingRecordModel.findOneAndUpdate(
        { _id: id },
        {
          status: "ARRIVED_AT_PICKUP",
        },
        { new: true }
      );

      let userRequested = await userModel.findOne({
        _id: tripBookingRecord.userId,
      });

      socket.emit("notify-arrived-at-pickup", {
        id: tripBookingRecord._id,
        success: true,
      });

      // notify to the user
      io.of("/booking")
        .to(userRequested.socketId)
        .emit("notify-arrived-at-pickup", {
          id: tripBookingRecord._id,
          success: true,
        });
    });

    socket.on("driver-arrived-at-destination", async (data) => {
      const { id } = data;

      let tripBookingRecord = await TripBookingRecordModel.findOne({
        _id: id,
      });

      if (
        !tripBookingRecord ||
        tripBookingRecord.status !== "ARRIVED_AT_PICKUP"
      ) {
        socket.emit("notify-arrived-at-destination", {
          id: tripBookingRecord._id,
          success: false,
        });
        console.log("Trip booking record not found: " + id);
        return;
      }

      tripBookingRecord = await TripBookingRecordModel.findOneAndUpdate(
        { _id: id },
        {
          status: "ARRIVED_AT_DESTINATION",
        },
        { new: true }
      );

      socket.emit("notify-arrived-at-destination", {
        id: tripBookingRecord._id,
        success: true,
      });
    });

    socket.on("user-cancel-trip", async (data) => {
      const { id } = data;

      let deletedTrip = await TripBookingRecordModel.findOneAndDelete({
        _id: id,
      });

      if (deletedTrip) {
        let driver = await driverModel.findOne({
          _id: deletedTrip.driverId,
        });

        if (driver && driver.socketId) {
          io.of("/booking").to(driver.socketId).emit("notify-cancel-trip", {
            id: deletedTrip._id,
            success: true,
          });
        }

        socket.emit("notify-cancel-trip", {
          success: true,
        });
      } else {
        socket.emit("notify-cancel-trip", {
          success: false,
        });
      }
    });

    socket.on("driver-cancel-trip", async (data) => {
      const { id } = data;

      let deletedTrip = await TripBookingRecordModel.findOneAndDelete({
        _id: id,
      });

      if (deletedTrip) {
        let userRequested = await userModel.findOne({
          _id: deletedTrip.userId,
        });

        if (userRequested && userRequested.socketId) {
          io.of("/booking")
            .to(userRequested.socketId)
            .emit("notify-cancel-trip", {
              id: deletedTrip._id,
              success: true,
            });
        }

        socket.emit("notify-cancel-trip", {
          success: true,
        });
      } else {
        socket.emit("notify-cancel-trip", {
          success: false,
        });
      }
    });

    socket.on("trip-completed", async (data) => {
      try {
        const { id } = data;

        let tripBookingRecord = await TripBookingRecordModel.findOne({
          _id: id,
        });

        if (
          !tripBookingRecord ||
          tripBookingRecord.status !== "ARRIVED_AT_DESTINATION"
        ) {
          socket.emit("notify-trip-completed", {
            id: tripBookingRecord._id,
            success: false,
          });
          console.log("Trip booking record not found: " + id);
          return;
        }

        // create a bill
        let bill = new BillModel({
          pickupAddress: tripBookingRecord.pickupAddress,
          destinationAddress: tripBookingRecord.destinationAddress,
          distanceInKilometers: tripBookingRecord.distanceInKilometers,
          durationInMinutes: tripBookingRecord.durationInMinutes,
          paymentMethod: tripBookingRecord.paymentMethod,
          noteForDriver: tripBookingRecord.noteForDriver,
          cost: tripBookingRecord.cost,
          travelMode: tripBookingRecord.travelMode,
          userId: tripBookingRecord.userId,
          driverId: tripBookingRecord.driverId,
        });

        await bill.save();

        console.log("Bill created: " + bill);

        // delete trip booking record
        await TripBookingRecordModel.findOneAndDelete({
          _id: tripBookingRecord._id,
        });

        socket.emit("notify-trip-completed", {
          id: tripBookingRecord._id,
          success: true,
        });

        // notify to the user
        let userRequested = await userModel.findOne({
          _id: tripBookingRecord.userId,
        });

        io.of("/booking")
          .to(userRequested.socketId)
          .emit("notify-trip-completed", {
            id: tripBookingRecord._id,
            cost: tripBookingRecord.cost,
            success: true,
          });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("update-driver-location", async (data) => {
      try {
        const { currentLatitude, currentLongitude } = data;
        await driverModel.findOneAndUpdate(
          { socketId: socket.id },
          {
            currentLatitude,
            currentLongitude,
          }
        );

        console.log("Driver updated location: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("driver-connect-socket", async (data) => {
      try {
        const { phoneNumber, currentLatitude, currentLongitude } = data;
        await driverModel.findOneAndUpdate(
          { phoneNumber },
          {
            socketId: socket.id,
            currentLatitude,
            currentLongitude,
            activeStatus: true,
          },
          { new: true }
        );

        console.log("Driver connected socket: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("user-connect-socket", async (data) => {
      try {
        const { phoneNumber } = data;
        let user = await userModel.findOneAndUpdate(
          { phoneNumber },
          {
            socketId: socket.id,
          },
          { new: true }
        );

        if (!user) {
          console.log("User not found: " + phoneNumber);
          return;
        }

        console.log("User connected socket: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    socket.on("disconnect", async (data) => {
      try {
        let user = await userModel.findOne({ socketId: socket.id });
        if (user) {
          // delete trip booking records if exist
          let deletedTrip = await TripBookingRecordModel.findOneAndDelete({
            userId: user._id,
          });

          if (deletedTrip) {
            console.log("Trip booking records deleted: " + deletedTrip._id);

            // notify to drivers
            deletedTrip.socketIdDriversReceived.map((e) => {
              io.of("/booking").to(e).emit("notify-delete-trip-record", {
                id: deletedTrip._id,
              });
            });
          }
        }

        await userModel.findOneAndUpdate(
          { socketId: socket.id },
          {
            socketId: null,
          }
        );
        await driverModel.findOneAndUpdate(
          { socketId: socket.id },
          {
            socketId: null,
            activeStatus: false,
          }
        );

        console.log("Client disconnected: " + socket.id);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });
  });
};
