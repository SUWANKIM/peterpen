package com.yj.kakao.afeel.main.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.main.CommunityDetailActivity;
import com.yj.kakao.afeel.main.MainActivity;
import com.yj.kakao.afeel.model.Community;

import java.util.ArrayList;


public class CommunityFragment extends Fragment implements RippleView.OnRippleCompleteListener, AdapterView.OnItemClickListener {

    private MainActivity m_act;
    private ListView lsv_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_community, container, false);

        m_act = (MainActivity) getActivity();
        initView(v);

        return v;
    }

    private void initView(View v) {
        ((RippleView) v.findViewById(R.id.rv_write)).setOnRippleCompleteListener(this);

        lsv_list = (ListView) v.findViewById(R.id.lsv_list);
        lsv_list.setOnItemClickListener(this);
        getList();
    }

    private void getList() {
        ArrayList<Community> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(new Community());
        CommunityAdapter adapter = new CommunityAdapter(m_act, list);
        lsv_list.setAdapter(adapter);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        Intent intent = null;
        switch (rippleView.getId()) {
            case R.id.rv_write:
                m_act.selectFrag(m_act.FRAGMENT_WRITE);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        m_act.startActivityForResult(new Intent(m_act, CommunityDetailActivity.class), m_act.ACTIVITY_COMMUNITY_DETAIL);
    }

    private class CommunityAdapter extends ArrayAdapter<Community> {
        private LayoutInflater mInflater;

        public CommunityAdapter(Context context, ArrayList<Community> values) {
            super(context, R.layout.list_row_community, values);
            mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            CommunityAdapter.Holder holder;

            if (convertView == null) {
                // Inflate the view since it does not exist
                convertView = mInflater.inflate(R.layout.list_row_community, parent, false);

                // Create and save off the holder in the tag so we get quick access to inner fields
                // This must be done for performance reasons
                holder = new CommunityAdapter.Holder();
                convertView.setTag(holder);
            } else {
                holder = (CommunityAdapter.Holder) convertView.getTag();
            }

            return convertView;
        }

        /**
         * View holder for the views we need access to
         */
        private class Holder {
        }
    }
}
