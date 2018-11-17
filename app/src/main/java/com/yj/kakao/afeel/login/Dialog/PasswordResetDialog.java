package com.yj.kakao.afeel.login.Dialog;

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
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.OptAnimationLoader;

public class PasswordResetDialog extends Dialog {
    private Context _context;
    private View.OnClickListener m_listener = null;

    private View mDialogView;
    private AnimationSet mModalInAnim;

    public PasswordResetDialog(Context context) {
        super(context, R.style.alert_dialog);
        _context = context;
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_password_reset);

        ((RippleView) findViewById(R.id.tv_ok)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                if (m_listener != null)
                    m_listener.onClick(null);
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

    public void setClickListener(View.OnClickListener listener) {
        m_listener = listener;
    }
}
