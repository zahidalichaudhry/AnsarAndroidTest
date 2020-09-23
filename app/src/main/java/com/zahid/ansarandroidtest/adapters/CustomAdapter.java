package com.zahid.ansarandroidtest.adapters;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zahid.ansarandroidtest.R;
import com.zahid.ansarandroidtest.model.CatApiResponseModel;
import com.zahid.ansarandroidtest.model.Result;
import com.zahid.ansarandroidtest.model.SubCategory;

import java.util.ArrayList;

public class CustomAdapter extends BaseExpandableListAdapter {

    private Context c;
    private ArrayList<Result> categories;
    private LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Result> categories)
    {
        this.c=c;
        this.categories=categories;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //GET A SINGLE PLAYER
    @Override
    public Object getChild(int groupPos, int childPos) {
        return categories.get(groupPos).getSubCategories().get(childPos);
    }

    //GET PLAYER ID
    @Override
    public long getChildId(int arg0, int arg1) {
        return 0;
    }

    //GET PLAYER ROW
    @Override
    public View getChildView(int groupPos, int childPos, boolean isLastChild, View convertView,
                             ViewGroup parent) {

        //ONLY INFLATER XML ROW LAYOUT IF ITS NOT PRESENT,OTHERWISE REUSE IT

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.sub_cat_item, null);
        }

        //GET CHILD/PLAYER NAME
        SubCategory child=(SubCategory) getChild(groupPos, childPos);

        //SET CHILD NAME
        TextView nameTv=(TextView) convertView.findViewById(R.id.tv_title);
        ImageView img=(ImageView) convertView.findViewById(R.id.imageView);

        nameTv.setText(child.getTitle());



        return convertView;
    }

    //GET NUMBER OF PLAYERS
    @Override
    public int getChildrenCount(int groupPosw) {

        return categories.get(groupPosw).getSubCategories().size();
    }

    //GET TEAM
    @Override
    public Object getGroup(int groupPos) {
        return categories.get(groupPos);
    }

    //GET NUMBER OF TEAMS
    @Override
    public int getGroupCount() {
        return categories.size();
    }

    //GET TEAM ID
    @Override
    public long getGroupId(int arg0) {
        return 0;
    }

    //GET TEAM ROW
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        //ONLY INFLATE XML TEAM ROW MODEL IF ITS NOT PRESENT,OTHERWISE REUSE IT
        if(convertView == null)
        {
            convertView=inflater.inflate(R.layout.main_cat_item, null);
        }

        //GET GROUP/TEAM ITEM
        Result cat=(Result) getGroup(groupPosition);

        //SET GROUP NAME
        TextView catName=(TextView) convertView.findViewById(R.id.tv_title);
        ImageView img=(ImageView) convertView.findViewById(R.id.image);
        catName.setText(cat.getTitle());

        //ASSIGN TEAM IMAGES ACCORDING TO TEAM NAME



        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}