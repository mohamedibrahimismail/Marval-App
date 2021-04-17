package com.example.marval.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.CommonVH
import com.example.marval.ui.details.DetailsActivity
import com.example.marval.utils.bindImage
import com.yayandroid.parallaxrecyclerview.ParallaxImageView
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder
import kotlinx.android.synthetic.main.main_recycler_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


private const val TAG = "MainActivityAdapter"

class MainActivityAdapter() : RecyclerView.Adapter<MainActivityAdapter.BasicViewHolder>() {

    var list: MutableList<Result> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :BasicViewHolder {
        return BasicViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.main_recycler_item,  parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasicViewHolder, position: Int) {
        with(holder.itemView) {
            character_name_txt.text = list.get(position).name
            image.bindImage(list.get(position).thumbnail.path + "." + list.get(position).thumbnail.extension)
            image.reuse()
            this.onClick {
                context.startActivity(DetailsActivity.getIntent(context, list.get(position)))
            }
        }
    }


    fun addToList(added_list: List<Result>) {
        var old = list.size
        var new = added_list.size - 1
        this.list.addAll(added_list)
        notifyItemRangeInserted(old, new)
    }


    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }

    class BasicViewHolder(itemView: View?) : ParallaxViewHolder(itemView) {

        override fun getParallaxImageId(): Int {
            return R.id.image
        }

    }
}
