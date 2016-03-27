package com.wecourse.dynamic_course;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wecourse.main.R;

/**
 * Created by ddany on 2016/3/25.
 */
public class DynamicFragment extends Fragment{

    private Context context;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dc_fragment_dynamic,null);
        listView = (ListView) view.findViewById(R.id.list);

        DynamicFragmentAdapter adapter = new DynamicFragmentAdapter(context);
        listView.setAdapter(adapter);

        return view;
    }
}
