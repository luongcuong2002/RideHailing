const {
  generateAccessToken,
  generateRefreshToken,
} = require("../../middlewares/jwt");
const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const checkExistingDriver = asyncHandler(async (req, res) => {
  const { phoneNumber } = req.query;
  const check = await driverModel.findOne({ phoneNumber });
  return res.status(200).json({
    data: check ? true : false,
  });
});

const register = asyncHandler(async (req, res) => {
  const { password, phoneNumber, driverName } = req.body;
  if (!driverName || !password || !phoneNumber)
    return res.status(400).json({
      sucess: false,
      mes: "Missing input",
    });
  const driver = await driverModel.findOne({ phoneNumber });
  if (driver) throw new Error("Driver has existed!");
  else {
    const newDriver = await driverModel.create(req.body);
    return res.status(200).json({
      sucess: newDriver ? true : false,
      mes: newDriver
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

  const response = await driverModel.findOne({ phoneNumber });

  if (response && (await response.isCorrectPassword(password))) {
    // tách pw và role ra khỏi response
    const { password, role, ...driverData } = response.toObject();
    const accessToken = generateAccessToken(response._id, role);
    const refreshToken = generateRefreshToken(response._id);
    const driver = await driverModel
      .findByIdAndUpdate(response._id, { refreshToken }, { new: true })
      .select("-password -role");
    res.cookie("refreshToken", refreshToken, {
      httpOnly: true,
      maxAge: 7 * 24 * 60 * 60 * 1000,
    });
    return res.status(200).json({
      sucess: true,
      accessToken,
      driver,
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

  const response = await driverModel.findOne({
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
  checkExistingDriver,
  register,
  login,
  refreshAccessToken,
};
