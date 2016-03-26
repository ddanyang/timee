package com.wecourse.dynamic_course;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wecourse.main.R;

/**
 * Created by ddany on 2016/3/26.
 */
public class DynamicFragmentAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;

    public DynamicFragmentAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dc_fragment_dynamic_item, null);
        }
        return convertView;
    }

    private class ViewHolder{

    }
}
