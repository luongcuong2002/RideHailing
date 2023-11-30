package com.cuongnl.ridehailing.viewmodel

import android.app.Activity
import android.os.CountDownTimer
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.firebase.auth.FirebasePhoneNumberAuth
import com.cuongnl.ridehailing.screens.otpverification.OtpVerificationActivity
import com.cuongnl.ridehailing.utils.Constant
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpVerificationViewModel : ViewModel() {

    private var _internationalPhoneNumber = mutableStateOf("")
    private var _otpTimeoutValue = mutableLongStateOf(Constant.OTP_TIMEOUT_IN_SECOND)
    private var _errorOccurred = mutableStateOf<FirebaseException?>(null)

    val internationalPhoneNumber: State<String> = _internationalPhoneNumber
    val otpTimeoutValue: State<Long> = _otpTimeoutValue
    val errorOccurred: State<FirebaseException?> = _errorOccurred

    private var otpCountdown: CountDownTimer? = null
    private var otpId: String? = null
    private var isOtpExpired = false

    fun initiateOtp(activity: Activity) {

        if (internationalPhoneNumber.value.isEmpty()) {
            throw Exception("Phone number can't be empty")
        }

        FirebasePhoneNumberAuth.verifyPhoneNumber(
            activity,
            internationalPhoneNumber.value,
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    otpId = p0
                    isOtpExpired = false
                }

                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    signInWithCredential(activity as OtpVerificationActivity, p0)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    setErrorOccurred(p0)
                    stopOtpTimer()
                }

                override fun onCodeAutoRetrievalTimeOut(p0: String) {
                    super.onCodeAutoRetrievalTimeOut(p0)
                    if (otpId != null && p0 == otpId) {
                        isOtpExpired = true
                    }
                }
            })

        startOtpTimer()
    }

    fun verifyOtp(activity: OtpVerificationActivity, otp: String) {
        if (otpId != null) {
            val credential = PhoneAuthProvider.getCredential(otpId!!, otp)
            signInWithCredential(activity, credential)
        } else {
            Toast.makeText(activity, activity.getText(R.string.otp_not_sent_yet), Toast.LENGTH_LONG)
                .show()
        }
    }

    fun signInWithCredential(activity: OtpVerificationActivity, credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    if (isOtpExpired) {
                        Toast.makeText(
                            activity,
                            activity.getString(R.string.otp_expired_and_send_again),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        activity.navigateToNextActivityAndFinish()
                    }
                } else {
                    Toast.makeText(
                        activity,
                        activity.getString(R.string.incorrect_otp),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    fun setInternationalPhoneNumber(phoneNumber: String) {
        _internationalPhoneNumber.value = phoneNumber
    }

    fun setOtpTimeoutValue(newValue: Long) {
        _otpTimeoutValue.longValue = newValue
    }

    fun setErrorOccurred(error: FirebaseException) {
        _errorOccurred.value = error
    }

    private fun startOtpTimer() {

        stopOtpTimer()

        setOtpTimeoutValue(Constant.OTP_TIMEOUT_IN_SECOND)

        otpCountdown = object : CountDownTimer(otpTimeoutValue.value * 1000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                setOtpTimeoutValue(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                setOtpTimeoutValue(0L)
                otpCountdown = null
            }
        }.start()
    }

    private fun stopOtpTimer() {
        if (otpCountdown != null) {
            otpCountdown?.cancel()
            otpCountdown = null
        }
    }
}