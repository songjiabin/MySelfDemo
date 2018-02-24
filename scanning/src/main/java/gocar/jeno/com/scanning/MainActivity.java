package gocar.jeno.com.scanning;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.zltd.industry.ScannerManager;

import java.util.ArrayList;

import gocar.jeno.com.scanning.base.BaseActivity;


/**
 * 单位接手的智联天下  扫描机
 */

public class MainActivity extends BaseActivity implements ScannerManager.IScannerStatusListener,
        RadioGroup.OnCheckedChangeListener, CheckBox.OnCheckedChangeListener, View.OnClickListener {


    private RadioGroup rg_model;
    private RadioButton rb_single; //单个的扫描
    private RadioButton rb_continuous;//连续的扫描
    private RadioButton rb_control; //物理的控制
    private CheckBox cb_clone;
    protected boolean inContinuousShoot = false;//是否正在扫描
    private boolean mIsScanKeyDown = false;//是否点击了 物理扫描建

    private ArrayList<String> mBarcodeList;
    private MyAdapter mListAdaper;
    private Button btn_clear;
    private ListView lv_scannering;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private TextView textView;


    @Override
    protected void setView() {
        rg_model = (RadioGroup) findViewById(R.id.rg_model);
        rb_single = (RadioButton) findViewById(R.id.rb_single);
        rb_continuous = (RadioButton) findViewById(R.id.rb_continuous);
        rb_control = (RadioButton) findViewById(R.id.rb_control);
        cb_clone = (CheckBox) findViewById(R.id.cb_clone);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        lv_scannering = (ListView) findViewById(R.id.lv_scannering);
        textView = (TextView) findViewById(R.id.tv);
        //如果不是扫描机的话那么直接隐藏

        if (!MyApplication.getINSTANCE().isISSCANERPHONE()) {
            rg_model.setVisibility(View.GONE);
            cb_clone.setVisibility(View.GONE);
        } else {
            rg_model.setVisibility(View.VISIBLE);
            cb_clone.setVisibility(View.VISIBLE);
        }


    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mSoundUtils.success();
                    mBarcodeList.add(0, (String) msg.obj);
                    initAdapter();
                    break;

                default:
                    break;
            }
        }
    };


    @Override
    protected void setListener() {
        rg_model.setOnCheckedChangeListener(this);
        cb_clone.setOnCheckedChangeListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    protected void setData() {
        initAdapter();
    }

    private void initAdapter() {
        if (mBarcodeList == null)
            mBarcodeList = new ArrayList<String>();

        mListAdaper = new MyAdapter(mBarcodeList);
        lv_scannering.setAdapter(mListAdaper);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onScannerStatusChanage(int i) {

    }


    private StringBuffer sb = new StringBuffer();

    @Override
    public void onScannerResultChanage(byte[] bytes) {
        String data = new String(bytes);
        if (!TextUtils.isEmpty(data)) {

            Message message = new Message();
            message.what = 1;
            message.obj = data;
            handler.sendMessage(message);


        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_single:
                //单选
                mScannerManager.stopContinuousScan();
                mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);
                mScannerManager.singleScan();
                break;
            case R.id.rb_continuous:
                //连续
                mScannerManager.setScanMode(ScannerManager.SCAN_CONTINUOUS_MODE);
                if (!inContinuousShoot) {
                    inContinuousShoot = true;
                    mScannerManager.startContinuousScan();
                } else {
                    inContinuousShoot = false;
                    mScannerManager.stopContinuousScan();
                }
                break;
            case R.id.rb_control:
                //保持按键
                mScannerManager.setScanMode(ScannerManager.SCAN_KEY_HOLD_MODE);
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mSoundUtils.enablePlay(isChecked);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_clear:
                mBarcodeList.clear();
                initAdapter();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BUTTON_A: {
                //当你点击物理的扫描按钮的时候
                if (!mIsScanKeyDown) {

                    mScannerManager.dispatchScanKeyEvent(event);
                    // mScannerManager.triggerLevel(ScannerManager.LOW_LEVEL);
                    mIsScanKeyDown = true;

                }
                return true;

            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BUTTON_A: {
                if (mIsScanKeyDown) {
                    mIsScanKeyDown = false;
                    mScannerManager.dispatchScanKeyEvent(event);
                    // mScannerManager.triggerLevel(ScannerManager.HIGH_LEVEL);
                }
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }


    public class MyAdapter extends BaseAdapter {


        private ArrayList<String> data;

        public MyAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holde = null;
            if (convertView == null) {
                holde = new ViewHolder();
                convertView = LinearLayout.inflate(MainActivity.this, R.layout.item_main, null);
                holde.textView = (TextView) convertView.findViewById(R.id.textview);
                convertView.setTag(holde);
            } else {
                holde = (ViewHolder) convertView.getTag();
            }
            holde.textView.setText(data.get(position));
            return convertView;
        }


    }

    public class ViewHolder {

        TextView textView;

    }


}
