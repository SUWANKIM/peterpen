package com.yj.kakao.afeel.main.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.NiceSpinner;
import com.yj.kakao.afeel.main.CommunityDetailActivity;
import com.yj.kakao.afeel.main.MainActivity;
import com.yj.kakao.afeel.model.Community;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class CommunityWriteFragment extends Fragment implements RippleView.OnRippleCompleteListener {

    private MainActivity m_act;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_community_write, container, false);

        m_act = (MainActivity) getActivity();
        initView(v);

        return v;
    }

    private void initView(View v) {
        ((RippleView) v.findViewById(R.id.imv_back)).setOnRippleCompleteListener(this);

        NiceSpinner niceSpinner = (NiceSpinner) v.findViewById(R.id.nice_spinner);
        List<String> dataset = new LinkedList<>(Arrays.asList("자유", "19", "펑예", "스압"));
        niceSpinner.attachDataSource(dataset);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        Intent intent = null;
        switch (rippleView.getId()) {
            case R.id.imv_back:
                m_act.selectFrag(m_act.FRAGMENT_COMMUNITY);
                break;
        }
    }
}
