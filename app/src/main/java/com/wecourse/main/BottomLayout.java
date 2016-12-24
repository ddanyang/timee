package com.wecourse.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wecourse.DeviceInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ddany on 2016/3/25.
 */
public class BottomLayout extends LinearLayout {

    //该接口用于在实现对底部Button的监听
    public interface BottomLayoutOnClick {
        public void bottom1NearOnClick();

        public void bottom2FaxianOnClick();

        public void bottom3XiaoxiOnClick();

        public void bottom4GuanzhuOnClick();

        public void bottom5GerenOnClick();
    }

    private BottomLayoutOnClick bottomLayoutLister;

    public void setBottomLayout(BottomLayoutOnClick b) {
        bottomLayoutLister = b;
    }

    //设备版本号
    final private int DEVICE_VERSION = DeviceInfo.DEVICE_SDK_VERSION;
    //定义元素
    private View bottom1Near, bottom2Faxian, bottom3Xiaoxi, bottom4Guanzhu, bottom5Geren;
    private ImageView bottom1NearBtn, bottom2FaxianBtn, bottom3XiaoxiBtn, bottom4GuanzhuBtn, bottom5GerenBtn;
    private TextView bottom1NearTv, bottom2FaxianTv, bottom3XiaoxiTv, bottom4GuanzhuTv, bottom5GerenTv;
    //Button中的Tag
    Map<String, Drawable> map;
    String no = "no";
    String yes = "yes";
    int colorno = Color.parseColor("#bfbfbf");
    int coloryes = Color.parseColor("#3F51B5");
    private ImageView oldBtn = null;
    private TextView oldTv = null;

    //构造方法
    public BottomLayout(Context context) {
        this(context, null);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomLayout(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.parseColor("#ffffff"));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main_bottom, this, true);

        bottom1Near = view.findViewById(R.id.bottom1_near);
        bottom2Faxian = view.findViewById(R.id.bottom2_faxian);
        bottom3Xiaoxi = view.findViewById(R.id.bottom3_xiaoxi);
        bottom4Guanzhu = view.findViewById(R.id.bottom4_guanzhu);
        //bottom5Geren = view.findViewById(R.id.bottom5_geren);

        //找到各个元素，并为其设置背景
        bottom1NearBtn = (ImageView) bottom1Near.findViewById(R.id.bottom1_near_btn);
        map = new HashMap<String, Drawable>();
        if (DEVICE_VERSION >= Build.VERSION_CODES.LOLLIPOP) {
            map.put(no, context.getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getDrawable(R.drawable.bottom4_guanzhu_yes));
        } else {
            map.put(no, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_yes));
        }
        bottom1NearBtn.setTag(map);

        bottom2FaxianBtn = (ImageView) bottom2Faxian.findViewById(R.id.bottom2_faxian_btn);
        map = new HashMap<String, Drawable>();
        if (DEVICE_VERSION >= Build.VERSION_CODES.LOLLIPOP) {
            map.put(no, context.getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getDrawable(R.drawable.bottom4_guanzhu_yes));
        } else {
            map.put(no, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_yes));
        }
        bottom2FaxianBtn.setTag(map);

        bottom3XiaoxiBtn = (ImageView) bottom3Xiaoxi.findViewById(R.id.bottom3_xiaoxi_btn);
        map = new HashMap<String, Drawable>();
        if (DEVICE_VERSION >= Build.VERSION_CODES.LOLLIPOP) {
            map.put(no, context.getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getDrawable(R.drawable.bottom4_guanzhu_yes));
        } else {
            map.put(no, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_yes));
        }
        bottom3XiaoxiBtn.setTag(map);

        bottom4GuanzhuBtn = (ImageView) bottom4Guanzhu.findViewById(R.id.bottom4_guanzhu_btn);
        map = new HashMap<String, Drawable>();
        if (DEVICE_VERSION >= Build.VERSION_CODES.LOLLIPOP) {
            map.put(no, context.getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getDrawable(R.drawable.bottom4_guanzhu_yes));
        } else {
            map.put(no, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_no));
            map.put(yes, context.getResources().getDrawable(R.drawable.bottom4_guanzhu_yes));
        }
        bottom4GuanzhuBtn.setTag(map);

