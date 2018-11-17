package com.yj.kakao.afeel.login.Fragment;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.MyAlertDialog;
import com.yj.kakao.afeel.MyConfirmDialog;
import com.yj.kakao.afeel.SelectDialog;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.login.PhotoRegActivity;


public class FifthFragment extends Fragment implements RippleView.OnRippleCompleteListener {

    ImageView imv_pic1, imv_pic2, imv_pic3, imv_pic4, imv_pic5, imv_university, imv_job;
    TextView tv_university, tv_job;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fifth, container, false);

        initView(v);
        return v;
    }

    private void initView(View v) {
        ((RippleView) v.findViewById(R.id.rv_pic1)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_pic2)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_pic3)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_pic4)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_pic5)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_university)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_job)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.tv_register)).setOnRippleCompleteListener(this);

        imv_pic1 = (ImageView) v.findViewById(R.id.imv_pic1);
        imv_pic2 = (ImageView) v.findViewById(R.id.imv_pic2);
        imv_pic3 = (ImageView) v.findViewById(R.id.imv_pic3);
        imv_pic4 = (ImageView) v.findViewById(R.id.imv_pic4);
        imv_pic5 = (ImageView) v.findViewById(R.id.imv_pic5);
        imv_university = (ImageView) v.findViewById(R.id.imv_university);
        imv_job = (ImageView) v.findViewById(R.id.imv_job);

        tv_university = (TextView) v.findViewById(R.id.tv_university);
        tv_job = (TextView) v.findViewById(R.id.tv_job);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.rv_pic1:
                showPhotoDlg(1);
                break;
            case R.id.rv_pic2:
                showPhotoDlg(2);
                break;
            case R.id.rv_pic3:
                showPhotoDlg(3);
                break;
            case R.id.rv_pic4:
                showPhotoDlg(4);
                break;
            case R.id.rv_pic5:
                showVerifyDlg();
                break;
            case R.id.rv_university:
                showUniversityDlg();
                break;
            case R.id.rv_job:
                showJobDlg();
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }

    private void register() {
        MyConfirmDialog dlg = new MyConfirmDialog(getActivity());
        dlg.setImageVisible(true);
        dlg.setContentVisible(false);
        dlg.setTitleVisible(true);
        dlg.setImageRes(R.drawable.icon_write);
        dlg.setTitleText("진심을 담아 쓰셨나요?\n\n프로필 수정 시 재 승인을 받아야 하며,\n승인 후 이용이 가능합니다.\n\n진행하시겠습니까?", 5, 0, 9);
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        dlg.show();
    }

    private void showUniversityDlg() {
        new SelectDialog(getActivity(), "학교", "수정", "삭제", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyConfirmDialog dlg = new MyConfirmDialog(getActivity());
                dlg.setImageVisible(false);
                dlg.setContentVisible(false);
                dlg.setTitleVisible(true);
                dlg.setTitleText("정말 삭제 하시겠습니까?", 0, 3, 5);
                dlg.setClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                dlg.show();
            }
        }).show();
    }

    private void showJobDlg() {
        /*
        new SelectDialog(getActivity(), "직장", "수정", "삭제", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).show();*/

        Intent intent = new Intent(getActivity(), PhotoRegActivity.class);
        startActivityForResult(intent, 100);
    }

    private void showPhotoDlg(int idx) {
        new SelectDialog(getActivity(), "프로필", "사진 찍기", "사진 앨범에서 선택", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        }).show();
    }

    private void showVerifyDlg() {
        MyAlertDialog dlg = new MyAlertDialog(getActivity());
        dlg.setImageVisible(true);
        dlg.setContentVisible(true);
        dlg.setTitleVisible(true);
        dlg.setImageRes(R.drawable.icon_verify);
        dlg.setTitleText("타인 사진 도용을 막기 위해\n자신의 아이디를 종이에 적어 자신의\n얼굴과 같이 찍어주셔야 인정됩니다.", 1, 0, 15);
        dlg.setContentText("인증자료는 절대 타인에게 공개되지 않으며\n인증을 위해서만 비공개로 확인됩니다.");
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPhotoDlg(5);
            }
        });
        dlg.show();
    }
}
