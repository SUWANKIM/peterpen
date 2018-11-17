package com.yj.kakao.afeel;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.common.OptAnimationLoader;

public class SelectDialog extends Dialog {
    private Context _context;
    private View.OnClickListener m_listener1 = null, m_listener2 = null;

    private View mDialogView;
    private AnimationSet mModalInAnim;

    public SelectDialog(Context context, String title, String text1, String text2, View.OnClickListener listener1, View.OnClickListener listener2) {
        super(context, R.style.alert_dialog);
        _context = context;
        m_listener1 = listener1;
        m_listener2 = listener2;
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_select);

        ((RippleView) findViewById(R.id.tv_cancel)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                dismiss();
            }
        });

        ((RippleView) findViewById(R.id.rv_btn1)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (m_listener1 != null)
                    m_listener1.onClick(null);
                dismiss();
            }
        });

        ((RippleView) findViewById(R.id.rv_btn2)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (m_listener2 != null)
                    m_listener2.onClick(null);
                dismiss();
            }
        });

        ((TextView) findViewById(R.id.tv_title)).setText(title);
        ((TextView) findViewById(R.id.tv_btn1)).setText(text1);
        ((TextView) findViewById(R.id.tv_btn2)).setText(text2);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
    }

    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }
}
