package terracotta.jeno.com.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * author : 宋佳
 * time   : 2017/11/22
 * desc   :
 * version: 1.0.0
 */

public class Dmeo {

    public static void main(String args[]) throws ParseException {
//        demo();

//        String data = "2017/11/29 11:26:02";
//        data = data.replaceAll("/", "-");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date date = sdf.parse(data);
//
//        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
//        String aaa=sdf2.format(date);
//        System.out.println(aaa);

        String demo="35614651356136e51";
        System.out.println(isNumeric(demo));

    }


    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }




    /**
     * 纯数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private static void demo() {
        String data = "2017-11-15";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(data);
            long abc = date.getTime();
            System.out.println(abc);

            String ssss = sdf.format(date);
            System.out.println(ssss);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
