package igorkuridza.ferit.hr.mymoney.model

import igorkuridza.ferit.hr.mymoney.common.getCurrentYear

object Months {
    fun getAll(): List<String>{
        val currentYear = getCurrentYear()
        return arrayListOf("January $currentYear","February $currentYear","March $currentYear",
            "April $currentYear","May $currentYear","June $currentYear",
            "July $currentYear", "August $currentYear", "September $currentYear",
            "October $currentYear","November $currentYear", "December $currentYear")
    }
}