package com.ixs.testing.mvvm_learning.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer


import com.ixs.testing.mvvm_learning.R
import com.ixs.testing.mvvm_learning.utils.Coroutine
import com.ixs.testing.mvvm_learning.utils.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class QuotesFragment : Fragment(),KodeinAware {

    override val kodein by kodein()

    private val factory : QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)

        Coroutine.main {
            val quotes = viewModel.quotes.await()
           quotes.observe(viewLifecycleOwner, Observer {
                context?.toast(it.size.toString())
           })
        }
    }

}