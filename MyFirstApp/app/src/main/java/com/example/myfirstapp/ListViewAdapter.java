package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathamesh 1 on 07-05-2017.
 */

public class ListViewAdapter extends ArrayAdapter<String> {
    private Activity activity;
    private List<String> friendList;
    private List<String> searchList;

    public ListViewAdapter(long_Click context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.friendList = objects;
        this.searchList = new ArrayList<>();
        this.searchList.addAll(friendList);
    }

    @Override
    public int getCount() {
        return friendList.size();
    }

    @Override
    public String getItem(int position) {
        return friendList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }
        holder.friendName.setText(getItem(position));
        return convertView;
    }

    private class ViewHolder {
        private TextView friendName;
        public ViewHolder(View v) {
            friendName = (TextView) v.findViewById(R.id.textView);
        }
    }
}
