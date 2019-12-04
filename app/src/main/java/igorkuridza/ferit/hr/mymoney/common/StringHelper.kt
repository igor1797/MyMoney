package igorkuridza.ferit.hr.mymoney.common

fun joinStrings(string1: String, string2: String): String{
    return "$string1 $string2"
}

fun getAmountString(amount: Float): String{
    return when {
        amount < 0F -> "-$amount kn"
        else -> "$amount kn"
    }
}