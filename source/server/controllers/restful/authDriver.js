const {
  generateAccessToken,
  generateRefreshToken,
} = require("../../middlewares/jwt");
const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const checkExistingDriver = asyncHandler(async (req, res) => {
  try {
    const { phoneNumber } = req.query;
    const check = await driverModel.findOne({ phoneNumber });
    return res.status(200).json({
      data: check ? true : false,
    });
  } catch (error) {
    res.status(400);
    throw new Error(error);
  }
});

const register = asyncHandler(async (req, res) => {
  const { password, phoneNumber } = req.body;
  if (!password || !phoneNumber)
    return res.status(400).json({
      success: false,
      mes: "Missing input",
    });
  const driver = await driverModel.findOne({ phoneNumber });
  if (driver) {
    res.status(400);
    throw new Error("Driver has existed!");
  } else {
    const newDriver = await driverModel.create(req.body);
    if (newDriver) {
      return res.status(200).send("Registered successfully.");
    } else {
      res.status(400);
      throw new Error("Registered failed.");
    }
  }
});

// refresh token => cap moi access token
//access token => xac thuc va phan quyen nguoi dung
const login = asyncHandler(async (req, res) => {
  const { phoneNumber, password } = req.body;
  if (!phoneNumber || !password) {
    res.status(400);
    throw new Error("Missing input!");
  }

  const response = await driverModel.findOne({ phoneNumber });

  if (response && (await response.isCorrectPassword(password))) {
    // tách pw và role ra khỏi response
    const { password, role, ...driver } = response.toObject();
    const accessToken = generateAccessToken(response._id, role);
    const refreshToken = generateRefreshToken(response._id);
    const driverData = await driverModel
      .findByIdAndUpdate(response._id, { refreshToken }, { new: true })
      .select("-password -role");
    res.cookie("refreshToken", refreshToken, {
      httpOnly: true,
      maxAge: 7 * 24 * 60 * 60 * 1000,
    });
    return res.status(200).json({
      accessToken,
      refreshToken,
      driverData,
    });
  } else {
    res.status(400);
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
  if (response) {
    return res.status(200).json({
      newAccessToken: generateAccessToken(response._id, response.role),
    });
  } else {
    res.status(400).send("error.");
  }
});

module.exports = {
  checkExistingDriver,
  register,
  login,
  refreshAccessToken,
};
