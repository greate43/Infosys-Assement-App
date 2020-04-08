package com.greate43.sk.infosysassement.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.greate43.sk.infosysassement.R

class MainActivity : AppCompatActivity() {
    private val mainFragmentFactory = MainFragmentFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = mainFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fragment = supportFragmentManager.fragmentFactory.instantiate(classLoader, MainFragment::class.java.name)
            supportFragmentManager.beginTransaction()
                .add(R.id.mainFragmentContainer, fragment)
                .commit()
        }
    }
}
