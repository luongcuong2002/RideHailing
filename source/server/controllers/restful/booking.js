const driverModel = require("../../models/driverModel");
const asyncHandler = require("express-async-handler");

const getBookingInfo = asyncHandler(async (req, res) => {
  const {
    startLatitude,
    startLongitude,
    endLatitude,
    endLongitude,
    travelMode,
    distanceInKilometers,
  } = req.body;

  function degreesToRadians(degrees) {
    return degrees * (Math.PI / 180);
  }

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
    !distanceInKilometers
  )
    throw new Error("Missing inputs");

  const taxiPrices = 2;
  const bikePrices = 1;

  let fareAmount = 0;
  let minutesToDriverArrival = 5;
  if (travelMode === "TAXI") {
    fareAmount = parseInt(distanceInKilometers * taxiPrices);
  } else {
    fareAmount = parseInt(distanceInKilometers * bikePrices);
  }

  const fareCalculationInfo = `
    <body>
      <h1>Tính tiền chuyến xe:</h1>
      <p>1. Quãng đường: ${distanceInKilometers}km</p>
      <p>2. Loại phương tiện di chuyển: ${travelMode}</p>
      <p>3. Số tiền phải trả trên 1km của dịch vụ: ${
        travelMode === "TAXI" ? taxiPrices : bikePrices
      }.000 VNĐ</p>
    </body>
  `;

  // tìm 10 tài xế gần nhất trong phạm vi 2km
  const taxiMinutes = 5;
  const bikeMinutes = 7;

  let drivers = await driverModel
    .find({ activeStatus: true, travelMode: travelMode })
    .select("_id currentLatitude currentLongitude socketId ");

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

  drivers = drivers.sort((a, b) => a.distance - b.distance);
  drivers = drivers.slice(0, 5);

  const response = {
    fareAmount: parseInt(fareAmount),
    fareCalculationInfo: fareCalculationInfo,
    minutesToDriverArrival: parseInt(minutesToDriverArrival),
    kilometersToDriverArrival: 0.4,
    driversNearbyLocation: drivers.map((driver) => {
      return {
        latitude: driver.currentLatitude,
        longitude: driver.currentLongitude,
        socketId: driver.socketId,
      };
    }),
  };

  console.log(response);

  return res.status(200).json(response);
});

const ratingDriver = asyncHandler(async (req, res) => {
  try {
    const { _id } = req.user;

    const { driverId, star } = req.body;

    let driver = await driverModel.findById(driverId).select("ratings");

    let newRatings = [
      ...driver.ratings,
      {
        star: star,
        postedBy: _id,
        comment: "empty",
      },
    ];
    // let newTotalRating =
    //   driver.ratings.reduce((a, b) => a + b.star, 0) / driver.ratings.length;
    await driverModel.findByIdAndUpdate(driverId, {
      totalRating: 4.9,
      ratings: newRatings,
    });

    console.log("Rating driver successfully");

    return res.status(200).send("Rating driver successfully");
  } catch (error) {
    console.log("Rating driver failed with error: ", error);
    return res.status(400).send(error.message);
  }
});

module.exports = {
  getBookingInfo,
  ratingDriver,
};
