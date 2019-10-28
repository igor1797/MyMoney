package igorkuridza.ferit.hr.mymoney.model.recyclerview

data class MonthYearBalance(
    val month: String,
    val year: Int,
    val expensesTotalAmount: Float,
    val incomesTotalAmount: Float
)