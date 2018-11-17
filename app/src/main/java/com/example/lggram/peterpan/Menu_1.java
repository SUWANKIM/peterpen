package com.example.lggram.peterpan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu_1 extends AppCompatActivity {
    ListView listView;
    PackageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience_categories);
        listView= (ListView) findViewById(R.id.listView); //리스트 뷰 객체 생성
        adapter = new PackageAdapter();
        adapter.addItems(new PackageItem("같이가용","졸라리재밌는여행",100000, 0x7f060001 ));
        adapter.addItems(new PackageItem("같이가2용","졸라리재밌는여행",100000, R.drawable.b ));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PackageItem item = (PackageItem)adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택"+item.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("name","mike");
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
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
            view.setName(item.getName());
            view.setInfo(item.getInfo());
            view.setPrice(item.getPrice());
            view.setImage(item.getResId());

            return view;
        }
    }

}
