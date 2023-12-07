const mongoose = require("mongoose"); // Erase if already required

// Declare the Schema of the Mongo model
var billSchema = new mongoose.Schema(
  {
    pickupAddress: {
      address: {
        type: String,
        required: true,
      },
      latitude: {
        type: Number,
        required: true,
      },
      longitude: {
        type: Number,
        required: true,
      },
    },
    destinationAddress: {
      address: {
        type: String,
        required: true,
      },
      latitude: {
        type: Number,
        required: true,
      },
      longitude: {
        type: Number,
        required: true,
      },
    },
    distanceInKilometers: {
      type: Number,
      required: true,
    },
    durationInMinutes: {
      type: Number,
      required: true,
    },
    paymentMethod: {
      type: String,
      required: true,
    },
    noteForDriver: String,
    cost: {
      type: Number,
      required: true,
    },
    travelMode: {
      type: String,
      required: true,
    },
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "UserModel",
      required: true,
    },
    driverId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "DriverModel",
      default: null,
    },
  },
  {
    timestamps: true,
  }
);

//Export the model
module.exports = mongoose.model("Bill", billSchema);
