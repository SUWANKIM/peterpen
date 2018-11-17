package com.example.lggram.peterpan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.viewpagerindicator.CirclePageIndicator;
import com.yj.kakao.afeel.BaseActivity;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.ImageSlidingAdapter;

import java.util.ArrayList;

public class TutorialActivity extends BaseActivity {

    // 종료 기발
    private boolean m_bFinish = false;

    ViewPager m_uiVwpShopPhoto;
    CirclePageIndicator mHelpIndicator;
    private ArrayList<Integer> m_arrPhotoName = new ArrayList<>();

    RippleView tvStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        m_uiVwpShopPhoto = (ViewPager) findViewById(R.id.vwp_shop_photo);
        mHelpIndicator = (CirclePageIndicator) findViewById(R.id.cpi_photo);

        m_arrPhotoName.add(R.drawable.bg_tutorial_1);
        m_arrPhotoName.add(R.drawable.bg_tutorial_2);
        m_arrPhotoName.add(R.drawable.bg_tutorial_3);

        ImageSlidingAdapter adapter = new ImageSlidingAdapter(this, m_arrPhotoName, 1);
        m_uiVwpShopPhoto.setAdapter(adapter);
        mHelpIndicator.setViewPager(m_uiVwpShopPhoto);

        tvStart = (RippleView) findViewById(R.id.tv_start);
        tvStart.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(RippleView rippleView) {
                SharedPreferences pref = TutorialActivity.this.getSharedPreferences("AFeel", 0);
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean("tutorial", true);
                edit.commit();

                Intent intent = new Intent(TutorialActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setPhotoPage(int pos) {
        if (pos == m_arrPhotoName.size() - 1) {
            tvStart.setVisibility(View.VISIBLE);
        } else {
            tvStart.setVisibility(View.GONE);
        }
    }

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
}
