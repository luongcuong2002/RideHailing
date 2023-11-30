const driverModel = require("../../models/driverModel");

module.exports = function (io) {
  io.of("/connect").on("connection", (socket) => {
    console.log("New client connected: " + socket.id);
    // bật chế độ nhận khách
    socket.on("receive-application", async (data) => {
      try {
        const { idDriver, currentLatitude, currentLongitude, socketId } = data;
        const response = await driverModel.findOneAndUpdate(
          { _id: idDriver },
          {
            socketId,
            currentLatitude,
            currentLongitude,
            activeStatus: true,
          },
          { new: true }
        );
        console.log(response);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });

    // Ngắt kết nối hoặc tắt chế độ nhận khách
    socket.on("disconnected", async (data) => {
      try {
        const { idDriver } = data;
        await driverModel.findOneAndUpdate(
          { _id: idDriver },
          {
            socketId: null,
            currentLatitude: null,
            currentLongitude: null,
            activeStatus: false,
          },
          { new: true }
        );
        // console.log(respone);
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });
  });
};
