package com.wecourse.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.wecourse.dynamic_course.FragmentDynamic;
import com.wecourse.faxian.Faxian;

public class MainActivity extends Activity implements BottomLayout.BottomLayoutOnClick {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private TabLayout lay_title;
    private FrameLayout frameLayout;
    private ViewPager lay_dynamic_course;
    private BottomLayout lay_bottom;

    private Fragment[] oldFragment = new Fragment[5];
    private Faxian  faxian, xiaoxi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        frameLayout = (FrameLayout) findViewById(R.id.lay_fragment);

        lay_dynamic_course = (ViewPager) findViewById(R.id.lay_dynamic_course);
        MainAdapter adapter = new MainAdapter(fragmentManager);
        lay_dynamic_course.setAdapter(adapter);

        lay_title = (TabLayout) findViewById(R.id.lay_title);
        lay_title.setTabMode(TabLayout.MODE_SCROLLABLE);
        lay_title.setupWithViewPager(lay_dynamic_course);

        //定义这五个界面
        faxian = new Faxian();
        xiaoxi = new Faxian();

        lay_bottom = (BottomLayout) findViewById(R.id.lay_bottom);
        lay_bottom.setBottomLayout(this);
    }

    @Override
    public void bottom1NearOnClick() {
        setLay_title(new String[]{"动态","课程表"});
        //将fragment移除掉
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(oldFragment[0]).commit();
        lay_dynamic_course.setVisibility(ViewPager.VISIBLE);
    }

    @Override
    public void bottom2FaxianOnClick() {
        setLay_title(new String[]{"发现"});
        lay_dynamic_course.setVisibility(ViewPager.INVISIBLE);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lay_fragment, faxian);
        fragmentTransaction.commit();
        oldFragment[0] = faxian;
    }

    @Override
    public void bottom3XiaoxiOnClick() {
        lay_dynamic_course.setVisibility(ViewPager.INVISIBLE);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lay_fragment,xiaoxi).commit();
        oldFragment[0] = xiaoxi;
    }

    @Override
    public void bottom4GuanzhuOnClick() {

    }

    @Override
    public void bottom5GerenOnClick() {

    }

    private void setLay_title(String[] titles) {
        TabLayout title = lay_title;
        title.removeAllTabs();
        int length = titles.length;
        if (length == 1) {
            title.setSelectedTabIndicatorHeight(0);
        }
        for (int i = 0; i < titles.length; i++) {
            title.addTab(title.newTab().setText(titles[i]));
        }
    }

    //定义ViewPager的Adapter
    class MainAdapter extends FragmentPagerAdapter{
        //定义多少个Tab页标签以及其内容
        final private int PAGE_COUNT = 2;
        private String tabTitles[] = new String[]{"动态","课程表"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new FragmentDynamic();
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
