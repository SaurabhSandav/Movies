package com.redridgeapps.movies.util;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class IntegerListStringConverter {

    private static final String DELIMITER = ",";

    @TypeConverter
    public static List<Integer> toList(String str) {
        ArrayList<Integer> list = new ArrayList<>();

        for (String s : TextUtils.split(str, DELIMITER)) {
            list.add(Integer.valueOf(s));
        }

        return list;
    }

    @TypeConverter
    public static String toString(List<Integer> list) {
        return TextUtils.join(DELIMITER, list);
    }
}
