package kst.app.fcdelivery.data.repository

import kotlinx.coroutines.flow.Flow
import kst.app.fcdelivery.data.entity.TrackingInformation
import kst.app.fcdelivery.data.entity.TrackingItem

interface TrackingItemRepository {

    val trackingItems: Flow<List<TrackingItem>>

    suspend fun getTrackingItemInformation(): List<Pair<TrackingItem, TrackingInformation>>

    suspend fun getTrackingInformation(companyCode: String, invoice: String): TrackingInformation?

    suspend fun saveTrackingItem(trackingItem: TrackingItem)

    suspend fun deleteTrackingItem(trackingItem: TrackingItem)
}