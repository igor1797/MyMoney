package igorkuridza.ferit.hr.mymoney.helpers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DateChangeViewModel: ViewModel() {

    val date = MutableLiveData<String>()

    fun setDate(date: String){
        this.date.value = date
    }
}