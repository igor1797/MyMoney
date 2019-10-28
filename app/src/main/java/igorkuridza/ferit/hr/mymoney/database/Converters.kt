package igorkuridza.ferit.hr.mymoney.database

import androidx.room.TypeConverter
import igorkuridza.ferit.hr.mymoney.common.convertDate
import igorkuridza.ferit.hr.mymoney.common.convertDateToMilliseconds
import igorkuridza.ferit.hr.mymoney.common.convertMilliSecondsToDate
import igorkuridza.ferit.hr.mymoney.model.CategoryColor
import igorkuridza.ferit.hr.mymoney.model.recyclerview.Date
import igorkuridza.ferit.hr.mymoney.model.TypeOfCategory

class Converters {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromCategoryColor(categoryColor: CategoryColor): String{
            return categoryColor.name
        }

        @TypeConverter
        @JvmStatic
        fun toCategoryColor(categoryColor: String): CategoryColor{
            return CategoryColor.valueOf(categoryColor)
        }

        @TypeConverter
        @JvmStatic
        fun fromTypeOfCategory(typeOfCategory: TypeOfCategory): String{
            return typeOfCategory.name
        }

        @TypeConverter
        @JvmStatic
        fun toTypeOfCategory(typeOfCategory: String): TypeOfCategory{
            return TypeOfCategory.valueOf(typeOfCategory)
        }

        @TypeConverter
        @JvmStatic
        fun fromDate(date: Date): Long{
            return convertDateToMilliseconds(date.fullDate)
        }

        @TypeConverter
        @JvmStatic
        fun toDate(time: Long): Date {
            val stringDate = convertMilliSecondsToDate(time)
            return convertDate(stringDate)
        }
    }
}