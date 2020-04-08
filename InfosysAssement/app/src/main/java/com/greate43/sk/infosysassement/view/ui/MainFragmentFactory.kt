package com.greate43.sk.infosysassement.view.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory

class MainFragmentFactory() : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            MainFragment::class.java.name -> {
                MainFragment()
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}