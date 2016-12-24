package wecourse.weihe.time;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by ddany on 2016/4/25.
 */
public class MainActivityAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;

    public MainActivityAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 30;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.time_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_context = (TextView) convertView.findViewById(R.id.tv_context);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_context.setText(position+"");

        return convertView;
    }

    static class ViewHolder{
        TextView tv_context;
    }
}
