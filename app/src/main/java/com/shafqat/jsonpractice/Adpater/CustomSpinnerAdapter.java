package com.shafqat.jsonpractice.Adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shafqat.jsonpractice.R;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {

    List<String> keys;
    Context context;

    public CustomSpinnerAdapter(List<String> keys, Context context) {
        this.keys = keys;
        this.context = context;
    }

    @Override
    public int getCount() {
        return keys.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.classyearitem,parent,false);
        }
        TextView clasYear = (TextView) convertView.findViewById(R.id.classTitle);
        clasYear.setText(keys.get(position));
        return convertView;
    }
}
