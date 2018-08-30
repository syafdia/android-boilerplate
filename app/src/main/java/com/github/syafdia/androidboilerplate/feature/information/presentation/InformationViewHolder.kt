package com.github.syafdia.androidboilerplate.feature.information.presentation

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.github.syafdia.androidboilerplate.R
import timber.log.Timber


class InformationViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    val textViewTitle: TextView = view.findViewById(R.id.textView_information_itemTitle)

    val textViewBody: TextView = view.findViewById(R.id.textView_information_itemBody)

    val sdvImage: SimpleDraweeView = view.findViewById(R.id.simpleDraweeView_information_itemImage)

    override fun onClick(v: View?) {
        Timber.d("Info has been clicked")
    }

}