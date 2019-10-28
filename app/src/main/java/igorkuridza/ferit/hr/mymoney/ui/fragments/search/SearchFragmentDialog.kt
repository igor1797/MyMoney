package igorkuridza.ferit.hr.mymoney.ui.fragments.search


import android.os.Bundle
import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragmentDialog
import kotlinx.android.synthetic.main.fragment_dialog_search.*


class SearchFragmentDialog : BaseFragmentDialog() {

    private var searchListener: SearchListener? = null

    override fun getLayoutResourceId(): Int = R.layout.fragment_dialog_search

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search.setOnClickListener {
            searchListener?.onNoteSearched(getNote())
            dismiss()
        }
    }

    fun setSearchListener(listener: SearchListener){
        this.searchListener = listener
    }

    private fun getNote(): String{
        val note = searchText.text.toString()
        return if(note.isNotEmpty())
            note
        else
            ""
    }

    companion object{
        fun newInstance(): SearchFragmentDialog = SearchFragmentDialog()
    }
}
