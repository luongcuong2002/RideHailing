const mongoose = require("mongoose"); // Erase if already required

// Declare the Schema of the Mongo model
var TripBookingRecordModel = new mongoose.Schema(
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
    minutesToDriverArrival: {
      type: Number,
      required: true,
    },
    kilometersToDriverArrival: {
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
    userId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "UserModel",
      required: true,
    },
    socketIdDriversReceived: {
      type: [String],
      default: [],
      required: true,
    },
    driverId: {
      type: mongoose.Schema.Types.ObjectId,
      ref: "DriverModel",
      default: null,
    },
    travelMode: {
      type: String,
      required: true,
    },
    status: {
      type: String,
      required: true,
      enum: ["PENDING", "DRIVER_ACCEPTED", "ARRIVED_AT_PICKUP", "ARRIVED_AT_DESTINATION"],
    },
  },
  {
    timestamps: true,
  }
);

//Export the model
module.exports = mongoose.model(
  "TripBookingRecordModel",
  TripBookingRecordModel
);