package me.zuuhyun.youtubeproject.util;

import java.security.SecureRandom;
import java.sql.Date;
import java.time.LocalDate;

public class Utils {

    public static long generateRandomNum(int start, int end) {
        SecureRandom secureRandom = new SecureRandom();
        return start + secureRandom.nextInt(end);
    }

    public static LocalDate getStartDate(LocalDate endDate, Period period){
        LocalDate startDate;
        if (period == Period.WEEK){
            startDate = LocalDate.now().minusWeeks(1);
        }else if (period == Period.MONTH){
            startDate = LocalDate.now().minusMonths(1);
        }else {/*Period.DAY*/
            startDate = endDate;
        }
        return startDate;
    }
}
