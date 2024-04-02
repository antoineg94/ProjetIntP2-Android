package com.example.projetintp2_android.Classes;

import androidx.room.TypeConverter;

import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CustomTypeConverters {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @TypeConverter
    public static LocalTime toLocalTime(Long value) {
        return (value == null) ? null : LocalTime.parse(value.toString(), formatter);
    }

    @TypeConverter
    public static Long toLong(LocalTime value) {
        return (value == null) ? null : Long.parseLong(value.format(formatter));
    }
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Time toTime(Long time) {
        return time == null ? null : new Time(time);
    }

    @TypeConverter
    public static Long toTimeMillis(Time time) {
        return time == null ? null : time.getTime();
    }
}
