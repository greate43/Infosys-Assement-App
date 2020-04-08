package com.greate43.sk.infosysassement.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greate43.sk.infosysassement.MyApplication
import com.greate43.sk.infosysassement.R
import com.greate43.sk.infosysassement.utilities.Prefs
import com.greate43.sk.infosysassement.view.adapter.FactsAdapter
import com.greate43.sk.infosysassement.viewmodel.FactsViewModel
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    val TAG = MainFragment::class.java.simpleName
    lateinit var adapter: FactsAdapter

    lateinit var prefs: Prefs

    companion object {
        fun newInstance() = MainFragment()
    }

     val viewModel by viewModels<FactsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        prefs = Prefs(requireContext())


        mainRecyclerView.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        llm.orientation = RecyclerView.VERTICAL

        mainRecyclerView.layoutManager = llm
        mainRecyclerView.itemAnimator = DefaultItemAnimator()

        adapter = FactsAdapter(requireContext())


        mainRecyclerView.adapter = adapter
        if (MyApplication.getInstance()?.hasNetwork()!!) {
            prefs.allowQuery = true
        } else {
            showToast(R.string.no_internet)
        }

        mainSwipeRefreshLayout.setOnRefreshListener {
            if (MyApplication.getInstance()?.hasNetwork()!!) {
                prefs.allowQuery = true
            } else {
                showToast(R.string.no_internet)
            }

            if (!prefs.allowQuery) {
                showToast(R.string.no_cache)
            } else {
                queryFacts()
            }
            stopRefresh()
        }

        if (!prefs.allowQuery) {
            showToast(R.string.no_cache)
        } else {
            queryFacts()
        }
    }

    private fun showToast(id: Int) {
        Toast.makeText(requireContext(), getString(id), Toast.LENGTH_SHORT).show()
    }

     fun queryFacts() {
        if (prefs.allowQuery) {
            viewModel.getFacts().observe(viewLifecycleOwner,
                Observer { facts ->
                    activity?.title = facts?.title
                    facts.rows?.let { adapter.setData(it) }
                    Log.d(TAG,"title: ${activity?.title}")

                })
        }
    }

    private fun stopRefresh() {
        if (mainSwipeRefreshLayout.isRefreshing) {
            mainSwipeRefreshLayout.isRefreshing = false
        }
    }

}
