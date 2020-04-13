package com.greate43.sk.infosysassement

import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.core.FlowableOnSubscribe
import java.net.HttpURLConnection
import java.net.URL

object Utils {
    val TAG = Utils::class.java.simpleName
    fun checkIfUrlExists(URLName: String?): @NonNull Flowable<String?> {
        val handler = FlowableOnSubscribe { emitter: FlowableEmitter<String?> ->
            emitter.onNext(
                try {
                    HttpURLConnection.setFollowRedirects(false)
                    val con: HttpURLConnection =
                        URL(URLName).openConnection() as HttpURLConnection
                    con.requestMethod = "HEAD"
                    if (con.responseCode == HttpURLConnection.HTTP_OK ||
                        con.responseCode == HttpURLConnection.HTTP_NOT_MODIFIED ||
                        con.responseCode == HttpURLConnection.HTTP_MOVED_PERM
                    ) {
                        URLName
                    } else {
                        Log.d(TAG, "http code ${con.responseCode}")
                        ""
                    }
                } catch (e: Exception) {
                    ""
                }
            )
        }

        return Flowable.create(handler, BackpressureStrategy.BUFFER)
    }
}