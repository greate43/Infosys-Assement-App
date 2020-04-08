package com.greate43.sk.infosysassement.view.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.greate43.sk.infosysassement.MyApplication
import com.greate43.sk.infosysassement.R
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainFragmentTest {
    val TAG = MainFragmentTest::class.java.simpleName

    private lateinit var scenario: FragmentScenario<MainFragment>

    @Before
    fun init() {
        val fragmentArgs = Bundle()
        val factory = MainFragmentFactory()
        scenario = launchFragment<MainFragment>(fragmentArgs, R.style.AppTheme, factory)
    }

    @Test
    fun checkIfTitleHasTheData() {
        scenario.onFragment { mainFragment ->
            if (MyApplication.getInstance()?.hasNetwork()!!) {
                mainFragment.viewModel.getFacts().observe(mainFragment.viewLifecycleOwner,
                    androidx.lifecycle.Observer { facts ->
                        mainFragment.activity?.title = facts?.title
                        Log.d(TAG, "title: ${mainFragment.activity?.title}")
                        assertEquals(facts.title, mainFragment.activity?.title)
                    })
            }
        }
    }

    @Test
    fun checkIfViewHolderIsDisplayed() {
        scenario.onFragment { mainFragment ->
            if (MyApplication.getInstance()?.hasNetwork()!!) {
                mainFragment.viewModel.getFacts().observe(mainFragment.viewLifecycleOwner,
                    androidx.lifecycle.Observer { facts ->
                        facts.rows?.let { mainFragment.adapter.setData(it) }

                        for (pos in 0 until facts.rows?.size!!)
                        /* check if the ViewHolder is being displayed */
                            onView(RecyclerViewMatcher(R.id.mainRecyclerView)
                                    .atPositionOnView(pos, R.id.factsCardView))
                                .check(matches(isDisplayed()))

                    })
            }
        }
    }
    @Test
    fun checkIfCacheIsWorking() {
        scenario.onFragment { mainFragment ->
            if (MyApplication.getInstance()?.hasNetwork()!!) {
                mainFragment.prefs.allowQuery = true
                queryFacts(mainFragment)
            }  else {
                // if it has been online at least once then query facts from cache
               if (mainFragment.prefs.allowQuery){
                   queryFacts(mainFragment)
               }
            }
        }
    }

    private fun queryFacts(mainFragment: MainFragment) {
        mainFragment.viewModel.getFacts().observe(mainFragment.viewLifecycleOwner,
            Observer { facts ->
                mainFragment.activity?.title = facts?.title
                facts.rows?.let { mainFragment.adapter.setData(it) }

                for (pos in 0 until facts.rows?.size!!)
                /* check if the ViewHolder is being displayed */
                    onView(RecyclerViewMatcher(R.id.mainRecyclerView)
                        .atPositionOnView(pos, R.id.factsCardView))
                        .check(matches(isDisplayed()))
            })
    }

}