package igorkuridza.ferit.hr.mymoney.helpers.iterator.collection

import igorkuridza.ferit.hr.mymoney.helpers.iterator.Iterator

interface IAbstractCollection {
    fun setData(data: List<Any>)
    fun createIterator(): Iterator
    fun getItem(index: Int): Any
    fun count(): Int
}