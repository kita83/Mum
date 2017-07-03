package com.app.strkita.mum;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.R.attr.format;

/**
 * 星座判定を行うクラス
 * Created by strkita on 2017/07/03.
 */

public class Constellation {
    private DateFormat format = new SimpleDateFormat("yyyy/M/d");
    private String name;
    private String from;
    private String to;

    public Constellation(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public boolean between(Date date) {
        boolean between = false;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);

        try {
            Date dateFrom = format.parse(String.valueOf(year) + "/" + from);
            Date dateTo = format.parse(String.valueOf(year) + "/" + to);
            between = !dateFrom.after(date) && !dateTo.before(date);
        } catch (ParseException e) {

        }

        return between;
    }

    public final String getName() {
        return name;
    }

}
