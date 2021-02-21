package com.ixs.testing.mvvm_learning.ui.home.quotes

import com.ixs.testing.mvvm_learning.R
import com.ixs.testing.mvvm_learning.data.db.entities.Quotes
import com.ixs.testing.mvvm_learning.databinding.QuotesItemBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quotes: Quotes
) : BindableItem<QuotesItemBinding>(){
    override fun getLayout() = R.layout.quotes_item

    override fun bind(viewBinding: QuotesItemBinding, position: Int) {
        viewBinding.quote = quotes;
    }
}