package com.greate43.sk.infosysassement.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.greate43.sk.infosysassement.R
import com.greate43.sk.infosysassement.service.model.Rows
import com.greate43.sk.infosysassement.utilities.GlideApp
import java.util.*

class FactsAdapter(context: Context) : RecyclerView.Adapter<FactsAdapter.FactsHolder>() {
    private val mContext = context
    private var data = Collections.emptyList<Rows>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsHolder {
        return FactsHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_facts, parent, false)
        )
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: FactsHolder, position: Int) {
        holder.bind(context = mContext, rows = data[position])
    }

    // this is will set the data to the list view
    fun setData(newData: List<Rows>) {
        data = newData
        notifyDataSetChanged()
    }

    inner class FactsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(context: Context, rows: Rows) {
            if (rows.title != null && rows.title.isNotEmpty()) {
                itemView.findViewById<TextView>(R.id.titleLbl).text = rows.title
            } else {
                itemView.findViewById<TextView>(R.id.titleLbl).text =
                    context.getString(R.string.no_info)
            }

            if (rows.description != null && rows.description.isNotEmpty()) {
                itemView.findViewById<TextView>(R.id.descriptionLbl).text = rows.description
            } else {
                itemView.findViewById<TextView>(R.id.descriptionLbl).text =
                    context.getString(R.string.no_info)
            }

            if (rows.imageHref != null && rows.imageHref.isNotEmpty()) {
                val requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                GlideApp.with(context).load(rows.imageHref)
                    .apply(requestOptions)
                    .centerInside()
                    .error(context.resources.getDrawable(R.drawable.no_image))
                    .placeholder(context.resources.getDrawable(R.drawable.ic_image_black_24dp))
                    .into(itemView.findViewById(R.id.imageHref))
            } else {
                GlideApp.with(context).load(context.resources.getDrawable(R.drawable.no_image))
                    .centerInside()
                    .into(itemView.findViewById(R.id.imageHref))
            }


        }
    }

}