/**
 * Copyright (C) 2013 Samsung Electronics Co., Ltd. All rights reserved.
 *
 * Mobile Communication Division,
 * Digital Media & Communications Business, Samsung Electronics Co., Ltd.
 *
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 *
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 */

package com.angelhack.oxygen.adapter;

import java.util.ArrayList;

import com.angelhack.oxygen.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class NodeListAdapter extends BaseAdapter {

    private Context mContext = null;

    private ArrayList<NodeInfo> mNodeInfoList = null;

    private LayoutInflater mInflater = null;

    private ViewHolder mViewHolder = null;


    private static final String TAG = "[Oxygen][NodeListAdapter]";

    class ViewHolder {
        TextView textView;
    }

    class NodeInfo {

        String nodeName = "";

        NodeInfo(String nodeName) {
            this.nodeName = nodeName;
        }
    }

    public NodeListAdapter(Context context) {
        super();
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mNodeInfoList = new ArrayList<NodeInfo>();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mNodeInfoList.size();
    }

    @Override
    public NodeInfo getItem(int position) {
        if(position >= mNodeInfoList.size()){
            return null;
        }
        
        return mNodeInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getNodeName(int position) {
        return mNodeInfoList.get(position).nodeName;
    }

    public void addNode(String nodeName) {

        NodeInfo nodeInfo = new NodeInfo(nodeName);
        mNodeInfoList.add(nodeInfo);
        notifyDataSetChanged();
    }

    public void removeNode(String nodeName) {
        NodeInfo info = getNodeInfo(nodeName);
        if (null == info) {
            return;
        }

        mNodeInfoList.remove(info);
        notifyDataSetChanged();
    }

    private NodeInfo getNodeInfo(String nodeName) {
        if (mNodeInfoList.isEmpty()) {
            return null;
        }

        for (NodeInfo info : mNodeInfoList) {
            if (info.nodeName.equals(nodeName)) {
                return info;
            }
        }

        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View v = convertView;

        if (v == null) {
            mViewHolder = new ViewHolder();
            v = mInflater.inflate(R.layout.node_listitem, parent, false);
            mViewHolder.textView = (TextView)v.findViewById(R.id.nodeName_textview);
            v.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder)v.getTag();
        }

        final NodeInfo nodeInfo = mNodeInfoList.get(position);
        mViewHolder.textView.setText(nodeInfo.nodeName);

      

        return v;
    }

}
