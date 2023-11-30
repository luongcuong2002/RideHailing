var express = require("express");
var router = express.Router();

const {
  forgotPassword,
  getCurrent,
  addAddress,
  deleteAddress,
  getAddresses,
} = require("../controllers/restful/user");
const { verifyAccessToken } = require("../middlewares/verifyToken");

router.get("/current", verifyAccessToken, getCurrent);
router.get("/get-addresses", verifyAccessToken, getAddresses);
router.post("/change-password", forgotPassword);
router.put("/add-address", verifyAccessToken, addAddress);
router.put("/delete-address/:did", verifyAccessToken, deleteAddress);

module.exports = router;
