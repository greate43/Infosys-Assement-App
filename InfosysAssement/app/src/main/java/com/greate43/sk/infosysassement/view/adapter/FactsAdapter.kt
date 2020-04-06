package com.greate43.sk.infosysassement.view.adapter

import android.content.Context
import android.os.Build
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
    private var TAG = FactsAdapter::class.java.simpleName
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
                // caching image using glide
                val requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                loadImage(context, rows, requestOptions)

            } else {
                noImage(context)
            }


        }

        // if file does not exists on tbe server  then glide will throw an exception and show a error image
        private fun loadImage(
            context: Context,
            rows: Rows,
            requestOptions: RequestOptions
        ) {
            GlideApp.with(context).load(rows.imageHref)
                .apply(requestOptions)
                .centerInside()
                .error(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        context.resources.getDrawable(R.drawable.no_image, null)
                    } else {
                        context.resources.getDrawable(R.drawable.no_image)

                    }
                )
                .placeholder(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        context.resources.getDrawable(R.drawable.ic_image_black_24dp, null)
                    } else {
                        context.resources.getDrawable(R.drawable.ic_image_black_24dp)
                    }
                )
                .into(itemView.findViewById(R.id.imageHref))
        }

        private fun noImage(context: Context) {
            GlideApp.with(context).load(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    context.resources.getDrawable(R.drawable.no_image, null)
                } else {
                    context.resources.getDrawable(R.drawable.no_image)

                }
            )
                .centerInside()
                .into(itemView.findViewById(R.id.imageHref))
        }
    }

}