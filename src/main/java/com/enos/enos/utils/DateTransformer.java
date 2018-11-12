package com.enos.enos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransformer {

    public static String parseDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        return sdf.format(date);
    }
}