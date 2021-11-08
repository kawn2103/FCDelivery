package kst.app.fcdelivery.presentation.trackinghistory

import kst.app.fcdelivery.data.entity.TrackingInformation
import kst.app.fcdelivery.data.entity.TrackingItem
import kst.app.fcdelivery.presentation.BasePresenter
import kst.app.fcdelivery.presentation.BaseView

class TrackingHistoryContract {

    interface View : BaseView<Presenter> {

        fun hideLoadingIndicator()

        fun showTrackingItemInformation(trackingItem: TrackingItem, trackingInformation: TrackingInformation)

        fun finish()
    }

    interface Presenter : BasePresenter {

        fun refresh()

        fun deleteTrackingItem()
    }
}