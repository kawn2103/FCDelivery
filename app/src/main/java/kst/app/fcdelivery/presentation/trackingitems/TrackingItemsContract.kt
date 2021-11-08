package kst.app.fcdelivery.presentation.trackingitems

import kst.app.fcdelivery.data.entity.TrackingInformation
import kst.app.fcdelivery.data.entity.TrackingItem
import kst.app.fcdelivery.presentation.BasePresenter
import kst.app.fcdelivery.presentation.BaseView

class TrackingItemsContract {

    interface View : BaseView<Presenter> {

        fun showLoadingIndicator()

        fun hideLoadingIndicator()

        fun showNoDataDescription()

        fun showTrackingItemInformation(trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>)
    }

    interface Presenter : BasePresenter {

        var trackingItemInformation: List<Pair<TrackingItem, TrackingInformation>>

        fun refresh()
    }
}