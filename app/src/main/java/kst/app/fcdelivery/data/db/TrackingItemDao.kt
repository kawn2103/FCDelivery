package kst.app.fcdelivery.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kst.app.fcdelivery.data.entity.TrackingItem

@Dao
interface TrackingItemDao {

    @Query("SELECT * FROM TrackingItem")
    fun allTrackingItems(): Flow<List<TrackingItem>>

    @Query("SELECT * FROM TrackingItem")
    suspend fun getAll(): List<TrackingItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TrackingItem)

    @Delete
    suspend fun delete(item: TrackingItem)
}