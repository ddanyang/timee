package wecourse.special.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import wecourse.weihe.time.R;

/**
 * Created by ddany on 2016/4/24.
 */
public class CircleView extends View {

    private Context context;
    //线的一些属性
    private int axisWidth;
    private int axisColor, cricleColor;
    private Paint paint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.axisview, 0, 0);
        axisWidth = typedArray.getDimensionPixelOffset(R.styleable.axisview_axis_width, 1);
        axisColor = typedArray.getColor(R.styleable.axisview_axis_color, 0xFF56abe4);
        cricleColor = typedArray.getColor(R.styleable.axisview_circle_color, 0xFF56abe4);
        Log.i("circleColor1", cricleColor + "");

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(axisWidth);
        paint.setDither(true);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
        float width, height, axis_X, axis_Y, circleR;
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        Log.i("width", width+"");
        Log.i("height", height+"");

        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        float width1 = display.getWidth();
        float height1 = display.getHeight();
        Log.i("width1", width1+"");
        Log.i("height1", height1 + "");

        paint.setStrokeWidth(width1 / 2);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, 0, width / 2, paint);

        paint.setColor(0xFF786567);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(width / 2, 0, width / 2, paint);
    }

}
