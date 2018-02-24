package terracotta.jeno.com.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//
//    private String[] arrays =
//            {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    private static ArrayList<String> list = new ArrayList<>();

    static {
        for (char i = 'a'; i <= 'z'; i++) {
            list.add(String.valueOf(i));
        }
    }

    private WebView wvBaseWebView;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///读取数据

/*

        try {
            readLoad();

        } catch (UnsupportedEncodingException e) {


        }
*/

        initView();
        initListener();

    }

    private void initListener() {
        wvBaseWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                return true;

            }
        });

        wvBaseWebView.setWebChromeClient(new WebChromeClient() {

        });
    }

    private void initView() {
        wvBaseWebView = (WebView) findViewById(R.id.webView);
        webSettings = wvBaseWebView.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(false);
        wvBaseWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setCacheMode(webSettings.LOAD_DEFAULT);// 打开缓存

        webSettings.setSupportZoom(false);
        webSettings.setAllowContentAccess(true);

        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        // webSettings.setLoadsImagesAutomatically(true);
        webSettings.setUseWideViewPort(true);// 将图片调整到适合webview的大小
        webSettings.setBlockNetworkImage(true);//这个加载js  后加载图片


//        String url = "http://192.168.2.51:1041/LuckDraw/index";
//        String params = "A=klm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjmklm,l,plkm,;.,;lpkmpk,;l,lkmkjnkjkjkjm";
//
//        byte[] a = EncodingUtils.getBytes(params, "BASE64");
//
//
//        wvBaseWebView.postUrl(url, a);


        String url = "https://www.baidu.com/";
        wvBaseWebView.loadUrl(url);


    }


    private void test(String data) {
        //Rsa 处理
//        String data = "p+2Z9WAinnTK2VXHmtGIxKF/oiZyGuJHU66YM+d7FSkQ0OFucCrP57iqaOshPFOSfRRM5li2Tj1/S7g9c7dfIigZzAcHZW0IvCitYG0LtIp5dxm87DgMF8bUgk1HVY1x4TBxCGEVQCGO78n47GvuDFQtahD/Q5KwVcEcStyc6w4=";


        //私钥
        String privateKeyXml = "<RSAKeyValue><Modulus>ztae/YElxNRlSDxnnfipujvVc8hiNlbg2YGNf9RQIHNFUGkBOQAXEBPemcHzcxB1Rc6h5Gh2/IoYLiDRZ2pn3PunZdM2iJcVsHBinne9kO6UMtppKB2YyoVKaj5r3x6a6R61KAjqCVf2WykDA9KevT0hxHEXQFyuAvLrHnY6dpU=</Modulus><Exponent>AQAB</Exponent><P>6L1c+/PKHPRdQ86Jv6oJ4gl9KNMlgnk+P7dH06Pb3chkRR/JlZsOcuqfXqTTGhvOHVVb6eUsChRdlC/zJplnKQ==</P><Q>44KStVtrzL9yGPckyOehJ0z0AEJw1tWTsWvUl5aaglSQlV/42XQa7qgeYDy8HfaiLiPxJRU8UFpW+1GjfUUdjQ==</Q><DP>Xs/Vs2YrLgR2ggr/nq7ce9K1xA3QOAjATSCTCCq2hPD/uBEtA9840aO27M1kmjw9iUGewff0Kt8FCDtIaK8RkQ==</DP><DQ>jIkk3NhbL7/22Asacuv6NU+ozdw83chSkl88lGMYkHdyVINTMegxlsjM8r+xqISoS/4xwKlsd2pGnrogVopyJQ==</DQ><InverseQ>kN1uclKX1hImToWFoAKA2p+JmdObnxhd4ghPPB+fYXRzafz0XXUvb/cC22ueCUfZG9dzHDNGgk6C25JIcU8mow==</InverseQ><D>Qn+B0iLJT8SxY034SjN97d/6uiS8XpgD5ineojBSKnyk1jtMJ1U+FO8ua+SNLnsmGPFb3yEuz/Zd28a1TDhLd46ABtzTkCMqCsDRfdo5FMpk1nb6VSz0UzTehgjWI8TKndOoDygt1D9Uu3E5nFFrtfkjnm4us8vVKpCPhxk8ckE=</D></RSAKeyValue>";
        PrivateKey privateKey = RsaHelper.decodePrivateKeyFromXml(privateKeyXml);
        try {
            byte[] decryptByte = RsaHelper.decryptData(Base64Helper.decode(data), privateKey);


            if (decryptByte == null) {
                Toast.makeText(this, "校验失败", Toast.LENGTH_SHORT).show();
                Log.i("打印失败", "失败的码是---------: " + data);
                return;
            }


            String result = new String(decryptByte);//总长是48位
            if (result.length() > 2) {
                return;
            }

            //最后解密的结果


            //进行算法   得到奖励
            if (result.length() == 48) {
                String firstIndex = (String) result.substring(result.length() - 4, result.length() - 2);
                String secondIndex = (String) result.substring(result.length() - 2, result.length());

                int firstIndexNum = Integer.parseInt(firstIndex);

                int sceondIndexNum = Integer.parseInt(secondIndex);


                String firstCheck = result.substring(firstIndexNum, firstIndexNum + 4);//第一个校验位
                String secondCheck = result.substring(sceondIndexNum, sceondIndexNum + 4);//第er个校验位
                String reslult = checkStr(firstCheck);


                if (reslult.equals(secondCheck)) {
                    //正确  得到金额
                    if (firstCheck.equals("ngrt") && secondCheck.equals("rkvx")) {
                        //5块
                        Log.i("打印成功", "金额是---------: " + "5块" + "-----------------" + reslult);
                    } else if (firstCheck.equals("apol") && secondCheck.equals("etsp")) {
                        //10块
                        Log.i("打印成功", "金额是---------: " + "10块");
                    } else if (firstCheck.equals("ulsf") && secondCheck.equals("ypwj")) {
                        //20块
                        Log.i("打印成功", "金额是---------: " + "20块");
                    } else if (firstCheck.equals("jynx") && secondCheck.equals("ncrb")) {
                        //30块
                        Log.i("打印成功", "金额是---------: " + "30快");
                    } else {
                        Log.i("打印失败---没有金额", "失败的码是---------: " + data);
                    }


                } else {
                    //校验失败
                    Log.i("打印失败", "校验失败 不符合要求---------: " + data);
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String checkStr(String str) {
        String result = "";
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            String pp = String.valueOf(c[i]);
            int index = list.indexOf(pp);//得到位置
            if (index + 4 > 25) {
                index = index + 4 - 26;
            } else {
                index = index + 4;
            }
            result += list.get(index);
        }
        return result;
    }


    private String readLoad() throws UnsupportedEncodingException {

        int num = 0;

        InputStreamReader inputStreamReader = null;
        InputStream inputStream = getResources().openRawResource(R.raw.d);
        inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//                sb.append("\n");
                test(line);
                num++;
                Log.i("打印数量", num + "");


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("打印数量", num + "");

        return sb.toString();
    }


}
