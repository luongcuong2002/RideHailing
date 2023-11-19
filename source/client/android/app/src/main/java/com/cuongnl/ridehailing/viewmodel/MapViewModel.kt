package com.cuongnl.ridehailing.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings

class MapViewModel : ViewModel() {

    private val _uiSettings = mutableStateOf(MapUiSettings().copy(zoomControlsEnabled = false))
    private val _properties = mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    private val _cameraPositionState = mutableStateOf(CameraPositionState())

    val uiSettings: State<MapUiSettings> = _uiSettings
    val properties: State<MapProperties> = _properties
    val cameraPositionState: State<CameraPositionState> = _cameraPositionState

    fun setUiSettings(uiSettings: MapUiSettings) {
        _uiSettings.value = uiSettings
    }

    fun setProperties(properties: MapProperties) {
        _properties.value = properties
    }

    fun setCameraPositionState(cameraPositionState: CameraPositionState) {
        _cameraPositionState.value = cameraPositionState
    }
}