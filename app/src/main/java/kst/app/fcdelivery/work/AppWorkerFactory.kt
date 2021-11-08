package kst.app.fcdelivery.work

import androidx.work.DelegatingWorkerFactory
import kotlinx.coroutines.CoroutineDispatcher
import kst.app.fcdelivery.data.repository.TrackingItemRepository


class AppWorkerFactory(
    trackingItemRepository: TrackingItemRepository,
    dispatcher: CoroutineDispatcher
) : DelegatingWorkerFactory() {

    init {
        addFactory(TrackingCheckWorkerFactory(trackingItemRepository, dispatcher))
    }
}