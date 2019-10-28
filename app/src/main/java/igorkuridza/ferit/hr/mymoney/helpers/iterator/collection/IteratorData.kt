package igorkuridza.ferit.hr.mymoney.helpers.iterator.collection

import igorkuridza.ferit.hr.mymoney.helpers.iterator.Iterator

class IteratorData: IAbstractCollection {

    private lateinit var data: List<Any>

    override fun setData(data: List<Any>){
        this.data = data
    }

    override fun createIterator(): Iterator {
        return Iterator(this)
    }

    override fun getItem(index: Int): Any {
        return data[index]
    }

    override fun count() = data.size
}