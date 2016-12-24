package wecourse.utils.static_;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.SurfaceHolder;

/**
 * Created by ddany on 2016/4/26.
 * 参看网址：http://2528.iteye.com/blog/2038754
 * 其实在研究getDimension()、getDimensionPixelOffset()和getDimensionPixelSize()这三个函数源码时，
 * 会发现这三个函数的实现都是先获取在xml中定义的值，然后再根据是dp/sp/in/mm/pt转化成相应的像素值，
 * 所以，我们只需要把android的部分代码抽出来即可，封装成一个小工具类如下：
 */
public class UiUtils {

    private static float mScale = -1;
    private static TypedValue typedValue = new TypedValue();

    //不能创建对象
    private UiUtils() {

    }

    public static int getXmlDef(Context context, int id) {
        synchronized (typedValue) {
            TypedValue value = typedValue;
            context.getResources().getValue(id, value, true);
            return (int)TypedValue.complexToFloat(value.data);
        }
    }

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        if (mScale == -1) {
            mScale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (pxValue / mScale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     * @param dipValue
     * @param context
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //是否启动结束绘制画图函数
    public static boolean FINISH = false;
    /*
    * 画圆点
    * canvas 传入画布
    * r 圆点围绕半径
    * circle_r 小圆半径
    * x 圆点围绕圆心
    * y 同上
    * */
    public static void drawCircleDot(Canvas canvas, float r, float circle_r, int x, int y, Paint paint, double rad, double distance) {
        int[] x_r = new int[8];
        int[] y_r = new int[8];

        for (int i = 0; i < 8; i++) {
            x_r[i] = (int) (Math.cos((i * Math.PI * distance) / 8 + rad) * r + x);
            y_r[i] = (int) (Math.sin((i * Math.PI * distance) / 8 + rad) * r + y);
            canvas.drawCircle(x_r[i], y_r[i], circle_r, paint);
            if (FINISH) {
//                x_r[i] = (int) (Math.cos((i * Math.PI * rad) / 8) * r + x);
//                y_r[i] = (int) (Math.sin((i * Math.PI * rad) / 8) * r + y);
//                canvas.drawCircle(x_r[i], y_r[i], circle_r, paint);
                FINISH = false;
            }
        }
    }

    /*
    * 画旋转的圆点
    * canvas 传入画布
    * speed 旋转的角度
    * r 圆点围绕半径
    * circle_r 小圆半径
    * x 圆点围绕圆心
    * y 同上
    * */
    public static void rotateCircle(Canvas canvas, int speed,int r, int circle_r, int x, int y, Paint paint) {
        //drawCircle(canvas, r, circle_r, x, y, paint);
        canvas.drawColor(Color.WHITE);
        canvas.rotate(speed, x, y);
        //drawCircleDot(canvas, r, circle_r, x, y, paint);
    }

    public static void drawCircle( float r, int x, int y, Paint paint, SurfaceHolder surfaceHolder) {
        float scale = 0;
        Canvas canvas;
        while (scale < 1) {
            canvas = surfaceHolder.lockCanvas();
            scale += 0.08;
            float _scale = (float) Math.pow(scale, 3);
            canvas.drawColor(Color.WHITE);
            canvas.drawCircle(x, y, r * _scale, paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
/*
    参考博客 :
            -- 双缓冲demo :  http://blog.csdn.net/imyfriend/article/details/8033823 应该是你的demo那个博客
            -- 双缓冲理解 : http://www.apkbus.com/android-99309-1-1.html 分析SurfaceView源码
            -- 双缓冲与单缓冲区别 : http://blog.csdn.net/lcfeng1982/article/details/7431446
            -- 双缓冲与但缓冲动画绘制区别demo : http://blog.csdn.net/geolo/article/details/6024761*/
}
