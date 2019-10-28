package igorkuridza.ferit.hr.mymoney.di

import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRepository
import igorkuridza.ferit.hr.mymoney.persistance.account_repository.AccountRoomRepository
import igorkuridza.ferit.hr.mymoney.persistance.category_repository.CategoryRepository
import igorkuridza.ferit.hr.mymoney.persistance.category_repository.CategoryRoomRepository
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRepository
import igorkuridza.ferit.hr.mymoney.persistance.transaction_repository.TransactionRoomRepository
import igorkuridza.ferit.hr.mymoney.prefs.provideSharedPrefs
import org.koin.dsl.module

val repositoryModule = module {

    factory<AccountRepository> { AccountRoomRepository() }
    factory<TransactionRepository>{ TransactionRoomRepository() }
    factory<CategoryRepository> { CategoryRoomRepository() }
    factory { provideSharedPrefs() }
}