package com.example.marval.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marval.R
import com.example.marval.model.main.Item
import com.example.marval.model.main.Result
import com.example.marval.ui.base.CommonVH
import com.example.marval.ui.details.DetailsActivity
import com.example.marval.utils.bindImage
import kotlinx.android.synthetic.main.comic_layout_item.view.*
import kotlinx.android.synthetic.main.main_recycler_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class ComicsAdapter(var list: List<Item>) : RecyclerView.Adapter<CommonVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.comic_layout_item,  parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {
            comic_txt.text = list.get(position).name
            comic_image.bindImage(list.get(position).resourceURI)
//            this.onClick {
//                context.startActivity(DetailsActivity.getIntent(context, list.get(position)))
//            }
        }
    }


    override fun getItemCount(): Int {
        //return languages.size
        return list.size
    }
}
