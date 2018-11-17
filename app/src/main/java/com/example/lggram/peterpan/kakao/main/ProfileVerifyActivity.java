package com.yj.kakao.afeel.main;

import android.os.Bundle;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.BaseActivity;
import com.yj.kakao.afeel.R;

public class ProfileVerifyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_verify);

        ((RippleView) findViewById(R.id.imv_back)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                finish();
            }
        });

        ((RippleView) findViewById(R.id.rv_register)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
            }
        });
    }
}
