package igorkuridza.ferit.hr.mymoney.helpers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChooseYearViewModel: ViewModel() {

    val year = MutableLiveData<Int>()

    fun setYear(year: Int){
        this.year.value = year
    }
}

