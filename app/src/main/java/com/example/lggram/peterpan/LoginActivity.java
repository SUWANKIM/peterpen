package com.example.lggram.peterpan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.rey.material.widget.EditText;
import com.yj.kakao.afeel.BaseActivity;
import com.yj.kakao.afeel.MyAlertDialog;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.http_conn.manager.CommDelegate;
import com.yj.kakao.afeel.http_conn.manager.CommManager;
import com.yj.kakao.afeel.login.Dialog.EventDialog;
import com.yj.kakao.afeel.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LoginActivity extends BaseActivity implements RippleView.OnRippleCompleteListener {

    // 종료 기발
    private boolean m_bFinish = false;

    EditText edt_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getEvent();

        initView();
    }

    private void initView() {
        ((RippleView) findViewById(R.id.tv_find_id)).setOnRippleCompleteListener(this);
        ((RippleView) findViewById(R.id.tv_register)).setOnRippleCompleteListener(this);
        ((RippleView) findViewById(R.id.tv_find_pwd)).setOnRippleCompleteListener(this);
        ((RippleView) findViewById(R.id.tv_login)).setOnRippleCompleteListener(this);

        edt_mail = (EditText) findViewById(R.id.edt_email);
        edt_mail.setError("error");
    }

    private void getEvent() {
        showProgress();
        CommManager.getEvent(delegate);
    }

    CommDelegate delegate = new CommDelegate() {

        @Override
        public void getEventResult(int retcode,
                                   String retmsg,
                                   JSONObject retdata) {
            closeProgress();

            try {
                String image_url = retdata.getString("image_url");

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                SharedPreferences pref = getSharedPreferences("AFeel", 0);
                Boolean bEvent = pref.getBoolean("event_" + formattedDate, false);

                if (!bEvent) {
                    EventDialog mDialog = new EventDialog(LoginActivity.this);
                    WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
                    params.width = LinearLayout.LayoutParams.MATCH_PARENT;
                    params.height = LinearLayout.LayoutParams.MATCH_PARENT;
                    mDialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
                    mDialog.setImage(CommManager.getServerUrl() + image_url);
                    mDialog.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void onBackPressed() {
        if (!m_bFinish) {
            m_bFinish = true;
            Toast.makeText(this,
                    getResources().getString(R.string.app_finish_message),
                    Toast.LENGTH_SHORT).show();
            m_hndBackKey.sendEmptyMessageDelayed(0, 2000);
        } else {
            // 앱을 종료한다.
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    // BACK key handler
    Handler m_hndBackKey = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0)
                m_bFinish = false;
        }
    };

    @Override
    public void onComplete(RippleView rippleView) {
        Intent intent = null;
        switch (rippleView.getId()) {
            case R.id.tv_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_find_pwd:
                intent = new Intent(this, FindPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_find_id:
                break;
            case R.id.tv_login:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
