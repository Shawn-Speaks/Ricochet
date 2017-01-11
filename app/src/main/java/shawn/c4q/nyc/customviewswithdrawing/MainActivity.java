package shawn.c4q.nyc.customviewswithdrawing;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final long UPDATE_DELAY_TIME = 20;
    public Handler mHandler;
    private ArrayList<DrawingView> mViewsUpdate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout root = (RelativeLayout) findViewById(R.id.activity_main);
        for (int index = 0; index < root.getChildCount(); index++){
            mViewsUpdate.add((DrawingView) root.getChildAt(index));
        }
        mHandler = new Handler();
        updateBall().run();
    }


    private Runnable updateBall() {
        return () -> {
           for (DrawingView drawing: mViewsUpdate) {
               mHandler.postDelayed(updateBall(), UPDATE_DELAY_TIME);
                drawing.invalidate();
           }
        };

    }
}
