var express = require("express");
var router = express.Router();

const { getBookingInfo, ratingDriver } = require("../controllers/restful/booking");
const { verifyAccessToken } = require("../middlewares/verifyToken");

router.post("/get-booking-info", verifyAccessToken, getBookingInfo);
router.post("/rating-driver", verifyAccessToken, ratingDriver);

module.exports = router;
