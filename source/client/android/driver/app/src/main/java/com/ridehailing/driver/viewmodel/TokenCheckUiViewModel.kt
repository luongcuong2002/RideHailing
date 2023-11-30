package com.ridehailing.driver.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.globalstate.CurrentDriver
import com.ridehailing.driver.models.Driver
import com.ridehailing.driver.models.api.FetchDriverResponse
import com.ridehailing.driver.network.retrofit.repository.DriverRepository
import com.ridehailing.driver.screens.login.LoginActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TokenCheckUiViewModel : ViewModel() {

    private var driverRepository: DriverRepository = DriverRepository()

    fun fetchUser(context: Context) {
        driverRepository.fetchDriver(
            object : Callback<FetchDriverResponse> {

                override fun onResponse(
                    call: Call<FetchDriverResponse>,
                    response: Response<FetchDriverResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val driver = Driver()
                                .apply {
                                    setPhoneNumber(it.phoneNumber)
                                    setDriverName(it.driverName)
                                    setDriverAvatar(it.driverAvatar)
                                    setLicensePlate(it.licensePlate)
                                    setVehicleBrand(it.vehicleBrand)
                                    setVehicleType(it.vehicleType)
                                }
                            CurrentDriver.setDriver(driver)
                        }
                        navigateToHomeActivity()
                    } else {
                        navigateToLoginActivity(context)
                    }
                }

                override fun onFailure(call: Call<FetchDriverResponse>, t: Throwable) {
                    navigateToLoginActivity(context)
                }
            }
        )
    }

    private fun navigateToHomeActivity() {

    }

    private fun navigateToLoginActivity(context: Context) {
        val intent = Intent(context, LoginActivity::class.java)
        context.startActivity(intent)
    }
}