const {
  generateAccessToken,
  generateRefreshToken,
} = require("../../middlewares/jwt");
const jwt = require("jsonwebtoken");

const userModel = require("../../models/userModel");
const asyncHandler = require("express-async-handler");

const checkExistingUser = asyncHandler(async (req, res) => {
  const { phoneNumber } = req.query;
  const check = await userModel.findOne({ phoneNumber });
  return res.status(200).json({
    data: check ? true : false,
  });
});

const register = asyncHandler(async (req, res) => {
  const { password, phoneNumber, userName } = req.body;
  if (!userName || !password || !phoneNumber)
    return res.status(400).json({
      sucess: false,
      mes: "Missing input",
    });
  const user = await userModel.findOne({ phoneNumber });
  if (user) throw new Error("User has existed!");
  else {
    const newUser = await userModel.create(req.body);
    return res.status(200).json({
      sucess: newUser ? true : false,
      mes: newUser
        ? "Register is sucessfully. Please go login."
        : "Something went wrong",
    });
  }
});

// refresh token => cap moi access token
//access token => xac thuc va phan quyen nguoi dung
const login = asyncHandler(async (req, res) => {
  const { phoneNumber, password } = req.body;
  if (!phoneNumber || !password)
    return res.status(400).json({
      sucess: false,
      mes: "Missing input",
    });

  const response = await userModel.findOne({ phoneNumber });

  if (response && (await response.isCorrectPassword(password))) {
    // tách pw và role ra khỏi response
    const { password, role, ...userData } = response.toObject();
    // tạo access token
    const accessToken = generateAccessToken(response._id, role);
    // tạo refresh token
    const refreshToken = generateRefreshToken(response._id);
    //Lưu refresh token vào database
    const user = await userModel
      .findByIdAndUpdate(response._id, { refreshToken }, { new: true })
      .select("-password -role");
    // Lưu refresh token vào cookie
    res.cookie("refreshToken", refreshToken, {
      httpOnly: true,
      maxAge: 7 * 24 * 60 * 60 * 1000,
    });
    return res.status(200).json({
      sucess: true,
      accessToken,
      user,
    });
  } else {
    throw new Error("Invalid credenttials!");
  }
});

const refreshAccessToken = asyncHandler(async (req, res) => {
  const cookie = req.cookies;
  if (!cookie && !cookie.refreshToken)
    throw new Error("No refresh token in cookies");
  const rs = await jwt.verify(cookie.refreshToken, process.env.JWT_SECRET);

  const response = await userModel.findOne({
    _id: rs._id,
    refreshToken: cookie.refreshToken,
  });
  return res.status(200).json({
    succes: response ? true : false,
    newAccessToken: response
      ? generateAccessToken(response._id, response.role)
      : "Refresh token matched",
  });
});

module.exports = {
  checkExistingUser,
  register,
  login,
  refreshAccessToken,
};