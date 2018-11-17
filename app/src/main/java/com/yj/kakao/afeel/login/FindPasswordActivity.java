package com.yj.kakao.afeel.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.BaseActivity;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.login.Dialog.PasswordResetDialog;

public class FindPasswordActivity extends BaseActivity implements RippleView.OnRippleCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        initView();
    }

    private void initView() {
        ((RippleView) findViewById(R.id.tv_find_pwd)).setOnRippleCompleteListener(this);
        ((RippleView) findViewById(R.id.imv_back)).setOnRippleCompleteListener(this);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        Intent intent = null;
        switch (rippleView.getId()) {
            case R.id.imv_back:
                finish();
                break;
            case R.id.tv_find_pwd:
                showAlert();
                break;
        }
    }

    private void showAlert() {

        PasswordResetDialog dlg = new PasswordResetDialog(FindPasswordActivity.this);
        dlg.setCanceledOnTouchOutside(false);
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dlg.show();

    }
}
