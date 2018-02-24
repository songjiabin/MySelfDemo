package gocar.jeno.com.scanning;

import android.app.Application;

/**
 * author : 宋佳
 * time   : 2017/09/05
 * desc   : Application
 * version: 1.0.0
 */

public class MyApplication extends Application {


    private static MyApplication INSTANCE = null;

    //是否是扫描的手机  还是正常的手机
    private static boolean ISSCANERPHONE = true;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }


    public static MyApplication getINSTANCE() {
        return INSTANCE;
    }


    public void setISSCANERPHONE(boolean isscanerphone) {
        this.ISSCANERPHONE = isscanerphone;
    }


    public boolean isISSCANERPHONE() {
        return ISSCANERPHONE;
    }


}
