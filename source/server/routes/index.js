const userRouter = require("./user.routes");
const driverRouter = require("./driver.routes");
const authUserRouter = require("./authUser.routes");
const authDriverRouter = require("./authDriver.routes");
const bookingRouter = require("./booking.routes");


function route(app) {
  app.use("/user", userRouter);
  app.use("/driver", driverRouter);
  app.use("/booking", bookingRouter);
  app.use("/auth/user", authUserRouter);
  app.use("/auth/driver", authDriverRouter);
}

module.exports = route;
