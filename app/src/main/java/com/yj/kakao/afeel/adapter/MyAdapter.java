package com.yj.kakao.afeel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunger.draglistview.DragListAdapter;
import com.yj.kakao.afeel.R;

import java.util.ArrayList;

/**
 * Created by Puma on 11/18/2016.
 */

public class MyAdapter extends DragListAdapter {

    Context _context;
    ArrayList<String> mDatas;

    public MyAdapter(Context context, ArrayList<String> arrayTitles) {
        super(context, arrayTitles);
        _context = context;
        mDatas = arrayTitles;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        view = LayoutInflater.from(_context).inflate(
                R.layout.list_row_ideal_order, null);

        TextView textView = (TextView) view.findViewById(R.id.tv_ideal);
        textView.setText(mDatas.get(position));

        TextView orderView = (TextView) view.findViewById(R.id.tv_order);
        orderView.setText("" + (position + 1) + ".");
        return view;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
