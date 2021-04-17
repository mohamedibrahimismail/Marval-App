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

class SearchAdapter(var searched_text: String = "", var listResults: List<Result>) :
    RecyclerView.Adapter<CommonVH>() {
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

                mySpannable.setSpan(
                    back,
                    0,
                    searched_text.length,
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE
                )


            } else {
                character_name.text = filteredList.get(position).name
            }

        }
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }
}
