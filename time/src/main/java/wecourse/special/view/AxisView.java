package wecourse.special.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import wecourse.utils.static_.UiUtils;
import wecourse.weihe.time.R;

/**
 * Created by ddany on 2016/4/24.
 */
public class AxisView extends View {

    private Context context;

    private long startTimeMillis = System.currentTimeMillis() + 3600 * 1000;
    private long endTimeMillis = startTimeMillis;
    private int TIME_DP = 50;//一小时多少dp
    private int SIZE_TIME_SCALE = 3600 * 1000 / TIME_DP;
    //线的一些属性
    private int axisWidth;//轴线的宽度
    private int axisColor, cricleColor;//轴线的颜色和圆点的颜色
    private Paint paint;

    public AxisView(Context context) {
        this(context, null);
    }

    public AxisView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AxisView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.axisview, 0, 0);
        axisWidth = typedArray.getDimensionPixelOffset(R.styleable.axisview_axis_width, 1);
        axisColor = typedArray.getColor(R.styleable.axisview_axis_color, 0xFF56abe4);
        cricleColor = typedArray.getColor(R.styleable.axisview_circle_color, 0xFF56abe4);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(axisWidth);
        paint.setDither(true);
//        float a = typedArray.getDimensionPixelOffset(android.R.attr.layout_width,2);
//        Log.i("width", String.valueOf(UiUtils.px2dip(context, a)));
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int _width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int _height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        endTimeMillis = System.currentTimeMillis()+3600*1000;
        _height += UiUtils.dip2px(context, (endTimeMillis - startTimeMillis) / SIZE_TIME_SCALE);
        setMeasuredDimension(_width, _height);

        //Log.i("onMeasure", UiUtils.px2dip(context,_height) + "");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //拿到长宽
        float width, height, axis_X, axis_Y, circleR;
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        axis_X = width / 2;
        axis_Y = height;
        circleR = axisWidth;
        //画直线
        float top = circleR;
        float bottom = axis_Y - top;
        paint.setColor(axisColor);
        canvas.drawLine(axis_X, top, axis_X, bottom, paint);
        //画圆点
        paint.setColor(cricleColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(axis_X, 0, circleR, paint);
        canvas.drawCircle(axis_X, axis_Y, circleR, paint);
        //画圆弧
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(axis_X, 0, circleR+axisWidth/2, paint);
        canvas.drawCircle(axis_X,axis_Y,circleR+axisWidth/2,paint);
    }

}
