package com.cuongnl.ridehailing.activitybehavior

interface IOtpVerificationActivityBehavior {
    fun initiateOtp()
    fun popActivity()
    fun navigateToNextActivityAndFinish()
}