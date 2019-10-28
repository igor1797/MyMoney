package igorkuridza.ferit.hr.mymoney.model

import androidx.annotation.ColorRes
import igorkuridza.ferit.hr.mymoney.R

enum class CategoryColor(@ColorRes private val colorRes: Int) {
    FIRST(R.color.firstCategoryColor),
    SECOND(R.color.secondCategoryColor),
    THIRD(R.color.thirdCategoryColor),
    FOURTH(R.color.fourthCategoryColor),
    FIFTH(R.color.fifthCategoryColor),
    SIXTH(R.color.sixthCategoryColor),
    SEVENTH(R.color.seventhCategoryColor),
    EIGHT(R.color.eighthCategoryColor),
    NINTH(R.color.ninthCategoryColor);

    fun getColor(): Int = colorRes
}