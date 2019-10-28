package igorkuridza.ferit.hr.mymoney.model.recyclerview

data class Date(
    val day: Int,
    val month: String,
    val year: Int,
    val fullDate: String = "$day $month $year"
){
    override fun toString(): String {
        return "$day $month $year"
    }
}