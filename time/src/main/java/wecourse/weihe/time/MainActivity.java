package wecourse.weihe.time;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.iflytek.sunflower.FlowerCollector;

import wecourse.special.view.VoiceContralView;

public class MainActivity extends Activity {

    private ListView listView;
    VoiceContralView voice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.activity_list);
        voice = (VoiceContralView) findViewById(R.id.voice);

        MainActivityAdapter adapter = new MainActivityAdapter(this);
        listView.setAdapter(adapter);
        listView.requestLayout();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                synchronized (voice) {
//                    voice.onResume();
//                }
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        FlowerCollector.onResume(this);
        FlowerCollector.setDebugMode(true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        FlowerCollector.onPause(this);
    }
}
