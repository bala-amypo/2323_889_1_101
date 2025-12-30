package com.example.demo.util;

import java.time.LocalDate;

public class DateUtil {
    public static boolean isFuture(LocalDate d) { 
        return d != null && d.isAfter(LocalDate.now()); 
    }
}