package com.shafqat.jsonpractice.Adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shafqat.jsonpractice.Model.ClassYear;
import com.shafqat.jsonpractice.Model.Data;
import com.shafqat.jsonpractice.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ClassYear> {

    List<ClassYear> data;
    Context context;
    public ListViewAdapter(@NonNull Context context, int resource, List<ClassYear> data) {
        super(context, resource);
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClassYear classData = data.get(position);
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView student_id = (TextView) convertView.findViewById(R.id.student_id);
        TextView parent_id = (TextView) convertView.findViewById(R.id.parent_id);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView phone = (TextView) convertView.findViewById(R.id.phone);
        TextView address = (TextView) convertView.findViewById(R.id.address);
        title.setText("");
        student_id.setText("Student Id:"+classData.getStudentId());
        parent_id.setText("Parent Id:"+classData.getParentId());
        name.setText("Name:"+classData.getName());
        phone.setText("phone:"+classData.getPhone());
        address.setText("Address:"+classData.getAddress());
        return convertView;
    }

    public void setData(List<ClassYear> getClassDataList) {
        data.clear();
        data.addAll(getClassDataList);
        notifyDataSetChanged();
    }
}
