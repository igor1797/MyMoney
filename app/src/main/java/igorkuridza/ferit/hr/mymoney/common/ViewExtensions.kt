package igorkuridza.ferit.hr.mymoney.common

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.setViewBackgroundColor(colorRes: Int){
    this.setBackgroundColor(ContextCompat.getColor(context,colorRes))
}

fun TextView.setTextViewTextColor(colorRes: Int){
    this.setTextColor(ContextCompat.getColor(context,colorRes))
}

fun ImageView.setColor(colorRes: Int){
    val drawable = this.drawable
    val wrapDrawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(wrapDrawable, ContextCompat.getColor(context,colorRes))
}