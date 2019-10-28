package igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.view

import android.os.Bundle
import android.view.View
import igorkuridza.ferit.hr.mymoney.R
import igorkuridza.ferit.hr.mymoney.common.*
import igorkuridza.ferit.hr.mymoney.model.Account
import igorkuridza.ferit.hr.mymoney.model.Category
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory
import igorkuridza.ferit.hr.mymoney.ui.base.BaseFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.AddTransactionListener
import igorkuridza.ferit.hr.mymoney.ui.fragments.addtransaction.presenter.AddTransactionFragmentDialogPresenter
import igorkuridza.ferit.hr.mymoney.ui.fragments.datepicker.DatePickerFragmentDialog
import igorkuridza.ferit.hr.mymoney.ui.fragments.datepicker.DatePickerListener
import kotlinx.android.synthetic.main.fragment_add_transaction.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AddTransactionFragmentDialog: BaseFragmentDialog(), DatePickerListener, AddTransactionFragmentDialogView{

    private val presenter: AddTransactionFragmentDialogPresenter by inject { parametersOf(this) }
    private var addTransactionListener: AddTransactionListener? = null
    private lateinit var category: Category
    private lateinit var date: String

    override fun getLayoutResourceId(): Int = R.layout.fragment_add_transaction

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onViewCreated()
    }

    override fun initUi() {
        category = arguments?.get(CATEGORY_KEY) as Category
        date = arguments?.getString(DATE_KEY) as String
        tvDate.text = date
        ivShowCalendar.setOnClickListener {showCalendarDialog()}
        presenter.apply {
            setIterator()
            getFirstAccount()
        }
        setButtonSaveOnClickListener()
        setTextViews()
        setTextViewsOnClickListener()
    }

    private fun setButtonSaveOnClickListener(){
        btnSave.setOnClickListener {
            presenter.saveTransaction()
            addTransactionListener?.onAddTransaction()
            dismiss()
        }
    }

    private fun isTypeOfCategoryExpenses(): Boolean{
        if (category.typeOfCategory == TypeOfCategory.EXPENSES) return true
        return false
    }

    private fun setTextViewsOnClickListener(){
        if(isTypeOfCategoryExpenses()){
            tvName1.setOnClickListener {
                presenter.getNextAccount()
            }
        }
        else{
            tvName2.setOnClickListener {
                presenter.getNextAccount()
            }
        }
    }

    override fun onGetFirstAccount(account: Account) {
        if(isTypeOfCategoryExpenses()){
            tvName1.text = account.name
            viewTo.setImageResource(category.color.getColor())
            viewFrom.setImageResource(account.color)
        }else{
            tvName2.text = account.name
            viewFrom.setImageResource(category.color.getColor())
            viewTo.setImageResource(account.color)
        }
    }

    override fun onGetNextAccount(account: Account) {
        if(isTypeOfCategoryExpenses()){
            tvName1.text = account.name
            viewFrom.setImageResource(account.color)
        }else{
            tvName2.text = account.name
            viewTo.setImageResource(account.color)
        }
    }

    private fun showCalendarDialog() {
        val fragmentManager = fragmentManager
        val calendarDialog = DatePickerFragmentDialog.newInstance()
        calendarDialog.setDatePickerListener(this)
        calendarDialog.show(fragmentManager!!, "")
    }

    private fun setTextViews() {
        if (isTypeOfCategoryExpenses()) {
            tvFrom.text = getText(R.string.textFromAccount)
            tvTo.text = getText(R.string.textForCategory)
            tvName2.text = category.name
        } else {
            tvFrom.text = getString(R.string.textFromCategory)
            tvTo.text = getString(R.string.textForAccount)
            tvName1.text = category.name
        }
    }

    private fun getAmountOfTransactionFromEditText(): Float{
        val amount = etAmountOfTransaction.text.toString()
        return if (amount.isNotEmpty()) amount.toFloat()
        else 0F
    }

    private fun isAmountOfMoneyEmptyOr0(amountOfMoney: Float): Boolean{
        if(amountOfMoney.toString().isEmpty() || amountOfMoney == 0F) return true
        return false
    }

    private fun getNoteFromEditText(): String{
        return etNote.text.toString()
    }

    override fun onSaveTransaction(account: Account){
        val amountOfMoney = getAmountOfTransactionFromEditText()
        if(isAmountOfMoneyEmptyOr0(amountOfMoney)){
            dismiss()
        }
        else {
            val note = getNoteFromEditText()
            presenter.updateAccountAmount(account, amountOfMoney, isTypeOfCategoryExpenses())
            val transaction = convertToTransaction(category,date,amountOfMoney,account, note)
            presenter.addTransactionToDb(transaction)
        }
    }

    fun setAddTransactionListener(addTransactionListener: AddTransactionListener){
        this.addTransactionListener = addTransactionListener
    }

    override fun onDatePicked(date: String) {
        this.date = date
        tvDate.text = date
    }

    companion object{
        fun newInstance(category: Category, date: String): AddTransactionFragmentDialog {
            val bundle = Bundle().apply {
                putParcelable(CATEGORY_KEY, category)
                putString(DATE_KEY, date)
            }
            return  AddTransactionFragmentDialog()
                .apply { arguments = bundle }
        }
    }
}