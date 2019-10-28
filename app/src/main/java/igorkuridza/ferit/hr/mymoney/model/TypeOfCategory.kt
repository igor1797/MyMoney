package igorkuridza.ferit.hr.mymoney.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class TypeOfCategory: Parcelable {
    INCOMES, EXPENSES
}