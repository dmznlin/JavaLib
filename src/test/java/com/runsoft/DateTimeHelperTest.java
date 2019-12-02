package com.runsoft;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

/** 
* DateTimeHelper Tester. 
* 
* @author <Authors name> 
* @since <pre>11/28/2019</pre>
* @version 1.0 
*/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateTimeHelperTest { 

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
}

    private static DateTimeHelper FHelper;

    static {
        DateTimeHelper.ConfigHelper(new DateTimeForChina());
        FHelper = new DateTimeHelper(new DateTimeFormatConfig() {});
    }
/**
*
* Method: ConfigHelper(DateTimeFormatConfig nConfig)
*
*/
@Test
public void testConfigHelper() throws Exception {
//TODO: Test goes here...
    System.out.println("ConfigHelper: Init Done");
}

/** 
* 
* Method: StrToDate(String nStr) 
* 
*/ 
@Test
public void testStrToDate() throws Exception {
//TODO: Test goes here...
    Date nDate = FHelper.Str2Date("2019-11-28");
    System.out.println("StrToDate: " + FHelper.Date2Str(nDate, true));
}

/**
* 
* Method: Str2Date(String nStr) 
* 
*/ 
@Test
public void testStr2Date() throws Exception { 
//TODO: Test goes here...
    Date nDate = DateTimeHelper.Str2Date("2019-11-28");
    System.out.println("Str2Date: " + DateTimeHelper.Date2Str(nDate, true));
} 

/** 
* 
* Method: Str2Time(String nStr) 
* 
*/ 
@Test
public void testStr2Time() throws Exception { 
//TODO: Test goes here...
    Date nTime = DateTimeHelper.Str2Time("15:20:20");
    System.out.println("Str2Time: " + DateTimeHelper.Time2Str(nTime, true));
} 

/**
*
* Method: DateToStr(Date nDate, boolean nSeparator)
*
*/
@Test
public void testDateToStr() throws Exception {
//TODO: Test goes here...
    System.out.println("DateToStr: " + FHelper.DateToStr(new Date(), true));
}

/** 
* 
* Method: Date2Str(Date nDate, boolean nSeparator) 
* 
*/ 
@Test
public void testDate2Str() throws Exception { 
//TODO: Test goes here...
    System.out.println("Date2Str: " + DateTimeHelper.Date2Str(new Date(), true));
} 

/**
*
* Method: TimeToStr(Date nTime, boolean nSeparator)
*
*/
@Test
public void testTimeToStr() throws Exception {
//TODO: Test goes here...
    System.out.println("TimeToStr: " + FHelper.TimeToStr(new Date(), true));
}

/**
* 
* Method: Time2Str(Date nTime, boolean nSeparator) 
* 
*/ 
@Test
public void testTime2Str() throws Exception { 
//TODO: Test goes here...
    System.out.println("Time2Str: " + DateTimeHelper.Time2Str(new Date(), true));
} 

/**
*
* Method: StrToDateTime(String nStr)
*
*/
@Test
public void testStrToDateTime() throws Exception {
//TODO: Test goes here...
    DateTimeHelper FHelper = new DateTimeHelper(new DateTimeFormatConfig(){
        @Override
        public String forDateTime() {
            return "yyyyMMdd HH:mm:ss";
        }
    });
    Date nDate = FHelper.StrToDateTime("20191129 15:20:20");
    System.out.println("StrToDateTime: " + FHelper.DateTimeToStr(nDate));
}

/**
*
* Method: Str2DateTime(String nStr)
*
*/
@Test
public void testStr2DateTime() throws Exception {
//TODO: Test goes here...
    Date nDate = DateTimeHelper.Str2DateTime("2019-11-29 15:20:20");
    System.out.println("Str2DateTime: " + DateTimeHelper.DateTime2Str(nDate));
}

/**
*
* Method: DateTimeToStr(Date nDateTime)
*
*/
@Test
public void testDateTimeToStr() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: DateTime2Str(Date nDateTime)
*
*/
@Test
public void testDateTime2Str() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: DateToWeek(Date nDate, boolean nEnglish)
*
*/
@Test
public void testDateToWeek() throws Exception {
//TODO: Test goes here...
    System.out.println("DateToWeek: " + FHelper.DateToWeek(new Date(), false));
}

