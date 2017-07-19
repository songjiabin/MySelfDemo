package gocar.jeno.com.horizontalprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 水平移动的  进度条
 */
public class MainActivity extends AppCompatActivity {

    private HorizontalProgressBar horizontalProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        horizontalProgressBar = (HorizontalProgressBar) findViewById(R.id.horizontal_progress_view);
        horizontalProgressBar.setProgressWithAnimation(60);
    }
}
