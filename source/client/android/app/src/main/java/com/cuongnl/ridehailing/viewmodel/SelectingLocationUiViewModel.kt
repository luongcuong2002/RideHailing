package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import com.cuongnl.ridehailing.enums.SelectingLocationType
import com.cuongnl.ridehailing.globalstate.CurrentLocation
import com.cuongnl.ridehailing.utils.MapUtils
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse

class SelectingLocationUiViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG = "SelectingLocationUiViewModel"

    private val placesClient = Places.createClient(application)
    private var cancellationTokenSource = CancellationTokenSource()

    val addressPredictions = mutableStateListOf<AutocompletePrediction>()
    private val _isFetchingAddressPredictions = mutableStateOf(false)
    private val _currentAddressType = mutableStateOf(SelectingLocationType.DESTINATION_LOCATION)
    private val _isAddressResponsesVisible = mutableStateOf(false)
    private val _destinationLocationLatLng = mutableStateOf<LatLng?>(CurrentLocation.getLatLng())
    private val _pickupLocationLatLng = mutableStateOf<LatLng?>(CurrentLocation.getLatLng())
    private val _destinationTextField = mutableStateOf(TextFieldValue(CurrentLocation.getFullAddress()))
    private val _pickupTextField = mutableStateOf(TextFieldValue(CurrentLocation.getFullAddress()))

    val isFetchingAddressPredictions: State<Boolean> = _isFetchingAddressPredictions
    val currentAddressType: State<SelectingLocationType> = _currentAddressType
    val isAddressResponsesVisible: State<Boolean> = _isAddressResponsesVisible
    val destinationLocationLatLng: State<LatLng?> = _destinationLocationLatLng
    val pickupLocationLatLng: State<LatLng?> = _pickupLocationLatLng
    val destinationTextField: State<TextFieldValue> = _destinationTextField
    val pickupTextField: State<TextFieldValue> = _pickupTextField

    val destinationFocusRequester = FocusRequester()
    val pickupFocusRequester = FocusRequester()

    fun onDestinationTextChange(text: String) {
        if (text.isNotEmpty()) {
            getAddressPredictions(text)
            setAddressResponsesVisibleAndCancelFetching(true)
        } else {
            setAddressResponsesVisibleAndCancelFetching(false)
            setDestinationLocationLatLng(null)
        }
    }

    fun onPickupTextChange(text: String) {
        if (text.isNotEmpty()) {
            getAddressPredictions(text)
            setAddressResponsesVisibleAndCancelFetching(true)
        } else {
            setAddressResponsesVisibleAndCancelFetching(false)
            setPickupLocationLatLng(null)
        }
    }

    fun onClickAddressPredictionResponse(addressPrediction: AutocompletePrediction) {
        val text = addressPrediction.getPrimaryText(null).toString()

        when (currentAddressType.value) {
            SelectingLocationType.PICKUP_LOCATION -> {
                setPickupLocationAndUpdateTextField(addressPrediction.placeId, text)
            }
            SelectingLocationType.DESTINATION_LOCATION -> {
                setDestinationLocationAndUpdateTextField(addressPrediction.placeId, text)
            }
        }
    }

    fun getAddressPredictions(query: String) {

        setIsFetchingAddressPredictions(true)

        cancellationTokenSource.cancel()
        cancellationTokenSource = CancellationTokenSource()

        val request =
            FindAutocompletePredictionsRequest.builder().setOrigin(CurrentLocation.getLatLng())
                .setCountries("VN").setTypesFilter(listOf(PlaceTypes.ADDRESS))
                .setCancellationToken(cancellationTokenSource.token).setQuery(query).build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response: FindAutocompletePredictionsResponse ->
                addressPredictions.clear()
                addressPredictions.addAll(response.autocompletePredictions)
            }.addOnFailureListener { exception: Exception? ->
                if (exception is ApiException) {
                    Log.e(TAG, "Place not found: ${exception.statusCode}")
                }
            }.addOnCompleteListener {
                setIsFetchingAddressPredictions(false)
            }
    }

    fun setDestinationLocationAndUpdateTextField(placeId: String, text: String) {
        MapUtils.getPlaceById(placesClient = placesClient,
            placeId = placeId,
            placeFields = listOf(Place.Field.LAT_LNG),
            onSuccess = {
                setDestinationLocationLatLng(it.latLng)
                setDestinationTextField(text)

                if (pickupLocationLatLng.value == null) {
                    pickupFocusRequester.requestFocus()
                }
            }
        )
    }

    fun setPickupLocationAndUpdateTextField(placeId: String, text: String) {
        MapUtils.getPlaceById(placesClient = placesClient,
            placeId = placeId,
            placeFields = listOf(Place.Field.LAT_LNG),
            onSuccess = {
                setPickupLocationLatLng(it.latLng)
                setPickupTextField(text)

                if (destinationLocationLatLng.value == null) {
                    destinationFocusRequester.requestFocus()
                }
            }
        )
    }

    fun setIsFetchingAddressPredictions(isFetching: Boolean) {
        _isFetchingAddressPredictions.value = isFetching
    }

    fun setCurrentAddressType(addressType: SelectingLocationType) {
        _currentAddressType.value = addressType
    }

    fun setAddressResponsesVisibleAndCancelFetching(isVisible: Boolean) {
        _isAddressResponsesVisible.value = isVisible

        if (!isVisible) {
            cancellationTokenSource.cancel()
        }
    }

    fun setDestinationLocationLatLng(latLng: LatLng?) {
        _destinationLocationLatLng.value = latLng
    }

    fun setPickupLocationLatLng(latLng: LatLng?) {
        _pickupLocationLatLng.value = latLng
    }

    fun setDestinationTextField(newText: String) {
        _destinationTextField.value = _destinationTextField.value.copy(newText)
    }

    fun setPickupTextField(newText: String) {
        _pickupTextField.value = _pickupTextField.value.copy(newText)
    }

    fun getDestinationTextFieldState(): MutableState<TextFieldValue> {
        return _destinationTextField
    }

    fun getPickupTextFieldState(): MutableState<TextFieldValue> {
        return _pickupTextField
    }
}