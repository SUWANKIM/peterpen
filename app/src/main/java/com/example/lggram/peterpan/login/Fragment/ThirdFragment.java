package com.yj.kakao.afeel.login.Fragment;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lunger.draglistview.DragListAdapter;
import com.lunger.draglistview.DragListView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.adapter.MyAdapter;
import com.yj.kakao.afeel.login.RegisterActivity;

import java.util.ArrayList;


public class ThirdFragment extends Fragment implements View.OnClickListener {

    DragListView mDragListView;
    private ArrayList<String> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_third, container, false);

        initView(v);
        return v;
    }

    private void initView(View v) {
        mDragListView = (DragListView) v.findViewById(R.id.lsv_ideal);
        mDatas = new ArrayList<>();

        String[] arr = getActivity().getResources().getStringArray(R.array.array_ideal);
        for (int i = 0; i < arr.length; i++) {
            mDatas.add(arr[i]);
        }

        mDragListView.setDragListAdapter(new MyAdapter(getActivity(), mDatas));
        mDragListView.setDragger(R.id.imv_move);
        mDragListView.setItemFloatColor("#1dc9c6");
        mDragListView.setItemFloatAlpha(0.65f);
        mDragListView.setMyDragListener(new DragListView.MyDragListener() {
            @Override
            public void onDragFinish(int srcPositon, int finalPosition) {
            }
        });

        v.findViewById(R.id.tv_next).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_next:
                ((RegisterActivity) getActivity()).goStep4();
                break;
        }
    }
}
