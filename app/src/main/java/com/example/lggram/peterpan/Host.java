package com.example.lggram.peterpan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.lggram.peterpan.Menu_1.REQUEST_CODE_MENU;

public class Host extends AppCompatActivity {
    TextView title;
    TextView hostname;
    TextView realname;
    TextView tel1;
    TextView tel2;
    TextView email;
    TextView address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_host);
        title = (TextView) findViewById(R.id.title);
        hostname = (TextView) findViewById(R.id.hostname);
        realname= (TextView) findViewById(R.id.realname);
        tel1 = (TextView) findViewById(R.id.tel1);
        tel2 = (TextView) findViewById(R.id.tel2);
        email = (TextView) findViewById(R.id.email);
        address= (TextView) findViewById(R.id.address);
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("name","mike");
        setResult(RESULT_OK,intent);
        finish();
        super.onBackPressed();
    }

    public void clickedButton(){

    }


}

