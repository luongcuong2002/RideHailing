const driverModel = require("../../models/driverModel");

module.exports = function (io) {
  io.of("/booking").on("connection", (socket) => {
    console.log("New client connected: " + socket.id);

    // tìm tài xế
    socket.on("findDriver", async (data) => {
      try {
        // console.log(data);
        const {
          fareAmount,
          drivers,
          startLatitude,
          startLongitude,
          endLatitude,
          endLongitude,
        } = data;
        //   console.log(listDriver);
        const listSocketId = await driverModel
          .find({ _id: drivers, activeStatus: true })
          .select("socketId -_id");
        listSocketId.map((e) => {
          // console.log(fareAmount, e.socketId);
          io.to(e.socketId).emit("receive-data", {
            fareAmount,
            startLatitude,
            startLongitude,
            endLatitude,
            endLongitude,
          });
        });
      } catch (err) {
        console.error("Error saving socket ID:", err);
      }
    });
  });
};
