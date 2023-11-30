const userModel = require("../../models/userModel");
const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const getBookingInfo = asyncHandler(async (req, res) => {
  const { _id } = req.user;
  const {
    startLatitude,
    startLongitude,
    endLatitude,
    endLongitude,
    travelMode,
    tripLength,
  } = req.body;

  function degreesToRadians(degrees) {
    return degrees * (Math.PI / 180);
  }

  // hàm tính khoảng cách theo tọa độ
  function getDistanceInKm(lat1, lon1, lat2, lon2) {
    const earthRadiusKm = 6371;

    const dLat = degreesToRadians(lat2 - lat1);
    const dLon = degreesToRadians(lon2 - lon1);

    const a =
      Math.sin(dLat / 2) * Math.sin(dLat / 2) +
      Math.cos(degreesToRadians(lat1)) *
        Math.cos(degreesToRadians(lat2)) *
        Math.sin(dLon / 2) *
        Math.sin(dLon / 2);

    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    const distance = earthRadiusKm * c;
    return distance;
  }

  if (
    !startLatitude ||
    !startLongitude ||
    !endLatitude ||
    !endLongitude ||
    !travelMode ||
    !tripLength
  )
    throw new Error("Missing inputs");
  // const response = await userModel
  //   .findByIdAndUpdate(_id, req.body, { new: true })
  //   .select("-password -role");

  // tính chi phí
  const taxiPrices = 2;
  const bikePrices = 1;

  let fareAmount = 0;
  let minutesToDriverArrival = 0;
  if (travelMode === "TAXI") {
    fareAmount = tripLength * taxiPrices;
    // minutesToDriverArrival = tripLength * taxiMinutes;
  } else {
    fareAmount = tripLength * bikePrices;
  }

  // đoạn html trả về từ server
  const fareCalculationInfo = `
    <body>
      <h1>Cách tính tiền chuyến xe:</h1>
      <p>1. Quãng đường: ${tripLength}km</p>
      <p>2. Loại phương tiện di chuyển: ${travelMode}</p>
      <p>3. Số tiền phải trả trên 1km của dịch vụ: ${
        travelMode === "TAXI" ? taxiPrices : bikePrices
      }$</p>
      <p>2. Sô tiền khách hàng phải trả: ${
        travelMode === "TAXI" ? taxiPrices : bikePrices
      }$ * ${tripLength}km = ${fareAmount}$</p>
    </body>
  `;

  // tìm 10 tài xế gần nhất trong phạm vi 2km
  const taxiMinutes = 5;
  const bikeMinutes = 7;

  let drivers = await driverModel
    .find({ activeStatus: true, travelMode })
    .select("_id currentLatitude currentLongitude ");
  // console.log(a);
  drivers = drivers.map((e) => {
    const distance = getDistanceInKm(
      startLatitude,
      startLongitude,
      e.currentLatitude,
      e.currentLongitude
    ).toFixed(2);
    if (travelMode === "TAXI") {
      minutesToDriverArrival = distance * taxiMinutes;
    } else {
      minutesToDriverArrival = distance * bikeMinutes;
    }
    if (distance < 2) return { ...e._doc, distance, minutesToDriverArrival };
  });
  // console.log(driver);

  drivers = drivers.sort((a, b) => a.distance - b.distance);
  drivers = drivers.slice(0, 5);
  // console.log(drivers);
  // const driverNear = await driverModel
  //   .find({ _id: drivers })
  //   .select("currentLatitude currentLongitude");
  return res.status(200).json({
    // sucess: driverNear ? true : false,
    updateUser: drivers
      ? { drivers, fareAmount, fareCalculationInfo }
      : "Error.",
  });
});

module.exports = {
  getBookingInfo,
};
