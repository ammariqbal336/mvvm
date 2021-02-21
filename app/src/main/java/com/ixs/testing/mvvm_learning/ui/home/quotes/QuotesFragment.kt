package com.ixs.testing.mvvm_learning.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager


import com.ixs.testing.mvvm_learning.R
import com.ixs.testing.mvvm_learning.data.db.entities.Quotes
import com.ixs.testing.mvvm_learning.utils.Coroutine
import com.ixs.testing.mvvm_learning.utils.hide
import com.ixs.testing.mvvm_learning.utils.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
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
        bindUI()

    }

    private fun bindUI() = Coroutine.main {
       progress_bar.show()
        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            progress_bar.hide()
            initRecycleView(it.toQuoteItem())
        })
    }

    private fun initRecycleView(it: List<QuoteItem>) {
       val mAdapter = GroupAdapter<ViewHolder>().apply {
           addAll(it)

       }
        rv_quotes.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter= mAdapter
        }
    }

    private fun List<Quotes>.toQuoteItem() :List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }

}