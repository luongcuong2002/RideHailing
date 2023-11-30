package com.cuongnl.ridehailing.screens.home.tab.booking

import android.content.Context
import android.os.Handler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.globalstate.CurrentUser
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.screens.home.tab.booking.behavior.BookingTabBehavior
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.AddressesList
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.BannerVouchers
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.DashboardBanner
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.TransportationMethodsList
import com.cuongnl.ridehailing.screens.home.tab.booking.ui.WhereDoYouWantToGo
import com.cuongnl.ridehailing.viewmodel.BookingTabUiViewModel
import com.cuongnl.ridehailing.viewmodel.apiservice.UserServiceViewModel
import ir.kaaveh.sdpcompose.sdp

val LocalBehavior =
    staticCompositionLocalOf<BookingTabBehavior> { error("No LocalActivityActionsClass provided") }

@Composable
fun BookingTab(
    userServiceViewModel: UserServiceViewModel = viewModel(),
    bookingTabUiViewModel: BookingTabUiViewModel = viewModel()
) {

    val context = LocalContext.current

    CompositionLocalProvider(LocalBehavior provides BookingTabBehavior(context)) {

        LaunchedEffect(null) {
            getUserAddress(context, userServiceViewModel, bookingTabUiViewModel)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DashboardBanner()

            Column(
                modifier = Modifier
                    .offset(y = (-30).sdp)
                    .weight(1f)
                    .padding(horizontal = 10.sdp)
            ) {
                WhereDoYouWantToGo()
                AddressesList()
                TransportationMethodsList()
                BannerVouchers()
            }
        }
    }
}

private fun getUserAddress(
    context: Context,
    userServiceViewModel: UserServiceViewModel,
    bookingTabUiViewModel: BookingTabUiViewModel
) {

    Handler().postDelayed({
        CurrentUser.getUser()?.clearAddresses()
        CurrentUser.getUser()?.apply {
            addAddress(
                Address(
                    AddressType.HOME,
                    "123 Nguyen Van Linh",
                    "777 Brockton Avenue, Abington MA 2351",
                    10.762622,
                    106.660172
                )
            )
            addAddress(
                Address(
                    AddressType.WORK,
                    "123 Nguyen Van Linh",
                    "30 Memorial Drive, Avon MA 2322",
                    10.762622,
                    106.660172
                )
            )
            addAddress(
                Address(
                    AddressType.OTHER,
                    "123 Nguyen Van Linh",
                    "337 Russell St, Hadley MA 1035",
                    10.762622,
                    106.660172
                )
            )
        }
        bookingTabUiViewModel.setIsLoadingAddress(false)
    }, 2000)


//    userServiceViewModel.getUserAddresses(object : SimpleApiCallback<AddressResponse> {
//        override fun onSuccess(call: Call<AddressResponse>, response: Response<AddressResponse>) {
//            response.body()?.let {
//                CurrentUser.getUser()?.clearAddresses()
//                CurrentUser.getUser()?.addAddresses(it.addresses)
//            }
//        }
//
//        override fun onFinish() {
//            bookingViewModel.setIsLoadingAddress(false)
//        }
//
//        override fun onError(call: Call<AddressResponse>, response: Response<AddressResponse>) {
//            Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
//            Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
//        }
//    })
}