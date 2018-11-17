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
