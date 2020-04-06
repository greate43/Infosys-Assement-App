package com.greate43.sk.infosysassement.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.greate43.sk.infosysassement.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.add(R.id.mainContainer, MainFragment.newInstance()).commit()
        }
    }
}
