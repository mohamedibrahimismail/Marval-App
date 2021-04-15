package com.example.marval.ui.search

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marval.R
import com.example.marval.model.main.Result
import com.example.marval.ui.base.CommonVH
import com.example.marval.ui.details.DetailsActivity
import com.example.marval.utils.bindImage
import kotlinx.android.synthetic.main.search_result_item.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick


private const val TAG = "MainActivityAdapter"

class SearchAdapter(var listResults: List<Result>) : RecyclerView.Adapter<CommonVH>() {
    var searched_text = ""
    var filteredList: MutableList<Result> = arrayListOf()

    init {
        filteredList.addAll(listResults)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH {
        return CommonVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        with(holder.itemView) {
            character_image.bindImage(
                filteredList.get(position).thumbnail.path + "." + filteredList.get(
                    position
                ).thumbnail.extension
            )
            this.onClick {
                context.startActivity(
                    DetailsActivity.getIntent(
                        context,
                        filteredList.get(position)
                    )
                )
            }


            if (!searched_text.equals("")) {
                character_name.setText(
                    filteredList.get(position).name,
                    TextView.BufferType.SPANNABLE
                )
                val mySpannable = character_name.text as Spannable
                val back = BackgroundColorSpan(resources.getColor(R.color.selected_character))
                var index: Int = filteredList.get(position).name.indexOf(searched_text.get(0))
                mySpannable.setSpan(
                    back,
                    1,
                    searched_text.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )
                if (index >= 0) {
                    var last:Int = searched_text.length-1
                    mySpannable.setSpan(
                        back,
                        index,
                        last,
                        Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                }
                // var index: Int = filteredList.get(position).name.indexOf(searched_text)
                // val sb = SpannableStringBuilder(filteredList.get(position).name)
//                if (index > 0) {
//                    val fcs =
//                        BackgroundColorSpan(this.context.resources.getColor(R.color.selected_character)) //specify color here
//                    sb.setSpan(
//                        fcs,
//                        index,
//                        index + searched_text.length - 1,
//                        Spannable.SPAN_INCLUSIVE_INCLUSIVE
//                    )
////                index = SpannableStringBuilder(filteredList.get(position).name).indexOf(
////                    searched_text,
////                    index + 1
////                )
//                    character_name.text = sb
//                }


            } else {
                character_name.text = filteredList.get(position).name
            }

        }
    }

    fun filter(text: String) {
        this.searched_text = text
        filteredList.clear()
        if (text.toString().trim().isEmpty()) {
            filteredList.addAll(listResults)
            searched_text = ""
        } else {
            searched_text = text.toLowerCase()
            for (item in listResults) {
                if (item.name.toLowerCase().contains(text)
                ) {
                    filteredList.add(item)
                }
            }
        }
        notifyDataSetChanged()
    }

//    fun addToList(added_list: List<Result>) {
//        var old = list.size
//        var new = added_list.size - 1
//        this.list.addAll(added_list)
//        notifyItemRangeInserted(old, new)
//    }


    override fun getItemCount(): Int {
        return filteredList.size
    }
}