//        bottom5GerenBtn = (ImageView) bottom5Geren.findViewById(R.id.bottom5_geren_btn);
//        map = new HashMap<String,Drawable>();
//        map.put(no, context.getResources().getDrawable(R.drawable.bottom5_geren_no));
//        map.put(yes, context.getResources().getDrawable(R.drawable.bottom5_geren_yes));
//        bottom5GerenBtn.setTag(map);

        bottom1NearTv = (TextView) bottom1Near.findViewById(R.id.bottom1_near_tv);
        bottom1NearTv.setTag(R.id.bottom1_near);
        bottom2FaxianTv = (TextView) bottom2Faxian.findViewById(R.id.bottom2_faxian_tv);
        bottom2FaxianTv.setTag(R.id.bottom2_faxian);
        bottom3XiaoxiTv = (TextView) bottom3Xiaoxi.findViewById(R.id.bottom3_xiaoxi_tv);
        bottom3XiaoxiTv.setTag(R.id.bottom3_xiaoxi);
        bottom4GuanzhuTv = (TextView) bottom4Guanzhu.findViewById(R.id.bottom4_guanzhu_tv);
        bottom4GuanzhuTv.setTag(R.id.bottom4_guanzhu);
//        bottom5GerenTv = (TextView) bottom5Geren.findViewById(R.id.bottom5_geren_tv);
//        bottom5GerenTv.setTag(R.id.bottom5_geren);

        //添加监听事件
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (oldBtn != null && oldTv != null) {
                    if (!oldTv.getTag().equals(id)) {
                        map = (Map<String, Drawable>) oldBtn.getTag();
                        if (DEVICE_VERSION >= Build.VERSION_CODES.LOLLIPOP) {
                            oldBtn.setBackground(map.get(no));
                        } else {
                            oldBtn.setBackgroundDrawable(map.get(no));
                        }
                        oldTv.setTextColor(colorno);
                    } else {
                        return;
                    }
                }
                switch (id) {
                    case R.id.bottom1_near:
                        oldBtn = bottom1NearBtn;
                        oldTv = bottom1NearTv;
                        map = (Map<String, Drawable>) bottom1NearBtn.getTag();
                        if (bottomLayoutLister != null) {
                            bottomLayoutLister.bottom1NearOnClick();
                        }
                        break;
                    case R.id.bottom2_faxian:
                        oldBtn = bottom2FaxianBtn;
                        oldTv = bottom2FaxianTv;
                        map = (Map<String, Drawable>) bottom2FaxianBtn.getTag();
                        bottomLayoutLister.bottom2FaxianOnClick();
                        break;
                    case R.id.bottom3_xiaoxi:
                        oldBtn = bottom3XiaoxiBtn;
                        oldTv = bottom3XiaoxiTv;
                        map = (Map<String, Drawable>) bottom3XiaoxiBtn.getTag();
                        bottomLayoutLister.bottom3XiaoxiOnClick();
                        break;
                    case R.id.bottom4_guanzhu:
                        oldBtn = bottom4GuanzhuBtn;
                        oldTv = bottom4GuanzhuTv;
                        map = (Map<String, Drawable>) bottom4GuanzhuBtn.getTag();
                        bottomLayoutLister.bottom4GuanzhuOnClick();
                        break;
//                    case R.id.bottom5_geren:
//                        oldBtn = bottom5GerenBtn;
//                        oldTv = bottom5GerenTv;
//                        map = (Map<String, Drawable>) bottom5GerenBtn.getTag();
//                        bottomLayoutLister.bottom5GerenOnClick();
//                        break;
                }
                oldBtn.setBackgroundDrawable(map.get(yes));
                oldTv.setTextColor(coloryes);
            }
        };
        bottom1Near.setOnClickListener(onClickListener);
        bottom2Faxian.setOnClickListener(onClickListener);
        bottom3Xiaoxi.setOnClickListener(onClickListener);
        bottom4Guanzhu.setOnClickListener(onClickListener);
        //bottom5Geren.setOnClickListener(onClickListener);
        onClickListener.onClick(bottom1Near);
    }

}
