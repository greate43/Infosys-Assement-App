package com.greate43.sk.infosysassement.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.greate43.sk.infosysassement.R
import com.greate43.sk.infosysassement.view.adapter.FactsAdapter
import com.greate43.sk.infosysassement.viewmodel.FactsViewModel
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {
    val TAG = MainFragment::class.java.simpleName
    lateinit var adapter: FactsAdapter


    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModels<FactsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainRecyclerView.setHasFixedSize(true)
        val llm = LinearLayoutManager(context)
        llm.orientation = RecyclerView.VERTICAL

        mainRecyclerView.layoutManager = llm
        mainRecyclerView.itemAnimator = DefaultItemAnimator()

        adapter = FactsAdapter(requireContext())


        mainRecyclerView.adapter = adapter

        liveData()

        mainSwipeRefreshLayout.setOnRefreshListener {
            liveData()
        }

    }

    private fun liveData() {
        viewModel.getFacts().observe(viewLifecycleOwner,
            Observer { facts ->
                activity?.title = facts.title
                adapter.setData(facts.rows)
                if (mainSwipeRefreshLayout.isRefreshing) {
                    mainSwipeRefreshLayout.isRefreshing = false
                }
            })
    }

}
