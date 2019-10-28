package igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.view

import android.os.Bundle
import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.setColor
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.ChangeAccountListener
import igorkuridza.ferit.hr.mymoney.ui.fragments.changeaccount.presenter.ChangeAccountFragmentDialogPresenter
import kotlinx.android.synthetic.main.fragment_change_account_fragment_dialog.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class ChangeAccountFragmentDialog : BaseFragmentDialog(), ChangeAccountFragmentDialogView{

    private val presenter: ChangeAccountFragmentDialogPresenter by inject { parametersOf(this) }
    private lateinit var account: Account
    private var changeAccountListener: ChangeAccountListener? = null

    override fun getLayoutResourceId(): Int = R.layout.fragment_change_account_fragment_dialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun setupUi() {
        account = arguments?.getParcelable<Account>("ACCOUNT") as Account
        setAll()
        cancel.setOnClickListener { dismiss() }
        ok.setOnClickListener{
            saveChanges()
            changeAccountListener?.onChangeAccountListener()
            dismiss()
        }
    }

    private fun setAll(){
        accountName.append(account.name)
        accountColor.setColor(account.color)
        accountAmount.append(account.amountOfMoney.toString())
    }

    private fun saveChanges(){
        val accountName: String = accountName.text.toString()
        val accountAmount: Float = accountAmount.text.toString().toFloat()
        presenter.saveChanges(accountAmount, account, accountName)
    }

    fun setChangeAccountListener(changeAccountListener: ChangeAccountListener){
        this.changeAccountListener = changeAccountListener
    }

    companion object {
        fun newInstance(account: Account): ChangeAccountFragmentDialog {
            val bundle = Bundle().apply {
                putParcelable("ACCOUNT", account)
            }
            return ChangeAccountFragmentDialog()
                .apply { arguments = bundle }
        }
    }
}
