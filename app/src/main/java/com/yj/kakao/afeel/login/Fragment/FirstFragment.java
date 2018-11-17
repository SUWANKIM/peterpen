package com.yj.kakao.afeel.login.Fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.login.PolicyActivity;
import com.yj.kakao.afeel.login.RegisterActivity;


public class FirstFragment extends Fragment implements RippleView.OnRippleCompleteListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        initView(v);
        return v;
    }

    private void initView(View v) {
        ((RippleView) v.findViewById(R.id.tv_next)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.tv_policy_view_1)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.tv_policy_view_2)).setOnRippleCompleteListener(this);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        Intent intent = null;
        switch (rippleView.getId()) {
            case R.id.tv_next:
                ((RegisterActivity) getActivity()).goStep2();
                break;
            case R.id.tv_policy_view_1:
                intent = new Intent(getActivity(), PolicyActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.tv_policy_view_2:
                intent = new Intent(getActivity(), PolicyActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
