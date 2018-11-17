package com.example.lggram.peterpan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PackageItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;
    
    public PackageItemView(Context context) {
        super(context);
        init(context);
    }
    
    public PackageItemView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater =(LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.add_item, this, true);

        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView2);
        textView3=(TextView)findViewById(R.id.textView3);
        imageView=(ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView.setText(name);
    }
    public void setInfo(String info){
        textView2.setText(info);
    }
    public void setPrice(int price){
        textView3.setText(String.valueOf(price));
    }
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }

}
