package igorkuridza.ferit.hr.mymoney.helpers.iterator

import igorkuridza.ferit.hr.mymoney.helpers.iterator.collection.IAbstractCollection

class Iterator(private val data: IAbstractCollection):
    IAbstractIterator {

    private var current: Int = 0

    override fun first(): Any {
            return data.getItem(0)
    }

    override fun isDone(): Boolean {
        return current >= data.count()
    }

    override fun next(): Any {
        current++
        return if(!isDone())
            data.getItem(current)
        else {
            current = 0
            data.getItem(current)
        }
    }

    override fun currentItem(): Any {
        return data.getItem(current)
    }
}