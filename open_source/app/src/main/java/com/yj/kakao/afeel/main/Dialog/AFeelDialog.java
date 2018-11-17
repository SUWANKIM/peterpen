package com.yj.kakao.afeel.main.Dialog;

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
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.OptAnimationLoader;

public class AFeelDialog extends Dialog {

    public final static int USE_HEART = 0;
    public final static int USE_POINT = 1;

    public final static int USE_HEART_IDEAL = 2;
    public final static int USE_POINT_IDEAL = 3;

    public final static int BUY_HEART_WITH_CARD = 4;
    public final static int BUY_HEART_WITH_POINT = 5;

    public final static int BUY_COUPON_WITH_HEART = 6;

    public final static int BUY_CARD_WITH_HEART = 7;

    private View.OnClickListener m_listener = null;

    private View mDialogView;
    private AnimationSet mModalInAnim;

    public AFeelDialog(Context context, int type) {
        super(context, R.style.alert_dialog);
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_afeel);

        if (type == USE_HEART)
            ((TextView) findViewById(R.id.tv_content)).setText("하트 20개가 소모되며,\n사용한 하트는 환불되지 않습니다.");
        else if (type == USE_POINT)
            ((TextView) findViewById(R.id.tv_content)).setText("200 포인트 소모되며,\n사용한 포인트는 환불되지 않습니다.");
        else if (type == USE_HEART_IDEAL) {
            ((TextView) findViewById(R.id.tv_title)).setText("이상형 더 보기 하시겠습니까?");
            ((TextView) findViewById(R.id.tv_content)).setText("하트 10개가 소모되며,\n사용한 하트는 환불되지 않습니다.");
            findViewById(R.id.fly_input).setVisibility(View.GONE);
        } else if (type == USE_POINT_IDEAL) {
            ((TextView) findViewById(R.id.tv_title)).setText("이상형 더 보기 하시겠습니까?");
            ((TextView) findViewById(R.id.tv_content)).setText("100 포인트 소모되며,\n사용한 포인트는 환불되지 않습니다.");
            findViewById(R.id.fly_input).setVisibility(View.GONE);
        } else if (type == BUY_HEART_WITH_CARD) {
            ((TextView) findViewById(R.id.tv_alert)).setText("스토어");
            ((TextView) findViewById(R.id.tv_title)).setText("결제하시겠습니까?");
            ((TextView) findViewById(R.id.tv_content)).setText("해당 상품은 5,500원이며,\n결제 후 환불되지 않습니다.");
            findViewById(R.id.fly_input).setVisibility(View.GONE);
        } else if (type == BUY_HEART_WITH_POINT) {
            ((TextView) findViewById(R.id.tv_alert)).setText("스토어");
            ((TextView) findViewById(R.id.tv_title)).setText("결제하시겠습니까?");
            ((TextView) findViewById(R.id.tv_content)).setText("해당 상품은 5,000포인트이며,\n결제 후 환불되지 않습니다.");
            findViewById(R.id.fly_input).setVisibility(View.GONE);
            findViewById(R.id.lly_point).setVisibility(View.VISIBLE);
        } else if (type == BUY_COUPON_WITH_HEART) {
            ((TextView) findViewById(R.id.tv_alert)).setText("하트 사용하기");
            ((TextView) findViewById(R.id.tv_title)).setText("결제하시겠습니까?");
            ((TextView) findViewById(R.id.tv_content)).setText("하트 130개가 소모되며\n사용한 하트는 환불되지 않습니다.");
            findViewById(R.id.fly_input).setVisibility(View.GONE);
            findViewById(R.id.lly_heart).setVisibility(View.VISIBLE);
        } else if (type == BUY_CARD_WITH_HEART) {
            ((TextView) findViewById(R.id.tv_alert)).setText("카드 구매하기");
            ((TextView) findViewById(R.id.tv_title)).setText("결제하시겠습니까?");
            ((TextView) findViewById(R.id.tv_content)).setText("하트 10개가 소모되며\n결제 후 환불되지 않습니다.");
            findViewById(R.id.fly_input).setVisibility(View.GONE);
            findViewById(R.id.lly_heart).setVisibility(View.VISIBLE);
        }

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

    public void setClickListener(View.OnClickListener listener) {
        m_listener = listener;
    }
}
