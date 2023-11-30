package com.cuongnl.ridehailing.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
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
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.screens.booking.BookingActivity
import com.cuongnl.ridehailing.utils.Constant
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

    fun onClickAddressPredictionResponse(context: Context, addressPrediction: AutocompletePrediction) {
        val text = addressPrediction.getPrimaryText(null).toString()

        when (currentAddressType.value) {
            SelectingLocationType.PICKUP_LOCATION -> {
                setPickupLocationAndUpdateTextField(context, addressPrediction.placeId, text)
            }
            SelectingLocationType.DESTINATION_LOCATION -> {
                setDestinationLocationAndUpdateTextField(context, addressPrediction.placeId, text)
            }
        }
    }

    fun onClickSavedAddress(context: Context, item: Address) {
        val latLng = LatLng(item.latitude, item.longitude)

        when (currentAddressType.value) {
            SelectingLocationType.PICKUP_LOCATION -> {
                setPickupLocationAndPerformNextAction(context, latLng, item.fullName)
            }
            SelectingLocationType.DESTINATION_LOCATION -> {
                setDestinationLocationAndPerformNextAction(context, latLng, item.fullName)
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

    fun setDestinationLocationAndUpdateTextField(context: Context, placeId: String, text: String) {
        MapUtils.getPlaceById(placesClient = placesClient,
            placeId = placeId,
            placeFields = listOf(Place.Field.LAT_LNG),
            onSuccess = {
                setDestinationLocationAndPerformNextAction(context, it.latLng, text)
            }
        )
    }

    fun setDestinationLocationAndPerformNextAction(context: Context, latLng: LatLng?, addressText: String) {
        setDestinationLocationLatLng(latLng)
        setDestinationTextField(addressText)

        if (pickupLocationLatLng.value == null) {
            pickupFocusRequester.requestFocus()
        } else {
            navigateToBookingActivity(context)
        }
    }

    fun setPickupLocationAndUpdateTextField(context: Context, placeId: String, text: String) {
        MapUtils.getPlaceById(placesClient = placesClient,
            placeId = placeId,
            placeFields = listOf(Place.Field.LAT_LNG),
            onSuccess = {
                setPickupLocationAndPerformNextAction(context, it.latLng, text)
            }
        )
    }

    fun setPickupLocationAndPerformNextAction(context: Context, latLng: LatLng?, addressText: String) {
        setPickupLocationLatLng(latLng)
        setPickupTextField(addressText)

        if (destinationLocationLatLng.value == null) {
            destinationFocusRequester.requestFocus()
        } else {
            navigateToBookingActivity(context)
        }
    }

    fun navigateToBookingActivity(context: Context) {

        val intent = Intent(context, BookingActivity::class.java)
        intent.putExtra(Constant.BUNDLE_DESTINATION_LAT_LNG, destinationLocationLatLng.value)
        intent.putExtra(Constant.BUNDLE_PICKUP_LAT_LNG, pickupLocationLatLng.value)
        intent.putExtra(Constant.BUNDLE_DESTINATION_ADDRESS, destinationTextField.value.text)
        intent.putExtra(Constant.BUNDLE_PICKUP_ADDRESS, pickupTextField.value.text)

        context.startActivity(intent)
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