package wecourse.special.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import wecourse.utils.static_.UiUtils;
import wecourse.weihe.time.R;

/**
 * Created by ddany on 2016/4/28.
 */
public class VoiceContralView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private boolean done = false;//是否循环绘制
    private final int INITIAL = 21;
    private int WAIT_OR_UPDATE = INITIAL;
    private Paint paint;
    private int width, height;//该View的宽和高
    private int DEFAUL_R;
    float scale = (float) 1.0;//通过它来调节圆的比例大小
    double parameter = 0;//通过他来调节转速和间距
    //定义点击事件的范围
    private int left, top, right, bottom;
    private Canvas canvas;
    //线程池和线程发放
    private ExecutorService threadPool;
    private UpdateViewThread updateViewThread;
    //语音用对象
    SpeechRecognizer mIat;
    //存放获取到的数据内容
    String contentStr = "";

    public VoiceContralView(Context context) {
        this(context, null);
    }

    public VoiceContralView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VoiceContralView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        holder = getHolder();
        holder.addCallback(this);
        //定义一个单例线程池
        threadPool = Executors.newSingleThreadExecutor();
        updateViewThread = new UpdateViewThread();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.voiceview);
        DEFAUL_R = typedArray.getDimensionPixelOffset(R.styleable.voiceview_circle_r, 20);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(Color.BLUE);


//        InitListener initListener =  new InitListener() {
//            @Override
//            public void onInit(int i) {
//                Log.i("没有", "meiyou");
//            }
//        };
        //语音类对象初始化
        mIat = SpeechRecognizer.createRecognizer(context,null);

        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter(SpeechConstant.ACCENT, "henanese");
//        mIat.setParameter(SpeechConstant.AUDIO_SOURCE,"-2");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        left = width / 2 - DEFAUL_R;
        top = height / 2 - DEFAUL_R;
        right = width / 2 + DEFAUL_R;
        bottom = height / 2 + DEFAUL_R;
        //button_rect = new Rect(left * 2, top * 2, right * 2, bottom * 2);
        setMeasuredDimension(width, height);
        Log.i("wozhixingla onMeasure", "执行啦");

        //设置背景透明
        setZOrderOnTop(true);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    public void resume() {
        threadPool.execute(updateViewThread);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        resume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        threadPool.shutdownNow();
    }

    class UpdateViewThread implements Runnable {

        SurfaceHolder surfaceHolder = holder;

        @Override
        public void run() {
            while (!done) {
                switch (WAIT_OR_UPDATE) {
                    case INITIAL:
                        try {
                            Thread.sleep(400);
                            scale = 0;
                            while (scale <= 1) {
                                canvas = surfaceHolder.lockCanvas();
                                float _scale = (float) Math.pow(scale, 3);
                                canvas.drawCircle(width / 2, height / 2, DEFAUL_R * _scale, paint);
                                surfaceHolder.unlockCanvasAndPost(canvas);
                                scale += 0.08;
                            }
//                            if (scale > 1) {
//                                canvas = surfaceHolder.lockCanvas();
//                                canvas.drawCircle(width / 2, height / 2, DEFAUL_R , paint);
//                                surfaceHolder.unlockCanvasAndPost(canvas);
//                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        requestExit();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        float r = (float) (DEFAUL_R / 6.18);
                        float distance = (float) (2 * r * 0.618);
                        float R_distance_r = (float) (r + DEFAUL_R * 1.2 + distance);
                        parameter = new Random().nextDouble();
                        double rad = 0;
                        while (!done) {
                            try {
                                rad = ((rad + parameter / 4 + 0.06) % (Math.PI * 2));
                                Thread.sleep(10);
                                canvas = surfaceHolder.lockCanvas();
                                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                                canvas.drawPaint(paint);
                                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                                canvas.drawCircle(width / 2, height / 2, DEFAUL_R, paint);
                                UiUtils.drawCircleDot(canvas, R_distance_r, r, width / 2, height / 2, paint, rad, parameter + 1);
                                surfaceHolder.unlockCanvasAndPost(canvas);

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        requestExit();
                        break;
                }
            }

        }


        public void requestExit() {
            done = true;
            canvas = surfaceHolder.lockCanvas();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPaint(paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            canvas.drawCircle(width / 2, height / 2, DEFAUL_R , paint);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    //boolean has = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                double x = event.getX();
                double y = event.getY();
                if (x <= right && x >= left && y >= top && y <= bottom) {
                    WAIT_OR_UPDATE = MotionEvent.ACTION_DOWN;
                    done = false;
                    int tag = mIat.startListening(mRecolistener);
                    Log.i("tag", tag + "");
                    resume();
                    return true;
                } else {
                    return false;
                }
//            case MotionEvent.ACTION_MOVE:
//                WAIT_OR_UPDATE = MotionEvent.ACTION_MOVE;
//                done = false;
//                //resume();
//                return false;
            case MotionEvent.ACTION_UP:
                UiUtils.FINISH = true;
                done = true;
                if (mIat.isListening()) {
                    mIat.stopListening();
                }

        }
        return true;
    }

    private RecognizerListener mRecolistener = new RecognizerListener() {
        @Override
        public void onVolumeChanged(int i, byte[] bytes) {
            parameter = i/30;
        }

        @Override
        public void onBeginOfSpeech() {
            Log.i("result:", "开始");
        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            while (b!=true) {
                contentStr = contentStr + "";
            }
            Log.i("result:", recognizerResult.getResultString());
        }

        @Override
        public void onError(SpeechError speechError) {
            Log.i("result:", "错误");

        }

        @Override
        public void onEvent(int i, int i1, int i2, Bundle bundle) {

        }
    };
}
