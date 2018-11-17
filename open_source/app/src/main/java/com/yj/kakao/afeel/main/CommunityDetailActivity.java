package com.yj.kakao.afeel.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.BaseActivity;
import com.yj.kakao.afeel.MyConfirmDialog;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.main.Dialog.EditDialog;
import com.yj.kakao.afeel.model.Community;

import java.util.ArrayList;

public class CommunityDetailActivity extends BaseActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_detail);

        ((RippleView) findViewById(R.id.imv_back)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                finish();
            }
        });

        ((RippleView) findViewById(R.id.rv_edit)).setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                new EditDialog(CommunityDetailActivity.this).show();
            }
        });

        lv = (ListView) findViewById(R.id.lv);
        getList();
    }

    private void getList() {
        ArrayList<Community> list = new ArrayList<Community>();
        for (int i = 0; i < 10; i++)
            list.add(new Community());
        CommunityReplyAdapter adapter = new CommunityReplyAdapter(this, list);
        lv.setAdapter(adapter);
    }

    private class CommunityReplyAdapter extends ArrayAdapter<Community> {
        private LayoutInflater mInflater;

        public CommunityReplyAdapter(Context context, ArrayList<Community> values) {
            super(context, 0, values);
            mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            CommunityReplyAdapter.Holder holder;

            if (convertView == null) {
                // Inflate the view since it does not exist

                if (position % 2 == 0)
                    convertView = mInflater.inflate(R.layout.list_row_community_reply, null);
                else
                    convertView = mInflater.inflate(R.layout.list_row_community_inner_reply, null);

                // Create and save off the holder in the tag so we get quick access to inner fields
                // This must be done for performance reasons
                holder = new CommunityReplyAdapter.Holder();
                convertView.setTag(holder);
            } else {
                holder = (CommunityReplyAdapter.Holder) convertView.getTag();
            }

            return convertView;
        }

        /**
         * View holder for the views we need access to
         */
        private class Holder {
        }
    }

    public void editCommunity() {
        Intent intent = new Intent();
        intent.putExtra("edit", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void deleteCommunity() {
        MyConfirmDialog dlg = new MyConfirmDialog(this);
        dlg.setImageVisible(false);
        dlg.setContentVisible(false);
        dlg.setTitleVisible(true);
        dlg.setTitleText("정말 삭제하시겠습니까?", 0, 3, 5);
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("delete", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        dlg.show();
    }
}
