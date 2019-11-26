package com.mvc.service.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ProductUtilsTest {

    @Test
    public void parseDate() throws ParseException {
        String date = "2019-25-05";
        Date originDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        String actual = originDate.toString();
        assertEquals("Tue Jan 05 00:00:00 EET 2021", actual);
    }
}