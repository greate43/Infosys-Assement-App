package com.greate43.sk.infosysassement.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.greate43.sk.infosysassement.R
import com.greate43.sk.infosysassement.service.model.Facts
import com.greate43.sk.infosysassement.viewmodel.FactsViewModel
import okhttp3.ResponseBody
import java.io.IOException


class MainFragment : Fragment() {
    val TAG = MainFragment::class.java.simpleName
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
        viewModel.getFacts().observe(viewLifecycleOwner,
            Observer { facts ->
               activity?.title  = facts.title
            })
    }

}
