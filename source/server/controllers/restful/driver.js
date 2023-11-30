const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const updateLocation = asyncHandler(async (req, res) => {
  // console.log(req.user);
  const { _id } = req.user;
  if (!req.body.currentLongitude || !req.body.currentLatitude)
    throw new Error("Missing inputs");
  const response = await driverModel
    .findByIdAndUpdate(_id, req.body, { new: true })
    .select("-password -role");
  return res.status(200).json({
    sucess: response ? true : false,
    updateUser: response ? response : "Some thing went wrong.",
  });
});

const getCurrent = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const driver = await driverModel
    .findById(_id)
    .select("-refreshToken -password -role");
  return res.status(200).json({
    succes: driver ? true : false,
    rs: driver ? driver : "Driver not found!",
  });
});

module.exports = {
  updateLocation,
  getCurrent,
};
