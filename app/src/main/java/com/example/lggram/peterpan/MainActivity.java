package com.example.lggram.peterpan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ScrollView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience_categories);
        sv = (ScrollView)findViewById(R.id.scrollView); //스크롤 뷰 객체 생성
        //상태바 삭제
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    public void onimageButton1Clicked(View v){
        Toast.makeText(getApplicationContext(),"이미지 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();

    }
    public void onimageButton2Clicked(View v){
        Toast.makeText(getApplicationContext(),"이미지 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        setContentView(R.layout.disabled_categories);
    }

    public void addItem(View v){
        Toast.makeText(getApplicationContext(),"이미지 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        setContentView(R.layout.disabled_categories);
    }





}



package com.example.lggram.peterpan;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.OptAnimationLoader;

import java.util.ArrayList;

public class AreaSelectDialog extends Dialog {
    private Context _context;

    ListView lsv_area, lsv_sub_area;
    AreaAdapter m_area_adapter;
    SubAreaAdapter m_subarea_adapter;

    int area_idx = 0, subarea_idx = 0;

    private View mDialogView;
    private AnimationSet mModalInAnim;

    public AreaSelectDialog(Context context, int idx1, int idx2) {
        super(context, R.style.alert_dialog);
        _context = context;
        area_idx = idx1;
        subarea_idx = idx2;
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_area_select);

        ((RippleView) findViewById(R.id.tv_ok)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {

                dismiss();
            }
        });

        lsv_area = (ListView) findViewById(R.id.lsv_area);
        lsv_sub_area = (ListView) findViewById(R.id.lsv_sub_area);

        m_subarea_adapter = new SubAreaAdapter(_context, new ArrayList<String>());
        lsv_sub_area.setAdapter(m_subarea_adapter);

        String[] arr = _context.getResources().getStringArray(R.array.array_area);
        m_area_adapter = new AreaAdapter(_context, arr);

        m_area_adapter.setPos(area_idx);
        lsv_area.setAdapter(m_area_adapter);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
    }

    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }

    public class AreaAdapter extends ArrayAdapter<String> {

        private Context m_context;
        private int selected_pos;

        public AreaAdapter(Context context, String[] arrayItem) {
            super(context, 0, arrayItem);
            m_context = context;
        }

        public void setPos(int pos) {
            selected_pos = pos;

            m_subarea_adapter.clear();
            String[] arr = _context.getResources().getStringArray(R.array.array_subarea_seoul);
            for (int i = 0; i < arr.length; i++) {
                m_subarea_adapter.add(arr[i]);
            }
            m_subarea_adapter.setPos(subarea_idx);
            m_subarea_adapter.notifyDataSetChanged();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemHolder itemHolder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row_area,
                        parent, false);
                itemHolder = new ItemHolder(convertView);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }

            itemHolder.showInfo(position);

            return convertView;
        }

        public class ItemHolder {

            private TextView tv_area;
            private View line;

            public ItemHolder(View v) {
                tv_area = (TextView) v.findViewById(R.id.tv_area);
                line = v.findViewById(R.id.line);
            }

            public void showInfo(final int position) {
                tv_area.setText(getItem(position));

                if (position == selected_pos)
                    tv_area.setSelected(true);
                else
                    tv_area.setSelected(false);

                if (position == selected_pos - 1 || position == selected_pos)
                    line.setVisibility(View.INVISIBLE);
                else
                    line.setVisibility(View.VISIBLE);

                tv_area.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        subarea_idx = 0;
                        setPos(position);
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }

    public class SubAreaAdapter extends ArrayAdapter<String> {

        private Context m_context;
        private int selected_pos;

        public SubAreaAdapter(Context context, ArrayList<String> arrayItem) {
            super(context, 0, arrayItem);
            m_context = context;
        }

        public void setPos(int i) {
            selected_pos = i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemHolder itemHolder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row_subarea,
                        parent, false);
                itemHolder = new ItemHolder(convertView);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }

            itemHolder.showInfo(position);

            return convertView;
        }

        public class ItemHolder {

            private TextView tv_subarea;
            private View line;
            private ImageView imv_check;

            public ItemHolder(View v) {
                tv_subarea = (TextView) v.findViewById(R.id.tv_subarea);
                line = v.findViewById(R.id.line);
                imv_check = (ImageView) v.findViewById(R.id.imv_select);
            }

            public void showInfo(final int position) {
                tv_subarea.setText(getItem(position));

                if (position == selected_pos) {
                    tv_subarea.setSelected(true);
                    imv_check.setVisibility(View.VISIBLE);
                } else {
                    tv_subarea.setSelected(false);
                    imv_check.setVisibility(View.INVISIBLE);
                }

                if (position == selected_pos - 1 || position == selected_pos)
                    line.setVisibility(View.INVISIBLE);
                else
                    line.setVisibility(View.VISIBLE);

                tv_subarea.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPos(position);
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }
}


package com.example.lggram.peterpan;

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

package com.example.lggram.peterpan;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.common.OptAnimationLoader;
import com.yj.kakao.afeel.common.Util;

import java.util.ArrayList;

public class IdealSelectDialog extends Dialog {
    private Context _context;

    ListView lsv_ideal;
    IdealAdapter m_ideal_adapter;

    int ideal_idx = 0;

    private View mDialogView;
    private AnimationSet mModalInAnim;

    public IdealSelectDialog(Context context, String title, String[] arr, int idx) {
        super(context, R.style.alert_dialog);
        _context = context;
        ideal_idx = idx;
        // TODO Auto-generated constructor stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dlg_ideal_select);

        ((TextView) findViewById(R.id.tv_title)).setText(title);

        lsv_ideal = (ListView) findViewById(R.id.lsv_ideal);

        m_ideal_adapter = new IdealAdapter(_context, arr);
        m_ideal_adapter.setPos(ideal_idx);
        lsv_ideal.setAdapter(m_ideal_adapter);

        Util.setListViewHeightBasedOnChildren(_context, lsv_ideal, 10);

        mDialogView = getWindow().getDecorView().findViewById(android.R.id.content);
        mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
    }

    @Override
    protected void onStart() {
        mDialogView.startAnimation(mModalInAnim);
    }

    public class IdealAdapter extends ArrayAdapter<String> {

        private Context m_context;
        private int selected_pos;

        public IdealAdapter(Context context, String[] arrayItem) {
            super(context, 0, arrayItem);
            m_context = context;
        }

        public void setPos(int pos) {
            selected_pos = pos;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemHolder itemHolder;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_row_ideal,
                        parent, false);
                itemHolder = new ItemHolder(convertView);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }

            itemHolder.showInfo(position);

            return convertView;
        }

        public class ItemHolder {

            private TextView tv_ideal;
            private View line;

            public ItemHolder(View v) {
                tv_ideal = (TextView) v.findViewById(R.id.tv_ideal);
                line = v.findViewById(R.id.line);
            }

            public void showInfo(final int position) {
                tv_ideal.setText(getItem(position));

                if (position == selected_pos)
                    tv_ideal.setSelected(true);
                else
                    tv_ideal.setSelected(false);


                if (position == selected_pos - 1 || position == selected_pos)
                    line.setVisibility(View.INVISIBLE);
                else
                    line.setVisibility(View.VISIBLE);

                tv_ideal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setPos(position);
                        notifyDataSetChanged();
                    }
                });
            }
        }
    }
}

package com.example.lggram.peterpan;

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




