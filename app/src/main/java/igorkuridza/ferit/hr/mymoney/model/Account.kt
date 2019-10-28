package igorkuridza.ferit.hr.mymoney.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    @ColumnInfo(name = "accountName")
    val name: String,
    @ColumnInfo(name = "accountAmountOfMoney")
    val amountOfMoney: Float,
    @ColumnInfo(name = "accountColor")
    val color: Int
): Parcelable