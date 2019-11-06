package com.ujiuye.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String DateToString(Date date){
        return sdf.format(date);
    }
    public Date StringToDate(String s) throws ParseException {
        return sdf.parse(s);
    }
}
