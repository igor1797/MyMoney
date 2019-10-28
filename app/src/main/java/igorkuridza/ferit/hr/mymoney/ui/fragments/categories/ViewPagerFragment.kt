package igorkuridza.ferit.hr.mymoney.ui.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.getTodayDate
import igorkuridza.ferit.hr.mymoney.helpers.DateChangeViewModel
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.categories.view.CategoriesFragment
import igorkuridza.ferit.hr.mymoney.ui.fragments.datepicker.DatePickerFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.datepicker.DatePickerListener
import kotlinx.android.synthetic.main.fragment_switch_categories.*

class ViewPagerFragment: BaseFragment(), DatePickerListener{

    private val pagerAdapter by lazy { ViewPagerAdapter(childFragmentManager) }
    private var model: DateChangeViewModel? = null

    override fun getLayoutResourceId() = R.layout.fragment_switch_categories

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewPager()

        model = ViewModelProviders.of(activity!!).get(DateChangeViewModel::class.java)
        tvDate.text = getTodayDate()

        ivShowCalendar.setOnClickListener {
            val calendarDialog = DatePickerFragmentDialog.newInstance()
            calendarDialog.setDatePickerListener(this)
            calendarDialog.show(fragmentManager!!, "")
        }
    }

    override fun onDatePicked(date: String) {
        tvDate.text = date
        model!!.setDate(date)
    }

    private fun setUpViewPager(){
        val fragments = mutableListOf<Fragment>(
            CategoriesFragment.newInstance(TypeOfCategory.EXPENSES),
            CategoriesFragment.newInstance(TypeOfCategory.INCOMES)
        )
        pagerAdapter.setFragments(fragments)
        viewPager.adapter = pagerAdapter
    }

    companion object{
        fun newInstance(): ViewPagerFragment =
            ViewPagerFragment()
    }
}
