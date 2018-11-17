package com.yj.kakao.afeel.login.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.login.Dialog.AreaSelectDialog;
import com.yj.kakao.afeel.login.Dialog.IdealSelectDialog;
import com.yj.kakao.afeel.login.RegisterActivity;


public class FourthFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fourth, container, false);

        initView(v);
        return v;
    }

    private void initView(View v) {
        v.findViewById(R.id.tv_area).setOnClickListener(this);
        v.findViewById(R.id.tv_blood).setOnClickListener(this);
        v.findViewById(R.id.tv_religion).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_area:
                new AreaSelectDialog(getActivity(), 0, 1).show();
                break;
            case R.id.tv_blood:
                new IdealSelectDialog(getActivity(), "혈액형", getActivity().getResources().getStringArray(R.array.array_blood), 3).show();
                break;
            case R.id.tv_religion:
                new IdealSelectDialog(getActivity(), "종교", getActivity().getResources().getStringArray(R.array.array_religion), 1).show();
                break;
        }
    }
}
