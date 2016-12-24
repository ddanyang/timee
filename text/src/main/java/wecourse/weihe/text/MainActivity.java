package wecourse.weihe.text;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ListView;

public class MainActivity extends Activity implements View.OnTouchListener {

    ViewGroup v;
    ListView list_one,list_two;
    HorizontalScrollView root;
    String[] arr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11","1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11"};
    VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = (HorizontalScrollView) findViewById(R.id.r);
        list_one = (ListView) findViewById(R.id.list_one);
        list_two = (ListView) findViewById(R.id.list_two);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, arr);

        list_one.setAdapter(adapter);
        list_two.setAdapter(adapter);

        root.setOnTouchListener(this);
        list_one.setOnTouchListener(this);

        if (velocityTracker == null) {
            velocityTracker = velocityTracker.obtain();
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        velocityTracker.addMovement(event);
        velocityTracker.computeCurrentVelocity(1000);
        switch (v.getId()) {
            case R.id.r:

                if (velocityTracker.getXVelocity() < velocityTracker.getYVelocity()) {
                    Log.i("X", velocityTracker.getXVelocity() + "");
                    Log.i("Y", velocityTracker.getYVelocity() + "");
                    return true;
                }
                return true;
            case R.id.list_one:
                Log.i("list_X", velocityTracker.getXVelocity() + "");
                Log.i("list_Y", velocityTracker.getYVelocity() + "");
                if (velocityTracker.getXVelocity(0) > velocityTracker.getYVelocity(0)) {
                    return true;
                }
                return false;
            case R.id.list_two:

                break;

        }


        return false;
    }

    private void clearVelocityTracker(VelocityTracker velocityTracker) {
        velocityTracker.clear();
        velocityTracker.recycle();
        velocityTracker = null;
    }
    }