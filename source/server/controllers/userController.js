const userModel = require("../models/userModel");
const asyncHandler = require("express-async-handler");

const getCurrent = asyncHandler(async (req, res) => {
  const { _id } = req.user;

  const user = await userModel
    .findById(_id)
    .select("-refreshToken -password -role");

  if (!user) {
    return res.status(404).json({
      success: false,
      message: "User not found",
    });
  }

  return res.status(200).json({
    phoneNumber: user.phoneNumber,
    userName: user.userName,
    email: user.email,
  });
});

const forgotPassword = asyncHandler(async (req, res) => {
  const { phoneNumber, newPassword } = req.body;
  const user = await userModel.findOne({ phoneNumber });

  if (!user) {
    return res.status(404).json({
      success: false,
      message: "User not found",
    });
  }

  user.password = newPassword;
  const updatedUser = await user.save();

  return res.status(200).json({
    success: true,
    message: "Changed password successfully!",
    user: updatedUser,
  });
});

module.exports = {
  getCurrent,
  forgotPassword,
};
