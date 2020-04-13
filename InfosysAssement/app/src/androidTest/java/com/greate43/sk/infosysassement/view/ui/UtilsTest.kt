package com.greate43.sk.infosysassement.view.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.greate43.sk.infosysassement.Utils
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsTest {
    var url = ""

    @Before
    fun init() {
        url =
            "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
    }

    @Test
    fun checkIfUrlExistsTest() {
        Utils.checkIfUrlExists(url).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { exists ->
                    assertNotNull(exists)
                    assertTrue(exists!!.isNotEmpty())
            }
    }
}