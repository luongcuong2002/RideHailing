var express = require("express");
var router = express.Router();

const { updateLocation, getCurrent } = require("../controllers/restful/driver");
const { verifyAccessToken } = require("../middlewares/verifyToken");

router.put("/update-location", verifyAccessToken, updateLocation);
router.get("/current", verifyAccessToken, getCurrent);

module.exports = router;
