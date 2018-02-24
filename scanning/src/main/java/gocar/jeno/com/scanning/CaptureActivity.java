package gocar.jeno.com.scanning;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.zltd.industry.ScannerManager;

import java.io.IOException;
import java.util.Vector;

import gocar.jeno.com.scanning.utils.SoundUtils;
import gocar.jeno.com.scanning.zxing.camera.CameraManager;
import gocar.jeno.com.scanning.zxing.decoding.CaptureActivityHandler;
import gocar.jeno.com.scanning.zxing.view.ViewfinderView;

/**
 * author : 宋佳
 * time   : 2017/09/12
 * desc   :
 * version: 1.0.0
 */

public class CaptureActivity extends FragmentActivity implements SurfaceHolder.Callback, View.OnClickListener, ScannerManager.IScannerStatusListener {


    private CaptureActivityHandler handler;
    private MediaPlayer mediaPlayer;
    private Vector<BarcodeFormat> decodeFormats;
    private boolean playBeep;
    private String characterSet;
    private boolean vibrate;
    private static final long VIBRATE_DURATION = 200L;
    private static final float BEEP_VOLUME = 0.10f;
    private ViewfinderView viewfinderView;
    private Button btn_chang;
    private RelativeLayout rl_allCapture;
    private ScannerManager mScannerManager;
    protected int decoderType = 0;
    private SoundUtils mSoundUtils;
    private Handler handlers = new Handler();
    private Button tv_data;
    private boolean mIsScanKeyDown = false;//是否点击了 物理扫描建
    private boolean hasSurface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        CameraManager.init(getApplication());
        hasSurface = false;
        setViews();
        setScanner();
        setListeners();
    }


    /**
     * 设置扫描机器
     */
    private void setScanner() {
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
                    MyApplication.getINSTANCE().setISSCANERPHONE(false);
                    return;
                }
            }
        }
    }


    private void setListeners() {
        btn_chang.setOnClickListener(this);
        mScannerManager.addScannerStatusListener(this);
    }


    private void setViews() {
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        btn_chang = (Button) findViewById(R.id.btn_chang);
        rl_allCapture = (RelativeLayout) findViewById(R.id.rl_allCapture);
        tv_data = (Button) findViewById(R.id.tv_data);
    }


    public Handler getHandler() {
        return handler;
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();

        if (MyApplication.getINSTANCE().isISSCANERPHONE()) {
            mScannerManager.removeScannerStatusListener(this);
            mScannerManager.disconnectDecoderSRV();
        }
    }


    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);

            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();




        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;
    }


    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {

            return;
        } catch (RuntimeException e) {

            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };


    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_chang:
                //当切换的时候
                if (rl_allCapture.getVisibility() == View.VISIBLE) {
                    //当是照相机扫描的时候
                    rl_allCapture.setVisibility(View.GONE);
                    mScannerManager.connectDecoderSRV();
                    mScannerManager.addScannerStatusListener(this);
                    mScannerManager.setScanMode(ScannerManager.SCAN_SINGLE_MODE);
                } else {
                    //当是红外线扫描的时候
                    rl_allCapture.setVisibility(View.VISIBLE);
                    viewfinderView.drawViewfinder();

                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onScannerStatusChanage(int i) {

    }

    @Override
    public void onScannerResultChanage(byte[] bytes) {
        String data = new String(bytes);
        if (!TextUtils.isEmpty(data)) {
            Message message = new Message();
            message.what = 1;
            message.obj = data;
            MyHandler.sendMessage(message);
        }
    }


    /**
     * 重新进行扫码
     */
    private void reStartScacode() {
        handlers.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (handler != null) {
                    handler.restartPreviewAndDecode();
                }
            }
        }, 800);
    }


    public void handleDecode(Result result, Bitmap barcode) {
        playBeepSoundAndVibrate();
        String data = result.getText();

        Message message = new Message();
        message.what = 2;
        message.obj = data;
        MyHandler.sendMessage(message);

    }


    private Handler MyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mSoundUtils.success();
                    //处理数据
                    String data_1 = (String) msg.obj;
                    //展示数据
                    tv_data.setText(data_1);
                    break;
                case 2:
                    String data_2 = (String) msg.obj;
                    reStartScacode();
                    tv_data.setText(data_2);
                    //展示数据
                    break;

                default:
                    break;
            }
        }
    };


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


}
