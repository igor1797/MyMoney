package igorkuridza.ferit.hr.mymoney.common

import android.content.Context
import androidx.core.content.ContextCompat

fun getColorByRes(context: Context, colorRes: Int): Int{
    return ContextCompat.getColor(context, colorRes)
}