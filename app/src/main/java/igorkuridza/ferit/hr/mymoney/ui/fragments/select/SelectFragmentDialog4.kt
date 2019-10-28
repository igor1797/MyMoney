package igorkuridza.ferit.hr.mymoney.ui.fragments.select

import android.os.Bundle
import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.TYPE_ALL_TIME
import igorkuridza.ferit.hr.mymoney.common.TYPE_CURRENT_MONTH
import igorkuridza.ferit.hr.mymoney.common.TYPE_CURRENT_YEAR
import igorkuridza.ferit.hr.mymoney.common.TYPE_TODAY
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragmentDialog
import kotlinx.android.synthetic.main.fragment_dialog_select4.*

class SelectFragmentDialog4: BaseFragmentDialog() {

    private var selectListener: SelectListener? = null

    override fun getLayoutResourceId(): Int = R.layout.fragment_dialog_select4

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewAllTime.setOnClickListener {
            selectListener?.onSelect(TYPE_ALL_TIME)
            dismiss()
        }
        viewChoseDay.setOnClickListener {
            selectListener?.onSelect(TYPE_TODAY)
            dismiss()
        }
        viewChoseMonth.setOnClickListener {
            selectListener?.onSelect(TYPE_CURRENT_MONTH)
            dismiss()
        }
        viewChoseYear.setOnClickListener {
            selectListener?.onSelect(TYPE_CURRENT_YEAR)
            dismiss()
        }
    }

    fun setSelectListener(selectListener: SelectListener){
        this.selectListener = selectListener
    }

    companion object{
        fun newInstance(): SelectFragmentDialog4 = SelectFragmentDialog4()
    }
}