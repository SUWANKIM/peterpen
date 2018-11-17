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
