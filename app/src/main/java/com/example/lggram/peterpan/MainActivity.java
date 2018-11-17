package com.example.lggram.peterpan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ScrollView sv;
    public static final int REQUEST_CODE_MENU =101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sv = (ScrollView)findViewById(R.id.scrollView); //스크롤 뷰 객체 생성

    }


    public void onimageButton1Clicked(View v){
        Toast.makeText(getApplicationContext(),"이미지 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(),Menu_1.class);
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
    public void onimageButton2Clicked(View v){
        Toast.makeText(getApplicationContext(),"이미지 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        setContentView(R.layout.disabled_categories);
    }

    public void addItem(View v){
        Toast.makeText(getApplicationContext(),"이미지 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        setContentView(R.layout.disabled_categories);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(),"onActivityResult 메소드 호출됨"+requestCode+"결과코드"+resultCode,Toast.LENGTH_LONG).show();


        }

        if(requestCode ==RESULT_OK){
            String name = data.getExtras().getString("name");
            Toast.makeText(getApplicationContext(),"응답 전달된 name"+name,Toast.LENGTH_LONG).show();

        }
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

