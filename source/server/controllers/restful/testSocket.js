const userModel = require("../../models/userModel");
const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const test = asyncHandler(async (req, res) => {
  //
  // 1.set socketID
  //   const { idDriver, socketId } = req.body;
  //   const response = await driverModel.findOneAndUpdate(
  //      {_id:idDriver} ,
  //     { socketId: null },
  //     { new: true }
  //   );
  //   console.log(idDriver);
  //   return res.status(200).json({
  //     sucess: response ? true : false,
  //     updateUser: response ? response : "error.",
  //   });
  //
  //2.find driver
  //   const { fareAmount, listDriver } = req.body;
  //   //   console.log(listDriver);
  //   const listSocketId = await driverModel
  //     .find({ _id: listDriver })
  //     .select("socketId -_id");
  // listSocketId.map(e => {
  //     console.log(fareAmount, e.socketId);
  // })
  //   return res.status(200).json({
  //     // sucess: response ? true : false,
  //     // updateUser: response ? response : "error.",
  //   });
});

module.exports = {
  test,
};
