package com.yj.kakao.afeel.main.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.yj.kakao.afeel.MyAlertDialog;
import com.yj.kakao.afeel.MyConfirmDialog;
import com.yj.kakao.afeel.R;
import com.yj.kakao.afeel.SelectDialog;
import com.yj.kakao.afeel.common.Util;
import com.yj.kakao.afeel.main.Dialog.AFeelDialog;
import com.yj.kakao.afeel.main.MainActivity;

import java.util.ArrayList;


public class StoreFragment extends Fragment implements RippleView.OnRippleCompleteListener, AdapterView.OnItemClickListener {

    private MainActivity m_act;
    private GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_store, container, false);

        m_act = (MainActivity) getActivity();
        initView(v);

        return v;
    }

    private void initView(View v) {
        ((RippleView) v.findViewById(R.id.rv_coupon_input)).setOnRippleCompleteListener(this);

        ((RippleView) v.findViewById(R.id.tab_ll0)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.tab_ll1)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.tab_ll2)).setOnRippleCompleteListener(this);

        ((RippleView) v.findViewById(R.id.rv_buy_heart1)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_buy_heart2)).setOnRippleCompleteListener(this);

        ((RippleView) v.findViewById(R.id.rv_use_heart1)).setOnRippleCompleteListener(this);
        ((RippleView) v.findViewById(R.id.rv_use_heart2)).setOnRippleCompleteListener(this);

        gridView = (GridView) v.findViewById(R.id.grv_list);
        gridView.setOnItemClickListener(this);
        getList();
    }

    @Override
    public void onComplete(RippleView rippleView) {
        Intent intent = null;
        switch (rippleView.getId()) {
            case R.id.rv_coupon_input:
                m_act.selectFrag(m_act.FRAGMENT_COUPON_MANAGE);
                break;
            case R.id.tab_ll0:
                setTab(0);
                break;
            case R.id.tab_ll1:
                setTab(1);
                break;
            case R.id.tab_ll2:
                setTab(2);
                break;
            case R.id.rv_buy_heart1:
                buyHeart();
                break;
            case R.id.rv_buy_heart2:
                break;
            case R.id.rv_use_heart1:
                useHeart();
                break;
            case R.id.rv_use_heart2:
                break;
        }
    }

    private void useHeart() {
        AFeelDialog dlg = new AFeelDialog(m_act, AFeelDialog.BUY_COUPON_WITH_HEART);
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notEnoughHeart();
            }
        });
        dlg.show();
    }

    private void notEnoughHeart() {
        MyAlertDialog dlg = new MyAlertDialog(m_act);
        dlg.setImageVisible(true);
        dlg.setContentVisible(false);
        dlg.setTitleVisible(true);
        dlg.setImageRes(R.drawable.icon_critical);
        dlg.setTitleText("보유하신 하트가 부족합니다.\n하트를 충전해주세요.", 0, 5, 7);
        dlg.setHeartVisible(true);
        dlg.setClickListener(null);
        dlg.show();
    }

    private void buyHeart() {
        new SelectDialog(m_act, "스토어", "카드 결제", "포인트 결제", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyHeartWithCard();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyHeartWithPoint();
            }
        }).show();
    }

    private void buyHeartWithCard() {
        AFeelDialog dlg = new AFeelDialog(m_act, AFeelDialog.BUY_HEART_WITH_CARD);
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        dlg.show();
    }

    private void buyHeartWithPoint() {
        AFeelDialog dlg = new AFeelDialog(m_act, AFeelDialog.BUY_HEART_WITH_POINT);
        dlg.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notEnoughPoint();
            }
        });
        dlg.show();
    }

    private void notEnoughPoint() {
        MyAlertDialog dlg = new MyAlertDialog(m_act);
        dlg.setImageVisible(true);
        dlg.setContentVisible(false);
        dlg.setTitleVisible(true);
        dlg.setImageRes(R.drawable.icon_critical);
        dlg.setTitleText("보유하신 포인트가 부족합니다.", 0, 5, 8);
        dlg.setPointVisible(true);
        dlg.setClickListener(null);
        dlg.show();
    }

    private void setTab(int index) {
        for (int i = 0; i < 3; i++) {
            int tvId = getResources().getIdentifier("tab_tv" + i, "id", m_act.getPackageName());
            int vId = getResources().getIdentifier("tab_v" + i, "id", m_act.getPackageName());

            TextView tabTv = (TextView) getView().findViewById(tvId);
            View v = getView().findViewById(vId);

            if (i == index) {
                tabTv.setTextColor(Color.parseColor("#FF6976"));
                v.setBackgroundColor(Color.parseColor("#FF6976"));
            } else {
                tabTv.setTextColor(Color.parseColor("#000000"));
                v.setBackgroundColor(Color.parseColor("#CCCCCC"));
            }
        }
    }

    private void getList() {
        ArrayList<Card> list = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            list.add(new Card());

        CardAdapter adapter = new CardAdapter(m_act, list);
        gridView.setAdapter(adapter);

        Util.setGridViewHeightBasedOnChildren(m_act, gridView, 3, 150);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        m_act.selectFrag(m_act.FRAGMENT_COUPON_BUY);
    }

    private class Card {

    }

    private class CardAdapter extends ArrayAdapter<Card> {
        private LayoutInflater mInflater;

        public CardAdapter(Context context, ArrayList<Card> values) {
            super(context, R.layout.list_row_card, values);
            mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Holder holder;

            if (convertView == null) {
                // Inflate the view since it does not exist
                convertView = mInflater.inflate(R.layout.list_row_card, parent, false);

                // Create and save off the holder in the tag so we get quick access to inner fields
                // This must be done for performance reasons
                holder = new Holder();
                holder.rippleView = (RippleView) convertView.findViewById(R.id.rippleView);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            holder.rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                @Override
                public void onComplete(RippleView rippleView) {
                    gridView.performItemClick(gridView.getAdapter().getView(position, null, null), position, gridView.getAdapter().getItemId(position));

                }
            });
            return convertView;
        }

        /**
         * View holder for the views we need access to
         */
        private class Holder {
            RippleView rippleView;
        }
    }
}
