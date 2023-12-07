const {
  generateAccessToken,
  generateRefreshToken,
} = require("../../middlewares/jwt");
const jwt = require("jsonwebtoken");

const userModel = require("../../models/userModel");
const asyncHandler = require("express-async-handler");

const checkExistingUser = asyncHandler(async (req, res) => {
  try {
    const { phoneNumber } = req.query;
    const check = await userModel.findOne({ phoneNumber });
    return res.status(200).json({
      data: check ? true : false,
    });
  } catch (error) {
    res.status(400);
    throw new Error(error);
  }
});

const register = asyncHandler(async (req, res) => {
  const { password, phoneNumber, userName } = req.body;
  if (!userName || !password || !phoneNumber) {
    return res.status(400).json({
      success: false,
      message: "Missing input",
    });
  }

  const user = await userModel.findOne({ phoneNumber });
  if (user) {
    return res.status(400).json({
      success: false,
      message: "User already exists",
    });
  } else {
    const newUser = await userModel.create(req.body);

    return res.status(200).json({
      success: newUser ? true : false,
      message: newUser
        ? "Registration successful. Please proceed to login."
        : "Something went wrong",
    });
  }
});

// refresh token => cap moi access token
//access token => xac thuc va phan quyen nguoi dung
const login = asyncHandler(async (req, res) => {
  const { phoneNumber, password } = req.body;
  if (!phoneNumber || !password) {
    return res.status(400).json({
      success: false,
      message: "Missing input",
    });
  }

  const user = await userModel.findOne({ phoneNumber });

  if (user && (await user.isCorrectPassword(password))) {
    const { password: _, role, ...userData } = user.toObject();
    const accessToken = generateAccessToken(user._id, role);
    const refreshToken = generateRefreshToken(user._id);

    await userModel.findByIdAndUpdate(
      user._id,
      { refreshToken },
      { new: true }
    );

    res.cookie("refreshToken", refreshToken, {
      httpOnly: true,
      maxAge: 7 * 24 * 60 * 60 * 1000,
    });

    return res.status(200).json({
      success: true,
      accessToken,
      refreshToken,
      userData: {
        phoneNumber: userData.phoneNumber,
        userName: userData.userName,
        email: userData.email,
      },
    });
  } else {
    return res.status(401).json({
      success: false,
    });
  }
});

const refreshAccessToken = asyncHandler(async (req, res) => {
  const { refreshToken } = req.cookies;

  if (!refreshToken) {
    return res.status(401).json({ error: "No refresh token in cookies" });
  }

  try {
    const decodedToken = await jwt.verify(refreshToken, process.env.JWT_SECRET);
    const response = await userModel.findOne({
      _id: decodedToken._id,
      refreshToken,
    });

    if (!response) {
      return res.status(401).json({ error: "Invalid refresh token" });
    }

    const newAccessToken = generateAccessToken(response._id, response.role);
    return res.status(200).json({ success: true, newAccessToken });
  } catch (error) {
    return res.status(401).json({ error: "Invalid refresh token" });
  }
});

module.exports = {
  checkExistingUser,
  register,
  login,
  refreshAccessToken,
};