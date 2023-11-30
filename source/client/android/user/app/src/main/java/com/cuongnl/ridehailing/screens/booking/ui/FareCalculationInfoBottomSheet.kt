package com.cuongnl.ridehailing.screens.booking.ui

import android.widget.TextView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cuongnl.ridehailing.R
import com.cuongnl.ridehailing.viewmodel.BookingActivityUiViewModel
import com.cuongnl.ridehailing.widgets.AppText
import com.cuongnl.ridehailing.widgets.TouchableOpacityButton
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FareCalculationInfoBottomSheet(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {

    if (bookingActivityUiViewModel.isBottomSheetVisible.value) {

        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
        )

        val pagerState = rememberPagerState(pageCount = {
            bookingActivityUiViewModel.bookingsInfo.size
        })

        LaunchedEffect(bookingActivityUiViewModel.isBottomSheetVisible.value) {
            if (bookingActivityUiViewModel.isBottomSheetVisible.value) {
                sheetState.show()
            } else {
                sheetState.hide()
            }
        }

        LaunchedEffect(null) {
            pagerState.scrollToPage(bookingActivityUiViewModel.fareCalculationInfoSelectedIndex.value)
        }

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                bookingActivityUiViewModel.setFareCalculationInfoSelectedIndex(page)
            }
        }

        ModalBottomSheet(
            onDismissRequest = {
                bookingActivityUiViewModel.setIsBottomSheetVisible(false)
            },
            sheetState = sheetState,
            windowInsets = WindowInsets.navigationBars,
            modifier = Modifier
                .navigationBarsPadding()
                .height((LocalConfiguration.current.screenHeightDp * 0.85).dp),
            containerColor = Color.White,
            dragHandle = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.dp)
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Banner()
                        TextInfo()
                    }
                }
                Indicator(pagerState)
            }
            SelectButton()
        }
    }
}

@Composable
private fun Banner(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel(),
) {

    val imageId =
        bookingActivityUiViewModel.bookingsInfo[bookingActivityUiViewModel.fareCalculationInfoSelectedIndex.value].transportationType.bannerFareCalculationInfo

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.sdp)
            .background(colorResource(id = R.color.orange_50))
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(10.sdp)
                .align(Alignment.Center)
                .fillMaxHeight()
        )
    }
}

@Composable
private fun ColumnScope.TextInfo(bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()) {

    val scrollState = rememberScrollState()

    val htmlText = bookingActivityUiViewModel.bookingsInfo[bookingActivityUiViewModel.fareCalculationInfoSelectedIndex.value].bookingInfoResponse?.fareCalculationInfo ?: "Loading..."

    AndroidView(
        modifier = Modifier
            .padding(top = 20.sdp)
            .padding(horizontal = 15.sdp)
            .weight(1f)
            .verticalScroll(scrollState),
        factory = { context -> TextView(context) },
        update = {
            it.text = HtmlCompat.fromHtml(
                htmlText,
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
        }
    )
}

@Composable
private fun SelectButton(
    bookingActivityUiViewModel: BookingActivityUiViewModel = viewModel()
) {
    TouchableOpacityButton(
        modifier = Modifier
            .padding(vertical = 20.sdp)
            .padding(horizontal = 15.sdp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.sdp))
            .background(colorResource(id = R.color.app_color))
            .padding(vertical = 10.sdp),
        onClick = {

        }
    ) {
        AppText(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.select_text) + " " + bookingActivityUiViewModel.bookingsInfo[bookingActivityUiViewModel.fareCalculationInfoSelectedIndex.value].transportationType.globalName,
            fontSize = 14.ssp,
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.Medium
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Indicator(pagerState: PagerState) {
    Row(
        Modifier
            .padding(top = 175.sdp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration)
                colorResource(id = R.color.orange_600)
            else
                colorResource(id = R.color.orange_200)

            Box(
                modifier = Modifier
                    .padding(2.sdp)
                    .clip(CircleShape)
                    .background(color)
                    .size(5.sdp)
            )
        }
    }
}