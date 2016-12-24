package wecourse;

import android.app.Application;

import com.iflytek.cloud.SpeechUtility;

import wecourse.utils.db.CreateOrUpDB;
import wecourse.utils.db.Static_DB;

/**
 * Created by ddany on 2016/5/3.
 */
public class ApplicationTimer extends Application {

    public CreateOrUpDB sqliteHelper;

    @Override
    public void onCreate() {
        SpeechUtility.createUtility(ApplicationTimer.this, "appid=57271845");
        sqliteHelper = new CreateOrUpDB(ApplicationTimer.this);
        super.onCreate();
    }

}
