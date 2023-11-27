package com.ridehailing.driver.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.ridehailing.driver.globalstate.CurrentDriver
import com.ridehailing.driver.models.Driver
import com.ridehailing.driver.models.api.FetchDriverResponse
import com.ridehailing.driver.models.api.LoginRequest
import com.ridehailing.driver.models.api.LoginResponse
import com.ridehailing.driver.network.retrofit.repository.AuthRepository
import com.ridehailing.driver.utils.LocalStorageUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginUiViewModel : ViewModel() {

    private val authRepository = AuthRepository()

    val phoneNumberTextField = mutableStateOf(
        TextFieldValue("")
    )

    fun setPhoneNumberTextField(textFieldValue: TextFieldValue) {
        phoneNumberTextField.value = textFieldValue
    }

    fun clickLoginButton(context: Context) {

        if (phoneNumberTextField.value.text.isEmpty()) {
            Toast.makeText(context, "Please enter your phone number", Toast.LENGTH_SHORT).show()
            return
        }

        val driver = Driver()
            .apply {
                setPhoneNumber(phoneNumberTextField.value.text)
            }
        CurrentDriver.setDriver(driver)
        navigateToHomeActivity(context)


//        val loginRequest = LoginRequest(
//            phoneNumber = phoneNumberTextField.value.text,
//        )
//
//        authRepository.login(loginRequest, object : Callback<LoginResponse> {
//
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    val accessToken = response.body()?.accessToken
//                    val driverData = response.body()?.driverData
//
//                    if (accessToken != null && driverData != null) {
//                        saveAccessToken(context, accessToken)
//                        setCurrentDriver(driverData)
//                        navigateToHomeActivity(context)
//                    } else if (accessToken == null) {
//                        Toast.makeText(context, "No access token provided!", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(context, "No user data provided!", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun saveAccessToken(context: Context, accessToken: String) {
        LocalStorageUtils.writeData(context, LocalStorageUtils.KEY_ACCESS_TOKEN, accessToken)
    }

    private fun setCurrentDriver(fetchDriverResponse: FetchDriverResponse) {
        val driver = Driver()
            .apply {
                setPhoneNumber(fetchDriverResponse.phoneNumber)
                setDriverName(fetchDriverResponse.driverName)
                setDriverAvatar(fetchDriverResponse.driverAvatar)
                setLicensePlate(fetchDriverResponse.licensePlate)
                setVehicleName(fetchDriverResponse.vehicleName)
                setVehicleType(fetchDriverResponse.vehicleType)
            }
        CurrentDriver.setDriver(driver)
    }

    private fun navigateToHomeActivity(context: Context) {
        //val intent = Intent(context, HomeActivity::class.java)
    }
}