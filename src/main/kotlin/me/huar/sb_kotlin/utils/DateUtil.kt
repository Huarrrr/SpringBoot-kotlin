package me.huar.sb_kotlin.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Description: 时间工具
 */
object DateUtil {
    /**
     * 获取当前年份
     */
    val currentYear: String
        get() = getFormatCurrentTime("yyyy")

    /**
     * 获取上一年的年份
     */
    val beforeYear: String
        get() {
            val currentYear =
                    getFormatCurrentTime("yyyy")
            val beforeYear = currentYear.toInt() - 1
            return "" + beforeYear
        }

    fun getNextYear(date: String?, format: String?, i: Int): String {
        var dealDate = ""
        try {
            val calendar = Calendar.getInstance()
            val dFormate = SimpleDateFormat(format!!, Locale.getDefault())
            calendar.time = dFormate.parse(date)
            calendar.add(Calendar.YEAR, +i)
            dealDate = dFormate.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dealDate
    }
    //    public static String getNextYear(String year) {
//        String currentYear = getFormatCurrentTime("yyyy");
//        int beforeYear = Integer.parseInt(currentYear) - 1;
//        return "" + beforeYear;
//    }
    /**
     * 获取当前月份
     */
    val currentMonth: String
        get() = getFormatCurrentTime("MM")

    /**
     * 获取当前是几号
     */
    val currentDay: String
        get() = getFormatCurrentTime("dd")

    /**
     * @return 当前日期是星期几
     */
    fun getWeekOfDate(dt: Date?): String? {
        val weekDays =
                arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        val cal = Calendar.getInstance()
        cal.time = dt
        var w = cal[Calendar.DAY_OF_WEEK] - 1
        if (w < 0) w = 0
        return weekDays[w]
    }

    /**
     * 获取当前日期时间
     *
     * @param format 日期格式
     */
    fun getCurrentDate(format: String?): String {
        return getFormatDateTime(
                Date(),
                format
        )
    }

    private fun getFormatCurrentTime(format: String?): String {
        return getFormatDateTime(
                Date(),
                format
        )
    }

    private fun getFormatDateTime(date: Date?, format: String?): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        return sdf.format(date)
    }

