package com.cuongnl.ridehailing.globalstate

import com.cuongnl.ridehailing.models.User

object CurrentUser {

    private var user: User? = null

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User) {
        this.user = user
    }
}