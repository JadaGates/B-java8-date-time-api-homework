package com.thoughtworks.capability.gtb;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {
  public static void main(String[] args) {
    ZoneId londonZoneId = ZoneId.of("Europe/London");
    ZoneId chicagoZoneId = ZoneId.of("America/Chicago");
    ZoneId localZoneId = ZoneId.systemDefault();

    String timeStr = "2020-04-01 14:30:00";

    // 根据格式创建格式化类
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    // 从字符串解析得到会议时间
    LocalDateTime londonMeetingTime = LocalDateTime.parse(timeStr, formatter);
    ZonedDateTime zonedLondonDateTime  = ZonedDateTime.of(londonMeetingTime, londonZoneId);

    ZonedDateTime localMeetingTimeWithZoned = zonedLondonDateTime.withZoneSameInstant(localZoneId);
    LocalDateTime localMeetingTime = localMeetingTimeWithZoned.toLocalDateTime();

    LocalDateTime now = LocalDateTime.now();

    if(now.isAfter(localMeetingTime)){
      Period period = Period.between(localMeetingTime.toLocalDate(),now.toLocalDate());
      period = period.plusDays(1);
      Temporal newLocalMeetingTime = period.addTo(localMeetingTime);

      ZonedDateTime zonedLocalMeetingTime = ZonedDateTime.of((LocalDateTime) newLocalMeetingTime, localZoneId);
      ZonedDateTime newChicagoMeetingTimeWithZoned = zonedLocalMeetingTime.withZoneSameInstant(chicagoZoneId);
      LocalDateTime newChicagoMeetingTime = newChicagoMeetingTimeWithZoned.toLocalDateTime();
      String newChicagoMeetingTimeString = formatter.format(newChicagoMeetingTime);

      System.out.println("The new meeting time for Cicago is at " + newChicagoMeetingTimeString);
    }else {
      System.out.println("会议还没开始呢");
    }
  }
}
