package com.ridehailing.driver.globalstate

import com.ridehailing.driver.models.Driver

object CurrentDriver {

    private var driver: Driver = Driver()

    fun getDriver(): Driver {
        return driver
    }

    fun setDriver(driver: Driver) {
        this.driver = driver
    }
}