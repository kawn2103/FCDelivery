package kst.app.fcdelivery.di

import android.app.Activity
import kotlinx.coroutines.Dispatchers
import kst.app.fcdelivery.BuildConfig
import kst.app.fcdelivery.data.api.SweetTrackerApi
import kst.app.fcdelivery.data.api.Url
import kst.app.fcdelivery.data.db.AppDatabase
import kst.app.fcdelivery.data.entity.TrackingInformation
import kst.app.fcdelivery.data.entity.TrackingItem
import kst.app.fcdelivery.data.preference.PreferenceManager
import kst.app.fcdelivery.data.preference.SharedPreferenceManager
import kst.app.fcdelivery.data.repository.*
import kst.app.fcdelivery.presentation.addtrackingitem.AddTrackingItemFragment
import kst.app.fcdelivery.presentation.addtrackingitem.AddTrackingItemPresenter
import kst.app.fcdelivery.presentation.addtrackingitem.AddTrackingItemsContract
import kst.app.fcdelivery.presentation.trackinghistory.TrackingHistoryContract
import kst.app.fcdelivery.presentation.trackinghistory.TrackingHistoryFragment
import kst.app.fcdelivery.presentation.trackinghistory.TrackingHistoryPresenter
import kst.app.fcdelivery.presentation.trackingitems.TrackingItemsContract
import kst.app.fcdelivery.presentation.trackingitems.TrackingItemsFragment
import kst.app.fcdelivery.presentation.trackingitems.TrackingItemsPresenter
import kst.app.fcdelivery.work.AppWorkerFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {

    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().trackingItemDao() }
    single { get<AppDatabase>().shippingCompanyDao() }

    // Api
    single {
        OkHttpClient()
            .newBuilder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }
    single<SweetTrackerApi> {
        Retrofit.Builder().baseUrl(Url.SWEET_TRACKER_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create()
    }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }


    // Repository
    single<TrackingItemRepository> { TrackingItemRepositoryImpl(get(), get(), get()) }
    single<ShippingCompanyRepository> { ShippingCompanyRepositoryImpl(get(), get(), get(), get()) }

    // Work
    single { AppWorkerFactory(get(), get()) }

    // Presentation
    scope<TrackingItemsFragment> {
        scoped<TrackingItemsContract.Presenter> { TrackingItemsPresenter(getSource(), get()) }
    }
    scope<AddTrackingItemFragment> {
        scoped<AddTrackingItemsContract.Presenter> {
            AddTrackingItemPresenter(getSource(), get(), get())
        }
    }
    scope<TrackingHistoryFragment> {
        scoped<TrackingHistoryContract.Presenter> { (trackingItem: TrackingItem, trackingInformation: TrackingInformation) ->
            TrackingHistoryPresenter(getSource(), get(), trackingItem, trackingInformation)
        }
    }
}