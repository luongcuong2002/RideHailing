const mongoose = require("mongoose"); // Erase if already required
const bcrypt = require("bcryptjs");
var userSchema = new mongoose.Schema(
  {
    userName: {
      type: String,
      required: true,
    },
    email: {
      type: String,
      required: false,
      unique: true,
    },
    phoneNumber: {
      type: String,
      required: true,
      unique: true,
    },
    password: {
      type: String,
      required: true,
    },
    role: {
      type: String,
      default: "user",
    },
    refreshToken: {
      type: String,
    },
    // travelMode: {
    //   type: String,
    //   enum: ["TAXI", "BIKE"],
    //   default: "BIKE",
    // },
    // startLatitude: {
    //   type: Number,
    // },
    // startLongitude: {
    //   type: Number,
    // },
    // endLatitude: {
    //   type: Number,
    // },
    // endLongitude: {
    //   type: Number,
    // },
    // notifications: [
    //   {
    //     title: {
    //       type: String,
    //       required: true,
    //     },
    //     shortContent: {
    //       type: String,
    //       required: true,
    //     },
    //     content: {
    //       type: String,
    //       required: true,
    //     },
    //     image: {
    //       type: String,
    //       default:
    //         "https://insieutoc.vn/wp-content/uploads/2021/03/cac-mau-logo-dep-nhat.jpg",
    //     },
    //     isRead: {
    //       type: Boolean,
    //       default: false,
    //     },
    //   },
    // ],
    address: [
      {
        addressType: {
          type: String,
          enum: ["Home", "Work", "Other"],
          default: "Home",
        },
        shortName: {
          type: String, 
        },
        fullName: {
          type: String,      
        },
        longitude: {
          type: Number,       
        },
        latitude: {
          type: Number,
        }
      },
    ],
  },
  {
    timestamps: true,
  }
);

userSchema.pre("save", async function (next) {
  if (!this.isModified("password")) {
    next();
  }
  const salt = await bcrypt.genSaltSync(10);
  this.password = await bcrypt.hash(this.password, salt);
  next();
});

userSchema.methods = {
  isCorrectPassword: async function (password) {
    return await bcrypt.compare(password, this.password);
  },
};
//Export the model
module.exports = mongoose.model("User", userSchema);
