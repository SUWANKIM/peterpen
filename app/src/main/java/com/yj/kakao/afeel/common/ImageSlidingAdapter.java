package com.yj.kakao.afeel.common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yj.kakao.afeel.login.TutorialActivity;
import com.yj.kakao.afeel.main.PhotoViewActivity;

import java.util.ArrayList;

public class ImageSlidingAdapter extends PagerAdapter {

    private ArrayList<Integer> arrReviewImgInfo;
    private Context _context;
    private int m_type;

    public ImageSlidingAdapter(Context con, ArrayList<Integer> imgInfo, int type) {
        this._context = con;
        this.arrReviewImgInfo = imgInfo;
        this.m_type = type;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        if (m_type == 1)
            ((TutorialActivity) _context).setPhotoPage(position);
        else if (m_type == 2)
            ((PhotoViewActivity) _context).setPhotoPage(position);
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arrReviewImgInfo.size();
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == ((ImageView) arg1);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(_context);
        if (m_type == 1)
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        else if (m_type == 2)
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(arrReviewImgInfo.get(position));
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}
