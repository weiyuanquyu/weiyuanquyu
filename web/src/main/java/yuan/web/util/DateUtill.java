package yuan.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtill {

    /**
     * 将String转换为Date
     * @param str  如果日期str 为原始格式,type为 dateFormat 格式
     * @return
     */
    public static Date getDateByStr(String str,String type){
        SimpleDateFormat dateFormat=new SimpleDateFormat(type);

        Date date=null;
        try {
            date=dateFormat.parse(str);
        } catch (ParseException e) {
            System.out.println("日期转换错误");
            e.printStackTrace();
        }
        return date;
    }

    /*
      本月第一天
    */
    public static String getFirstOfMonth(){

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return format.format(c.getTime());
    }

    /*
       本月最后一天
     */
    public static String getLastOfMonth(){

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return format.format(ca.getTime());
    }

    /*
      今天
     */

    public static String getToday(){

        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月最后一天
        return dateFormat.format(new Date());
    }

    public static String getTime(Date date){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(date);
    }

}
