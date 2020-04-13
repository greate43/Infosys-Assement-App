package com.greate43.sk.infosysassement.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greate43.sk.infosysassement.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, MainFragment.newInstance())
                .commit()
        }
    }
}
