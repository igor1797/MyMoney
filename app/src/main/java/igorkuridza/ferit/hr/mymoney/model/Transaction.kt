package igorkuridza.ferit.hr.mymoney.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var transactionId: Long? = null,
    @Embedded
    val category: Category,
    val amountOfMoney: Float,
    @Embedded
    val account: Account,
    val note: String,
    val timeOfTransaction: Long,
    val day: Int,
    val month: String,
    val year: Int,

    val fullDate: String = "$day $month $year",
    val monthYearDate: String = "$month $year",
    val dayMonthDate: String = "$day $month"
)