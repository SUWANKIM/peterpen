package com.yj.kakao.afeel.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.BaseActivity;
import com.yj.kakao.afeel.MyConfirmDialog;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.main.Dialog.EditDialog;
import com.yj.kakao.afeel.model.Community;

import java.util.ArrayList;

public class ProfileEditActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        ((RippleView) findViewById(R.id.imv_back)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                finish();
            }
        });

        ((RippleView) findViewById(R.id.rv_next)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(ProfileEditActivity.this, ProfileVerifyActivity.class);
                startActivity(intent);
            }
        });
    }
}
