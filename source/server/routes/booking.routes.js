var express = require("express");
var router = express.Router();

const { getBookingInfo } = require("../controllers/restful/booking");
const { verifyAccessToken } = require("../middlewares/verifyToken");

router.put("/get-booking-info", verifyAccessToken, getBookingInfo);

module.exports = router;
