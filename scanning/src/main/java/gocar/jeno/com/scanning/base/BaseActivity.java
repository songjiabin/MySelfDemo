package gocar.jeno.com.scanning.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.zltd.industry.ScannerManager;

import gocar.jeno.com.scanning.MyApplication;
import gocar.jeno.com.scanning.utils.SoundUtils;

/**
 * author : 宋佳
 * time   : 2017/09/05
 * desc   : Acitity基类
 * version: 1.0.0
 */

public abstract class BaseActivity extends FragmentActivity implements ScannerManager.IScannerStatusListener {


    protected ScannerManager mScannerManager;
    protected int decoderType = 0;
    protected SoundUtils mSoundUtils;
    public boolean hasSurface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String contextString = this.toString();
        String name = contextString.substring(contextString.lastIndexOf(".") + 1, contextString.indexOf("@"));
        if (!name.equals("CaptureActivity")) {
            setContentView(getLayoutId());
            setView();
            setListener();
            setData();
        }

        //进行对扫描的一些设置
        initScanner();
    }

    protected abstract void setData();

    protected void initScanner() {
        //1.创建ScannerManager
        if (MyApplication.getINSTANCE().isISSCANERPHONE()) {
            //如果是扫描机的话 进行操作
            try {
                    mScannerManager = ScannerManager.getInstance();
                    decoderType = mScannerManager.getDecoderType();
                    mSoundUtils = SoundUtils.getInstance();
                    mSoundUtils.init(this);
                } catch (Exception e) {
                    if (e instanceof NullPointerException) {
                        //表示是正常的手机
//                        MyApplication.getINSTANCE().setISSCANERPHONE(false);
                        return;
                    }
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
     /*    if (MyApplication.getINSTANCE().isISSCANERPHONE()) {
            //2.连接扫描服务
            int res = mScannerManager.connectDecoderSRV();
            mScannerManager.addScannerStatusListener(this);
            if (decoderType == Constants.DECODER_ONED_SCAN) {
                if (!mScannerManager.getScannerEnable()) {
                    new AlertDialog.Builder(this)
                            .setTitle("设置")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setMessage("请在设置中打开扫描头")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                    //点击确定
                                }
                            })
                            .setCancelable(false)
                            .show();
                }
            }
        } else {
            //正常的手机
        }*/
    }

    protected abstract void setView();


    protected abstract void setListener();


    protected abstract int getLayoutId();


    // 4.在App不工作或者退到后台之后,有必要释放资源,关闭或者移除扫描服务
    @Override
    protected void onPause() {
        super.onPause();
         if (MyApplication.getINSTANCE().isISSCANERPHONE()) {
            mScannerManager.removeScannerStatusListener(this);
            mScannerManager.disconnectDecoderSRV();
        }

    }


}
