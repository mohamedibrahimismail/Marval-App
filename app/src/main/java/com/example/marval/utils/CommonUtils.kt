package  com.example.marval.utils


import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marval.R
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        Glide.with(this.context)
            .load(imgUrl)
            .apply(
                RequestOptions()
                    //.placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)

    }
}

