package com.cuongnl.ridehailing.activitybehavior

interface ILoginActivityBehavior {
    fun isInvalidTextVisible(): Boolean
    fun canClickContinueButton(): Boolean
    fun openPolicy()
    fun onContinueButtonClicked()
}