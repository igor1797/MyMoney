package igorkuridza.ferit.hr.mymoney.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long? = null,
    @ColumnInfo(name = "categoryName")
    val name: String,
    val color: CategoryColor,
    val typeOfCategory: TypeOfCategory
): Parcelable