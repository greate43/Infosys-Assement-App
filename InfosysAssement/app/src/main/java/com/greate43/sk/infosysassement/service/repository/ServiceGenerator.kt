package com.greate43.sk.infosysassement.service.repository

import android.util.Log
import com.greate43.sk.infosysassement.MyApplication
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


object ServiceGenerator {
    private val TAG = ServiceGenerator::class.java.simpleName
    private const val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"
    private const val cacheSize = 5 * 1024 * 1024.toLong() // 5 MB

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient()!!)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    private val requestApi: DropboxFactsApi = retrofit.create(DropboxFactsApi::class.java)

    fun getRequestApi(): DropboxFactsApi {
        return requestApi
    }


    private fun okHttpClient(): OkHttpClient? {
        return OkHttpClient.Builder()
            .cache(cache())
            .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
            .addNetworkInterceptor(networkInterceptor()) // only used when network is on
            .addInterceptor(offlineInterceptor())
            .build()
    }

    private fun cache(): Cache {
        return Cache(
            File(
                MyApplication.getInstance()!!.cacheDir,
                "someIdentifier"
            ), cacheSize
        )
    }

    /**
     * This interceptor will be called both if the network is available and if the network is not available
     * @return
     */
    private fun offlineInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "offline interceptor: called.")
            var request: Request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!MyApplication.getInstance()?.hasNetwork()!!) {
                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    /**
     * This interceptor will be called ONLY if the network is available
     * @return
     */
    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Log.d(TAG, "network interceptor: called.")
            val response: Response = chain.proceed(chain.request())
            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()
            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                Log.d(
                    TAG,
                    "log: http log: $message"
                )
            })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }


}