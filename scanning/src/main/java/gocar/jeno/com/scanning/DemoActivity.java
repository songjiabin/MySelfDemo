package gocar.jeno.com.scanning;

import android.content.Intent;
import android.view.View;

import gocar.jeno.com.scanning.base.BaseActivity;

/**
 * author : 宋佳
 * time   : 2017/09/12
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends BaseActivity {


    @Override
    protected void setData() {

    }

    @Override
    protected void setView() {
        findViewById(R.id.btnJump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DemoActivity.this,CaptureActivity.class);
                DemoActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_scond;
    }

    @Override
    public void onScannerStatusChanage(int i) {

    }

    @Override
    public void onScannerResultChanage(byte[] bytes) {

    }
}
