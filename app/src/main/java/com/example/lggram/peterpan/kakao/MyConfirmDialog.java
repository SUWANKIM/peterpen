package com.yj.kakao.afeel;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.common.OptAnimationLoader;

public class MyConfirmDialog extends Dialog {
    private Context _context;
    private View.OnClickListener m_listener = null;

    private View mDialogView;
    private AnimationSet mModalInAnim;

    TextView tv_title;

    public MyConfirmDialog(Context context) {
        super(context, R.style.alert_dialog);
        _context = context;
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_my_confirm);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("");

        ((RippleView) findViewById(R.id.tv_ok)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (m_listener != null)
                    m_listener.onClick(null);
                dismiss();
            }
        });

        ((RippleView) findViewById(R.id.tv_cancel)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                dismiss();
            }
        });

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
    }

    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }

    public void setImageVisible(Boolean bShow) {
        if (bShow)
            findViewById(R.id.imv_image).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.imv_image).setVisibility(View.GONE);
    }

    public void setImageRes(int resourceId) {
        ((ImageView) findViewById(R.id.imv_image)).setImageResource(resourceId);
    }

    public void setTitleVisible(Boolean bShow) {
        if (bShow)
            findViewById(R.id.tv_title).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.tv_title).setVisibility(View.GONE);
    }

    public void setContentVisible(Boolean bShow) {
        if (bShow)
            findViewById(R.id.tv_content).setVisibility(View.VISIBLE);
        else
            findViewById(R.id.tv_content).setVisibility(View.GONE);
    }

    public void setTitleText(String title, int index, int start, int end) {
        if (tv_title.getText().toString().length() != 0)
            tv_title.setText(TextUtils.concat(tv_title.getText(), "\n"));

        String[] arr = title.split("\n");

        for (int i = 0; i < arr.length; i++) {
            SpannableString spanText = new SpannableString(arr[i]);
            if (i == index) {
                spanText.setSpan(new ForegroundColorSpan(0xFF1DC9C6), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                spanText.setSpan(new ForegroundColorSpan(0xFF444444), 0, arr[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (i != arr.length - 1)
                tv_title.setText(TextUtils.concat(tv_title.getText(), spanText, "\n"));
            else
                tv_title.setText(TextUtils.concat(tv_title.getText(), spanText));
        }
    }

    public void setContentText(String content) {
        ((TextView) findViewById(R.id.tv_content)).setText(content);
    }

    public void setClickListener(View.OnClickListener listener) {
        m_listener = listener;
    }
}
