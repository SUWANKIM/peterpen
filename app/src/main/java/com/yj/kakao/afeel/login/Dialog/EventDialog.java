package com.yj.kakao.afeel.login.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.andexert.library.RippleView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.victor.loading.rotate.RotateLoading;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.OptAnimationLoader;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventDialog extends Dialog {
    private Context _context;

    private ImageLoader m_imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions optionsImage = new DisplayImageOptions.Builder()
            .resetViewBeforeLoading(true)
            .cacheOnDisk(true)
            .imageScaleType(ImageScaleType.EXACTLY)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .displayer(new FadeInBitmapDisplayer(300))
            .build();

    private View mDialogView;
    private AnimationSet mModalInAnim;

    public EventDialog(Context context) {
        super(context, R.style.alert_dialog);
        _context = context;
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_event);

        ((RippleView) findViewById(R.id.tv_day_close)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());

                SharedPreferences pref = _context.getSharedPreferences("AFeel", 0);
                SharedPreferences.Editor edit = pref.edit();
                edit.putBoolean("event_" + formattedDate, true);
                edit.commit();

                dismiss();
            }
        });
        ((RippleView) findViewById(R.id.tv_close)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                dismiss();
            }
        });

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
    }

    public void setImage(String url) {
        m_imageLoader.displayImage(url, (ImageView) findViewById(R.id.imv_event), optionsImage);
    }

    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }
}
