package com.example.lggram.peterpan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Item_list extends AppCompatActivity {
    ListView listView;
    PackageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        listView= (ListView) findViewById(R.id.listView); //리스트 뷰 객체 생성
        adapter = new PackageAdapter();
        adapter.addItems(new PackageItem("둘레길 걷기","김수완","화악산", R.drawable.doole));
        adapter.addItems(new PackageItem("제주도 올레길","노재영","제주도공항", R.drawable.b ));
        adapter.addItems(new PackageItem("도자기 공예 원데이","박상현","인사동", R.drawable.b ));
        adapter.addItems(new PackageItem("도시와 농촌의 즐거움","류요선","일산역", R.drawable.b ));


        listView.setAdapter(adapter);

    }

    class PackageAdapter extends BaseAdapter {
        ArrayList<PackageItem> items = new ArrayList<PackageItem>();

        @Override
        public int getCount(){
            return items.size();
        }

        public void addItems(PackageItem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup){
            PackageItemView view = new PackageItemView(getApplicationContext());
            PackageItem item = items.get(position);
            //Host host = new Host(getApplicationContext());
            view.setName(item.getName());
            view.setInfo(item.getInfo());
            view.setPrice(item.getPrice());
            view.setImage(item.getResId());

            return view;
        }
    }

}
