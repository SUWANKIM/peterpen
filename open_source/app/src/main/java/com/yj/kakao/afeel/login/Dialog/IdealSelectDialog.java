package com.yj.kakao.afeel.login.Dialog;

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