/**
*
* Method: Date2Week(Date nDate, boolean nEnglish)
*
*/
@Test
public void testDate2Week() throws Exception {
//TODO: Test goes here...
    System.out.println("Date2Week: " + DateTimeHelper.Date2Week(new Date(), false));
}

/**
*
* Method: DateToCH(Date nDate)
*
*/
@Test
public void testDateToCH() throws Exception {
//TODO: Test goes here...
    System.out.println("DateToCH: " + FHelper.DateToCH(new Date()));
}

/**
*
* Method: Date2CH(Date nDate)
*
*/
@Test
public void testDate2CH() throws Exception {
//TODO: Test goes here...
    System.out.println("Date2CH: " + DateTimeHelper.Date2CH(new Date()));
}

/**
*
* Method: TimeToCH(Date nTime)
*
*/
@Test
public void testTimeToCH() throws Exception {
//TODO: Test goes here...
    System.out.println("TimeToCH: " + FHelper.TimeToCH(new Date()));
}

/**
*
* Method: Time2CH(Date nTime)
*
*/
@Test
public void testTime2CH() throws Exception {
//TODO: Test goes here...
    System.out.println("Time2CH: " + DateTimeHelper.Time2CH(new Date()));
}

/**
*
* Method: GetDateTimeSerial(String nPrefix)
*
*/
@Test
public void testDayStart() throws Exception {
//TODO: Test goes here...
    DateTimeHelper FHelper = new DateTimeHelper(new DateTimeFormatConfig(){
        @Override
        public String forDateTime() {
            return "yyyy-MM-dd HH:mm:ss:SSS";
        }
    });
    System.out.println("DayStart: " + FHelper.DateTimeToStr(FHelper.DayStart("2019-12-01")));
}

/**
*
* Method: GetDayStart(String nStr)
*
*/
@Test
public void testGetDayStart() throws Exception {
//TODO: Test goes here...
    System.out.println("GetDayStart: " + DateTimeHelper.DateTime2Str(DateTimeHelper.GetDayStart("2019-12-01")));
}

/**
*
* Method: DayEnd(String nStr)
*
*/
@Test
public void testDayEnd() throws Exception {
//TODO: Test goes here...
    DateTimeHelper FHelper = new DateTimeHelper(new DateTimeFormatConfig(){
        @Override
        public String forDateTime() {
            return "yyyy-MM-dd HH:mm:ss:SSS";
        }
    });
    System.out.println("DayEnd: " + FHelper.DateTimeToStr(FHelper.DayEnd("2019-12-01")));
}

/**
*
* Method: GetDayEnd(String nStr)
*
*/
@Test
public void testGetDayEnd() throws Exception {
//TODO: Test goes here...
    System.out.println("GetDayEnd: " + DateTimeHelper.DateTime2Str(DateTimeHelper.GetDayEnd("2019-12-01")));
}

/**
*
* Method: DateTimeSerial(String nPrefix)
*
*/
@Test
public void testDateTimeSerial() throws Exception {
//TODO: Test goes here...
}

/**
*
* Method: GetTickCount()
*
*/
@Test
public void testGetTickCount() throws Exception {
//TODO: Test goes here...
    System.out.println("GetTickCount: " + DateTimeHelper.GetTickCount());
}

/**
*
* Method: TickCount()
*
*/
@Test
public void testTickCount() throws Exception {
//TODO: Test goes here...
    System.out.println("TickCount: " + FHelper.TickCount());
}

/**
*
* Method: TickCountDiff(Long nBegin)
*
*/
@Test
public void testTickCountDiff() throws Exception {
//TODO: Test goes here...
        System.out.println("TickCountDiff: " + FHelper.TickCountDiff(System.nanoTime()));
    }


/**
*
* Method: GetTickCountDiff(Long nBegin)
*
*/
@Test
public void testGetTickCountDiff() throws Exception {
//TODO: Test goes here...
    System.out.println("GetTickCountDiff: " + DateTimeHelper.GetTickCountDiff(System.nanoTime()));
}

}
