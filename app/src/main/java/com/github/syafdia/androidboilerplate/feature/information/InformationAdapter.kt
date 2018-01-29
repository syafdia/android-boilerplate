package com.github.syafdia.androidboilerplate.feature.information

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.syafdia.androidboilerplate.R


class InformationAdapter(
        private val infos: MutableList<InformationItemModel>
) : RecyclerView.Adapter<InformationViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): InformationViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_information, parent, false)

        return InformationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return infos.size
    }

    override fun onBindViewHolder(holder: InformationViewHolder?, position: Int) {
        val info = infos[position]
        val imgUri = Uri.parse(info.imageUri)

        holder?.textViewTitle?.text = info.title
        holder?.textViewBody?.text = info.body
        holder?.sdvImage?.setImageURI(imgUri)
    }
}