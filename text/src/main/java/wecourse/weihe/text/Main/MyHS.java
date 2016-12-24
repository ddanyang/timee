
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ddany on 2016/4/19.
 */
public  class MyHS extends HorizontalScrollView {

    public MyHS(Context context) {
        super(context);
    }

    public MyHS(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHS(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

}
