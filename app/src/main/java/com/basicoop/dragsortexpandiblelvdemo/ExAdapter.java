package com.basicoop.dragsortexpandiblelvdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minfu on 13/4/16.
 */
public class ExAdapter extends BaseExpandableListAdapter {

    public List<ItemType> itemTypes = new ArrayList<ItemType>();
    private LayoutInflater inflater = null;

    public ExAdapter(List<ItemType> ItemType, Activity act){
        this.itemTypes = ItemType;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return this.itemTypes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.itemTypes.get(groupPosition).itemName.size();
    }

    @Override
    public ItemType getGroup(int groupPosition) {
        return itemTypes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.group, null);
        }

        TextView tv_title = (TextView) convertView.findViewById(R.id.group);
        tv_title.setText(itemTypes.get(groupPosition).itemType);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.child, null);
        }

        TextView tv_title = (TextView) convertView.findViewById(R.id.child);
        ItemType itemType = itemTypes.get(groupPosition);
        tv_title.setText(itemType.itemName.get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void insert(ItemType itemType, int position){
        itemTypes.add(position, itemType);
    }

    public void removeByPos(int position){
        itemTypes.remove(position);
    }
}
