package com.ridehailing.driver.network.socketio

import com.ridehailing.driver.computerlocal.ServerAddress
import io.socket.client.IO
import io.socket.client.Socket

object SocketClient {
    fun getSocket(namespace: String = "/"): Socket? {
        return IO.socket(ServerAddress.SERVER_ADDRESS + namespace)
    }
}