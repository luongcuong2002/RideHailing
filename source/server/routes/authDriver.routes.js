const express = require("express");
const router = express.Router();

const authDriverController = require("../controllers/restful/authDriver.js");

router.get("/check-existing-driver", authDriverController.checkExistingDriver);
router.post("/login", authDriverController.login);
router.post("/register", authDriverController.register);
router.post("/refresh-token", authDriverController.refreshAccessToken);

module.exports = router;
