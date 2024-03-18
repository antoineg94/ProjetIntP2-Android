package com.example.projetintp2_android.Classes;

import androidx.room.TypeConverter;

import java.sql.Time;
import java.util.Date;

public class CustomTypeConverters {
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
