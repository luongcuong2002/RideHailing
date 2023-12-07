const mongoose = require("mongoose"); // Erase if already required
const bcrypt = require("bcryptjs");
// Declare the Schema of the Mongo model
var driverSchema = new mongoose.Schema(
  {
    driverName: {
      type: String,
      default: "Test name",
    },
    // email: {
    //   type: String,
    //   required: false,
    //   unique: true,
    // },
    phoneNumber: {
      type: String,
      required: true,
      unique: true,
    },
    password: {
      type: String,
      required: true,
    },
    travelMode: {
      type: String,
      enum: ["TAXI", "BIKE"],
      default: "TAXI",
    },
    vehicleBrand: {
      type: String,
      default: "Hyundai",
    },
    role: {
      type: String,
      default: "driver",
    },
    driverAvatar: {
      type: String,
      default: null,
    },
    licensePlate: {
      type: String,
      default: "29V5-19850",
    },
    activeStatus: {
      type: Boolean,
      default: false,
    },
    currentAddress: {
      type: Number,
    },
    currentLongitude: {
      type: Number,
    },
    currentLatitude: {
      type: Number,
    },
    socketId: {
      type: String,
    },
    ratings: [
      {
        star: { type: Number },
        postedBy: { type: mongoose.Types.ObjectId, ref: "User" },
        comment: { type: String },
      },
    ],
    totalRating: {
      type: Number,
      default: 0,
    },
    refreshToken: {
      type: String,
    },
  },
  {
    timestamps: true,
  }
);

driverSchema.pre("save", async function (next) {
  if (!this.isModified("password")) {
    next();
  }
  const salt = await bcrypt.genSaltSync(10);
  this.password = await bcrypt.hash(this.password, salt);
  next();
});

driverSchema.methods = {
  isCorrectPassword: async function (password) {
    return await bcrypt.compare(password, this.password);
  },
};

//Export the model
module.exports = mongoose.model("Driver", driverSchema);
