package com.yj.kakao.afeel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;


public class AFeelApp extends Application {

    private static Activity m_act = null;
    public static final String TAG = AFeelApp.class.getSimpleName();

    private static AFeelApp _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;

        initImageLoader(getApplicationContext());
    }

    public static Activity getCurrentActivity() {
        return m_act;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        m_act = currentActivity;
    }

    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public void setMainAct(BaseActivity act) {
        m_act = act;
    }

    public static synchronized AFeelApp getInstance() {
        return _instance;
    }
}
