var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var logger = require("morgan");
var cors = require("cors");

const { errorsMiddleware } = require("./middlewares/errorsMiddleware");
const dbConnect = require("./config/database");

dbConnect();

var app = express();

// view engine setup
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "jade");

app.use(cors("*"));
app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, "public")));

const route = require("./routes/index");
route(app);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
  next(createError(404));
});

// error handler
app.use(errorsMiddleware);

const PORT = process.env.PORT;
const http = require("http");
const server = http
  .createServer(app)
  .listen(PORT, () => console.log(`Server running on port ${PORT}`));

const socketIO = require("socket.io");
// const driverModel = require("./models/driverModel");
const io = socketIO(server, {
  cors: {
    origin: "*",
  },
});

const booking = require("./controllers/sockets/booking");
const connectSocket = require("./controllers/sockets/connectSocket");

booking(io);
// connectSocket(io);

// io.on("connection", (socket) => {
//   console.log("New client connected: " + socket.id);

//   socket.emit("connected", socket.id);

//   // Xử lý khi tài xế đăng nhập thành công
//   // socket.on("driver-login", async (driverData) => {
//   //   // Lưu socket ID vào schema của tài khoản tài xế
//   //   try {
//   //     const { idDriver, socketId } = driverData;
//   //     await driverModel.findOneAndUpdate(
//   //       { _id: idDriver },
//   //       { socketId: socketId },
//   //       { new: true }
//   //     );
//   //     // console.log(respone);
//   //   } catch (err) {
//   //     console.error("Error saving socket ID:", err);
//   //   }
//   // });
// });

module.exports = app;