    /**
     * 日期格式之间的转换
     *
     * @param date   日期
     * @param format 想要转换的日期格式
     */
    fun getFormatResult(date: String?, format: String): String {
        var dealDate = ""
        var sdf1: SimpleDateFormat? = null
        var sdf2: SimpleDateFormat? = null
        if ("yyyy-MM-dd" == format) {
            sdf1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            sdf2 = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        } else if ("yyyy-MM" == format) {
            sdf1 = SimpleDateFormat("yyyy-MM", Locale.getDefault())
            sdf2 = SimpleDateFormat("yyyyMM", Locale.getDefault())
        } else if ("yyyyMMdd" == format) {
            sdf1 = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            sdf2 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        } else if ("yyyyMM" == format) {
            sdf1 = SimpleDateFormat("yyyyMM", Locale.getDefault())
            sdf2 = SimpleDateFormat("yyyy-MM", Locale.getDefault())
        }
        try {
            if (sdf1 != null) {
                dealDate = sdf1.format(sdf2!!.parse(date))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dealDate
    }

    /**
     * 时间戳转日期格式
     *
     * @param milSecond
     * @param pattern
     * @return
     */
    fun getDateToString(milSecond: Long, pattern: String?): String {
        val date = Date(milSecond)
        val format = SimpleDateFormat(pattern!!)
        return format.format(date)
    }

    fun timeStamp2Date(seconds: String?, format: String?): String? {
        var format1 = format
        if (seconds == null || seconds.isEmpty() || seconds == "null") {
            return ""
        }
        if (format1 == null || format1.isEmpty()) {
            format1 = "yyyy-MM-dd HH:mm:ss"
        }
        val sdf = SimpleDateFormat(format1)
        return sdf.format(Date(java.lang.Long.valueOf(seconds + "000")))
    }


    /**
     * 获取某年最后一天日期
     *
     * @param year 年份
     * @return Date
     */
    fun getCurrYearLast(year: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar[Calendar.YEAR] = year
        calendar.roll(Calendar.DAY_OF_YEAR, -1)
        return calendar.time
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    fun getCurrYearFirst(year: Int, month: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.clear()
        calendar[Calendar.YEAR] = year
        calendar.roll(Calendar.DAY_OF_MONTH, month - 1)
        return calendar.time
    }

    /**
     * 将日期转换成Date类型
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    fun getDateFromString(dateStr: String?, pattern: String?): Date? {
        val sdf = SimpleDateFormat(pattern!!, Locale.getDefault())
        var resDate: Date? = null
        try {
            resDate = sdf.parse(dateStr)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return resDate
    }

    /**
     * 判断开始日期是否大于结束日期
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param pattern   日期格式
     */
    fun compareToDate(startDate: String?, endDate: String?, pattern: String?): Boolean {
        val sdf = SimpleDateFormat(pattern!!, Locale.getDefault())
        var date1: Date? = null
        var date2: Date? = null
        try {
            date1 = sdf.parse(startDate)
            date2 = sdf.parse(endDate)
            return date1 >= date2
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return false
    }

    /**
     * 获取前几天的日期
     */
    fun getBeforeDate(date: String?, pattern: String?, i: Int): String {
        var dealDate = ""
        try {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat(pattern!!, Locale.getDefault())
            calendar.time = sdf.parse(date)
            calendar.add(Calendar.DATE, -i)
            dealDate = sdf.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dealDate
    }

    /**
     * 获取后几天
     */
    fun getNextDate(date: String?, pattern: String?, i: Int): String {
        var dealDate = ""
        try {
            val calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat(pattern!!, Locale.getDefault())
            calendar.time = sdf.parse(date)
            calendar.add(Calendar.DATE, +i)
            dealDate = sdf.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dealDate
    }

    /**
     * 获取前几个月
     */
    fun getBeforeMon(date: String?, formate: String?, i: Int): String {
        var dealDate = ""
        try {
            val calendar = Calendar.getInstance()
            val dFormate = SimpleDateFormat(formate!!, Locale.getDefault())
            calendar.time = dFormate.parse(date)
            calendar.add(Calendar.MONTH, -i)
            dealDate = dFormate.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dealDate
    }

    /**
     * 获取后几个月
     */
    fun getNextMon(date: String?, formate: String?, i: Int): String {
        var dealDate = ""
        try {
            val calendar = Calendar.getInstance()
            val dFormate = SimpleDateFormat(formate!!, Locale.getDefault())
            calendar.time = dFormate.parse(date)
            calendar.add(Calendar.MONTH, +i)
            dealDate = dFormate.format(calendar.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dealDate
    }

    /**
     * 获取当前日期的时间戳
     */
    fun getMillSecondsByDate(date: String, format: String?): Long {
        return if (date.contains("时间")) {
            0
        } else {
            var millseconds: Long = 0
            val simpleDateFormat = SimpleDateFormat(format!!)
            try {
                millseconds = simpleDateFormat.parse(date).time
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            millseconds
        }
    }

    /**
     * 时间戳转换日期
     *
     * @param millSecond
     * @param format
     * @return
     */
    fun getMillDate(format: String?, millSecond: Long): String {
        val date = Date(millSecond)
        val formatter = SimpleDateFormat(format!!)
        return formatter.format(date)
    }

    /**
     * 获取今年时间戳
     *
     * @return
     */
    fun yearTimeInMillis(): Long {
        val isThisYear = Calendar.getInstance()
        isThisYear.add(Calendar.YEAR, 1)
        isThisYear[Calendar.MONTH] = 0
        isThisYear[Calendar.DAY_OF_MONTH] = 1
        isThisYear[Calendar.HOUR_OF_DAY] = 0
        isThisYear[Calendar.MINUTE] = 0
        isThisYear[Calendar.SECOND] = 0
        isThisYear[Calendar.MILLISECOND] = 0
        return isThisYear.timeInMillis / 1000
    }

    /**
     * 获取指定日期所在月份开始的时间戳
     *
     * @param date 指定日期
     * @return
     */
    @Throws(ParseException::class)
    fun getMonthBegin(date: String?): Long {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val time = format.parse(date)
        val calendar = Calendar.getInstance()

        return getTimeMillis(calendar, time)
    }

    private fun getTimeMillis(calendar: Calendar?, time: Date?): Long {
        calendar!!.time = time
        //设置为1号,当前日期既为本月第一天
        calendar[Calendar.DAY_OF_MONTH] = 1
        //将小时至0
        calendar[Calendar.HOUR_OF_DAY] = 0
        //将分钟至0
        calendar[Calendar.MINUTE] = 0
        //将秒至0
        calendar[Calendar.SECOND] = 0
        //将毫秒至0
        calendar[Calendar.MILLISECOND] = 0
        // 获取本月第一天的时间戳
        return calendar.timeInMillis
    }


    /**
     * 获取x年前的时间戳
     *
     * @return x
     */
    fun getYearsAgo(x: Int): Long {
        try {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR].toString()
            val currentYear = year.toInt()
            val fiftyAgoYear = currentYear - x
            val time: Date?
            time = format.parse("$fiftyAgoYear-01-01")
            calendar.time = time
            return getTimeMillis(calendar, time)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val tenYears = 60L * 365 * 1000 * 60 * 60 * 24L
        return System.currentTimeMillis() - tenYears
    }
}