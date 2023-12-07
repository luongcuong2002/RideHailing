// const driverModel = require("../../models/driverModel");
// const userModel = require("../../models/userModel");

// module.exports = function (io) {
//   io.of("/connect-driver").on("connection", (socket) => {
//     console.log("New client connected: " + socket.id);
//     // bật chế độ nhận khách
//     socket.on("receive-application", async (data) => {
//       try {
//         const { idDriver, currentLatitude, currentLongitude, socketId } = data;
//         const response = await driverModel.findOneAndUpdate(
//           { _id: idDriver },
//           {
//             socketId,
//             currentLatitude,
//             currentLongitude,
//             activeStatus: true,
//           },
//           { new: true }
//         );
//         // console.log(response);
//       } catch (err) {
//         console.error("Error saving socket ID:", err);
//       }
//     });
    
//     // Ngắt kết nối hoặc tắt chế độ nhận khách
//     socket.on("disconnect-driver", async (data) => {
//       try {
//         const { idDriver } = data;
//         await driverModel.findOneAndUpdate(
//           { _id: idDriver },
//           {
//             socketId: null,
//             currentLatitude: null,
//             currentLongitude: null,
//             activeStatus: false,
//           },
//           { new: true }
//         );
//         // console.log(respone);
//       } catch (err) {
//         console.error("Error saving socket ID:", err);
//       }
//     });
//   });

//   io.of("/connect-user").on("connection", (socket) => {
//     console.log("New client connected: " + socket.id);

//     socket.on("user-connect-socket", async (data) => {
//       try {
//         const { idUser, socketId } = data;
//         const response = await userModel.findOneAndUpdate(
//           { _id: idUser },
//           {
//             socketId,
//           },
//           { new: true }
//         );
//         // console.log(response);
//       } catch (err) {
//         console.error("Error saving socket ID:", err);
//       }
//     });

//     socket.on("disconnect-user", async (data) => {
//       try {
//         const { idUser } = data;
//         await userModel.findOneAndUpdate(
//           { _id: idDriver },
//           {
//             socketId: null,
//           },
//           { new: true }
//         );
//         // console.log(respone);
//       } catch (err) {
//         console.error("Error saving socket ID:", err);
//       }
//     });
//   });
// };
