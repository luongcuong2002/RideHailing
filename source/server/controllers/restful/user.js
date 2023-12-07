const userModel = require("../../models/userModel");
const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const getCurrent = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const user = await userModel
    .findById(_id)
    .select("-refreshToken -password -role");
  if (user) {
    return res.status(200).json({
      phoneNumber: user.phoneNumber,
      userName: user.userName,
      email: user.email,
    });
  } else {
    res.status(400);
    throw new Error("error");
  }
});

const forgotPassword = asyncHandler(async (req, res) => {
  const { phoneNumber, newPassword } = req.body;
  const user = await userModel.findOne({ phoneNumber });
  user.password = newPassword;
  const check = await user.save();
  if (check) {
    return res.status(200).send("success.");
  } else {
    res.status(400);
    throw new Error("error");
  }
});

const addAddress = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const { addressType, shortName, fullName, longitude, latitude } = req.body;
  if (!addressType || !shortName || !fullName || !longitude || !latitude)
    throw new Error("Missing inputs.");
  const response = await userModel.findByIdAndUpdate(
    _id,
    {
      $push: { address: req.body },
    },
    { new: true }
  );
  return res.json({
    data: response ? true : false,
    // createdBlog: response ? response : "Cannot create new address.",
  });
});

const deleteAddress = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const { did } = req.params;
  const response = await userModel.findByIdAndUpdate(
    _id,
    {
      $pull: { address: { _id: did } },
    },
    { new: true }
  );
  return res.json({
    data: response ? true : false,
    // deleteBlog: response ? response : " Something went wrong. ",
  });
});

const getAddresses = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const response = await userModel.findByIdAndUpdate(_id);
  if (user) {
    return res.status(200).json({
      addresses: response.address
    });
  } else {
    res.status(400);
    throw new Error("error");
  }
});

module.exports = {
  getCurrent,
  forgotPassword,
  addAddress,
  deleteAddress,
  getAddresses,
};
