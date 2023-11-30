const express = require("express");
const router = express.Router();

const authUserController = require("../controllers/restful/authUser.js");

router.get("/check-existing-user", authUserController.checkExistingUser);
router.post("/login", authUserController.login);
router.post("/register", authUserController.register);
router.post("/refresh-token", authUserController.refreshAccessToken);

module.exports = router;
