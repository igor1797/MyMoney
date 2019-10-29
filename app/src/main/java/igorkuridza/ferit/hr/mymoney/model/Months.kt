package igorkuridza.ferit.hr.mymoney.model

import igorkuridza.ferit.hr.mymoney.common.getCurrentYear

object Months {
    fun getAll(): List<String>{
        val currentYear = getCurrentYear()
        return arrayListOf("siječnja $currentYear","veljače $currentYear","ožujka $currentYear",
            "travnja $currentYear","svibnja $currentYear","lipnja $currentYear",
            "srpnja $currentYear", "kolovoza $currentYear", "rujna $currentYear",
            "listopada $currentYear","studenoga $currentYear", "prosinca $currentYear")
    }
}