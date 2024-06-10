package com.nimeshpatel.cicd.app

import android.os.StrictMode
import androidx.hilt.work.HiltWorkerFactory
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import dagger.Lazy
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 10-Jun-24.
 * Purpose:
 */
@HiltAndroidApp
class MyApplication : MultiDexApplication(){
    @Inject
    lateinit var hiltWorkerFactory: Lazy<HiltWorkerFactory>

    @Inject
    lateinit var coroutineScope: Lazy<CoroutineScope>

    override fun onCreate() {
        super.onCreate()

        coroutineScope.get().launch {
            if (BuildConfig.DEBUG) {
                val vmPolicy =
                    StrictMode.VmPolicy.Builder()
                        .detectFileUriExposure()
                        .detectLeakedClosableObjects()
                        .detectLeakedSqlLiteObjects()
                        .penaltyLog()
                        .detectAll()
                        .build()

                val threadPolicy =
                    StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyDeath()
                        .build()

                StrictMode.setThreadPolicy(threadPolicy)
                StrictMode.setVmPolicy(vmPolicy)
            }
        }
    }
}
