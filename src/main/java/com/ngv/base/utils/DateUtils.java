package com.ngv.base.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class DateUtils {

  public static Date convertStringToDate(String dateFormatPattern, String value) {
    try {
      if (!isValid(value, dateFormatPattern)) {
        return null;
      }
      if (StringUtils.isEmpty(dateFormatPattern)) {
        return null;
      }
      return new SimpleDateFormat(dateFormatPattern).parse(value);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static LocalDate convertStringToLocalDate(String dateFormatPattern, String value) {
    try {
      if (!isValid(value, dateFormatPattern)) {
        return null;
      }
      if (StringUtils.isEmpty(dateFormatPattern)) {
        return null;
      }
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
      return LocalDate.parse(value, formatter);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static LocalDateTime convertStringToLocalDateTime(String dateFormatPattern, String value) {
    try {
      if (!isValid(value, dateFormatPattern)) {
        return null;
      }
      if (StringUtils.isEmpty(dateFormatPattern)) {
        return null;
      }
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
      return LocalDateTime.parse(value, formatter);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static String convertPatterDate(String sourcePattern, String desPattern, String value) {
    try {
      if (StringUtils.isEmpty(sourcePattern) || StringUtils.isEmpty(desPattern)) {
        return null;
      }
      Date date = new SimpleDateFormat(sourcePattern).parse(value);
      DateFormat df = new SimpleDateFormat(desPattern);
      return df.format(date);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static boolean compareDate(Date firstDate, Date secondDate) {

    return firstDate.before(secondDate);
  }

  public static boolean checkIsToday(String dateFormatPattern, String date) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
      LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
      return dateTime.toLocalDate().equals(LocalDate.now());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return true;
  }

  public static boolean isStartDateBeforeEndDate(String fDate, String tDate, String format) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      Date date1 = sdf.parse(fDate);
      Date date2 = sdf.parse(tDate);
      if (date1.after(date2)) {
        return false;
      }
//            if (date1.before(date2) || date1.equals(date2)) {
//                return true;
//            }
    } catch (ParseException ex) {
      ex.printStackTrace();
    }
    return true;
  }

  public static boolean isInDaysRange(String fDate, String tDate, int daysRange, String fomat) {
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern(fomat);
    LocalDate date1 = LocalDate.parse(fDate, sdf);
    LocalDate date2 = LocalDate.parse(tDate, sdf);
    long daysBetween = ChronoUnit.DAYS.between(date1, date2);
    if (daysBetween > daysRange) {
      return false;
    }
    return true;
  }

  public static String convertDateToString(String dateFormatPattern, Date value) {
    try {
      if (StringUtils.isEmpty(dateFormatPattern)) {
        return null;
      }
      DateFormat df = new SimpleDateFormat(dateFormatPattern);
      return df.format(value);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static String convertLocalDateToString(String dateFormatPattern, LocalDate value) {
    try {
      if (StringUtils.isEmpty(dateFormatPattern)) {
        return null;
      }
      DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormatPattern);
      return df.format(value);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static String convertLocalDateTimeToString(String dateFormatPattern, LocalDateTime value) {
    try {
      if (StringUtils.isEmpty(dateFormatPattern)) {
        return null;
      }
      DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormatPattern);
      return df.format(value);
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  public static long calculateDateString(String fDate, String tDate, String fomat) {
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern(fomat);
    LocalDate date1 = LocalDate.parse(fDate, sdf);
    LocalDate date2 = LocalDate.parse(tDate, sdf);
    return ChronoUnit.DAYS.between(date1, date2);
  }

  public static Date trim(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.MILLISECOND, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.HOUR, 0);

    return calendar.getTime();
  }

  public static LocalDateTime getLocalNow() {
    return LocalDateTime.now();
  }

  public static LocalDate getCurrentDate() {
    return LocalDate.now();
  }

  public static String getNumsYear(LocalDate from, LocalDate to) {
    Period period = Period.between(from, to);
    int year = period.getYears();
    int month = period.getMonths();
    String result = "";
    if (year > 0) {
      result = String.format("%.1f năm", year + (double) month / 12);
    } else {
      result = String.format("%d tháng", month);
    }
    return result;
  }

  public static long getNumsMonth(LocalDate from, LocalDate to) {
    return ChronoUnit.MONTHS.between(from, to);
  }

  public static boolean isValid(String dateStr, String dateFormat) {
    DateFormat sdf = new SimpleDateFormat(dateFormat);
    sdf.setLenient(false);
    try {
      sdf.parse(dateStr);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }

  public static List<LocalDate> getDatesBetweenDays(LocalDate startDate, LocalDate endDate) {
    return startDate.datesUntil(endDate)
        .collect(Collectors.toList());
  }
}
