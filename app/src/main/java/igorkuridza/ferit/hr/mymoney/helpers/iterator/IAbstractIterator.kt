package igorkuridza.ferit.hr.mymoney.helpers.iterator


interface IAbstractIterator {
    fun first(): Any
    fun next(): Any
    fun isDone(): Any
    fun currentItem(): Any
}