package com.yj.kakao.afeel.login.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.login.Dialog.AreaSelectDialog;
import com.yj.kakao.afeel.login.Dialog.IdealSelectDialog;
import com.yj.kakao.afeel.login.RegisterActivity;


public class SecondFragment extends Fragment implements View.OnClickListener {

    FrameLayout fly_Height;
    int iWidthStep = 0;

    TextView tv_min, tv_max;
    CrystalRangeSeekbar seekbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        initView(v);
        return v;
    }

    private void initView(View v) {

        tv_min = (TextView) v.findViewById(R.id.tv_min_height);
        tv_max = (TextView) v.findViewById(R.id.tv_max_height);

        seekbar = (CrystalRangeSeekbar) v.findViewById(R.id.seekbar1);
        seekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tv_min.setText("" + (minValue.intValue() / 10 * 5 + 150));
                tv_max.setText("" + (maxValue.intValue() / 10 * 5 + 150));

                if (iWidthStep > 0) {
                    FrameLayout.LayoutParams llp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    llp.setMargins(minValue.intValue() / 10 * iWidthStep + 24, 0, 0, 0);
                    tv_min.setLayoutParams(llp);

                    FrameLayout.LayoutParams llp1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    llp1.setMargins(maxValue.intValue() / 10 * iWidthStep + 24, 0, 0, 0);
                    tv_max.setLayoutParams(llp1);
                }
            }
        });


        fly_Height = (FrameLayout) v.findViewById(R.id.fly_height);
        fly_Height.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                fly_Height.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                iWidthStep = (fly_Height.getMeasuredWidth() - 120) / 10;

                seekbar.setMinValue((float) 0.0).setMaxValue((float) 100.0).setSteps((float) 10.0).setGap((float) 10.0).setMinStartValue((float) 20.0).setMaxStartValue((float) 60.0).apply();
            }
        });

        v.findViewById(R.id.tv_area).setOnClickListener(this);
        v.findViewById(R.id.tv_blood).setOnClickListener(this);
        v.findViewById(R.id.tv_religion).setOnClickListener(this);
        v.findViewById(R.id.tv_status).setOnClickListener(this);
        v.findViewById(R.id.tv_next).setOnClickListener(this);
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
            case R.id.tv_status:
                new IdealSelectDialog(getActivity(), "상태", getActivity().getResources().getStringArray(R.array.array_status), 2).show();
                break;
            case R.id.tv_next:
                ((RegisterActivity) getActivity()).goStep3();
                break;
        }
    }
}
