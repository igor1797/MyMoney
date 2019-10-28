package igorkuridza.ferit.hr.mymoney.di

import igorkuridza.ferit.hr.mymoney.helpers.iterator.collection.IteratorData
import igorkuridza.ferit.hr.mymoney.helpers.iterator.collection.IAbstractCollection
import igorkuridza.ferit.hr.mymoney.helpers.iterator.IAbstractIterator
import igorkuridza.ferit.hr.mymoney.helpers.iterator.Iterator
import org.koin.dsl.module

val iteratorModule = module {
    factory <IAbstractCollection> { IteratorData() }
    factory <IAbstractIterator> { Iterator(get()) }
}