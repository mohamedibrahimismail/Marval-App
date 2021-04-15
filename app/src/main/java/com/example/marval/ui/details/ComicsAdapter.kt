package com.example.marval.ui.details

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marval.R
import com.example.marval.ui.base.CommonVH
import com.example.marval.utils.bindImage
import kotlinx.android.synthetic.main.comic_layout_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


private const val TAG = "ComicsAdapter"

class ComicsAdapter(var list: List<com.example.marval.model.resource.Result>) :
    RecyclerView.Adapter<CommonVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.comic_layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {

            if (list.get(position).title != null) {
                comic_txt.text = list.get(position).title
            }
            if (list.get(position).thumbnail != null) {
                comic_image.bindImage(list.get(position).thumbnail!!.path + "." + list.get(position).thumbnail!!.extension)
                this.onClick {
                    context.startActivity(
                        ImageActivity.getIntent(
                            context,
                            list.get(position).thumbnail!!.path + "." + list.get(position).thumbnail!!.extension
                        )
                    )
                }
            } else {
                loader.visibility = View.GONE
                comic_image.bindImage("")
            }

        }
    }


    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }
}
