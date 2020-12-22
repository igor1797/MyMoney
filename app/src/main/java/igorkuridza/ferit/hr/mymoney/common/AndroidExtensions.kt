package igorkuridza.ferit.hr.mymoney.common

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import igorkuridza.ferit.hr.mymoney.R

fun FragmentActivity.showFragment(containerId: Int, fragment: Fragment, shouldAddToBackStack: Boolean = false, tag: String = ""){
    supportFragmentManager.beginTransaction().apply {
        if (shouldAddToBackStack) {
            addToBackStack(tag)
        }
    }.replace(containerId, fragment).commitAllowingStateLoss()
}

fun RecyclerView.setDivider(){
    val divider = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
    divider.setDrawable(ContextCompat.getDrawable(this.context, R.drawable.recyclerview_divider)!!)
    this.addItemDecoration(divider)
}

