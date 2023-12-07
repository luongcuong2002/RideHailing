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
    phoneNumber: driver.phoneNumber,
    driverName: driver.driverName,
    driverAvatar: driver.driverAvatar,
    licensePlate: driver.licensePlate,
    vehicleBrand: driver.vehicleBrand,
    travelMode: driver.travelMode,
  });
});

module.exports = {
  updateLocation,
  getCurrent,
};
