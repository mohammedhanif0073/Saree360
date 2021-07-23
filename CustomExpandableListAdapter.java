package com.example.saree360.AdapterClass;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saree360.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<MenuModel> headerlist;
    private HashMap<MenuModel, List<String>> childlist;
    ExpandableListView expandList;

    public CustomExpandableListAdapter(Context context, List<MenuModel> headerlist,
                                 HashMap<MenuModel, List<String>> childlist,ExpandableListView mView) {
        this.context = context;
        this.headerlist= headerlist;
        this.childlist = childlist;
        this.expandList = mView;
    }

    @Override
    public int getGroupCount() {

        int i = headerlist.size();
        Log.d("GROUPCOUNT", String.valueOf(i));
        return this.headerlist.size();

    }
    @Override
    public int getChildrenCount(int groupPosition) {
        int childCount = 0;
        if (groupPosition != 5) {
            childCount = this.childlist.get(this.headerlist.get(groupPosition))
                    .size();
        }
        return childCount;
//        return this.childlist.get(this.headerlist.get(groupPosition))
//                    .size();
    }

    @Override
    public MenuModel getGroup(int groupPosition) {
        return this.headerlist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        Log.d("CHILD", childlist.get(this.headerlist.get(groupPosition))
                .get(childPosititon).toString());
        return this.childlist.get(this.headerlist.get(groupPosition))
                .get(childPosititon);
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        MenuModel headerTitle = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_header, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView headerIcon = (ImageView) convertView.findViewById(R.id.iconimage);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        //lblListHeader.setText((CharSequence) headerTitle);
        lblListHeader.setText(headerTitle.getIconName());
        headerIcon.setImageResource(headerTitle.getIconImg());
        Log.d("Header","Icon"+headerIcon);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_child, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}