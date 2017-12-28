package android.gdmec.edu.cn.boxuegu.view;

import android.app.Activity;
import android.gdmec.edu.cn.boxuegu.R;
import android.gdmec.edu.cn.boxuegu.adapter.AdBannerAdapter;
import android.gdmec.edu.cn.boxuegu.adapter.CourseAdapter;
import android.gdmec.edu.cn.boxuegu.bean.CourseBean;
import android.gdmec.edu.cn.boxuegu.utils.AnalysisUtils;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 17/12/27.
 */

public class CourseView {
    private ListView lv_list;
    private CursorAdapter adapter;
    private List<List<CourseBean>> cbl;
    private FragmentActivity mContext;
    private LayoutInflater mInflater;
    private View mCurrentView;
    private ViewPager adPager;
    private View adBannerLay;
    private AdBannerAdapter ada;
    private static final int MSG_AD_SLID = 002;
    private ViewPagerIndicator vpi;
    private MHandler mHandler;
    private List<CourseBean> cadl;
    public CourseView(FragmentActivity context){
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }
    public void createView(){
        mHandler = new MHandler();
        initAdData();
        getCourseData();
        initView();
        new AdAutoSlidThread.start();
    }
    class MHandler extends Handler{
        @Override
        public void dispatchMessage(Message msg){
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_AD_SLID:
                    if (ada.getCount()>0){
                        adPager.setCurrentItem(adPager.getCurrentItem()+1);
                    }
                    break;
            }
        }
    }
    class AdAutoSlidThread extends  Thread{
        @Override
        public void run(){
            super.run();
            while (true){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mHandler != null)
                    mHandler.sendEmptyMessage(MSG_AD_SLID);
            }
        }
    }
    private void initView(){
        mCurrentView = mInflater.inflate(R.layout.main_view_course,null);
        lv_list = (ListView)mCurrentView.findViewById(R.id.lv_list);
        adapter = new CourseAdapter(mContext);
        adapter.setData(cbl);
        lv_list.setAdapter(adapter);
        adPager = (ViewPager) mCurrentView.findViewById(R.id.vp_advertBanner);
        adPager.setLongClickable(false);
        ada = new AdBannerAdapter(mContext.getSupportFragmentManager(),mHandler);
        adPager.setAdapter(ada);
        vpi = (ViewPagerIndicator) mCurrentView.findViewById(R.id.vpi_advert_indicator);
        vpi.setCount(ada.geiSize());
        adBannerLay = mCurrentView.findViewById(R.id.rl_adBanner);
        adPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position,float positionOffset,int positionOffsetPixels){
            }
            @Override
            public void onPageSelected(int position){
                if (ada.getSize()>0){
                    vpi.setCurrentPosition(position % ada.getSize());
                }
            }
            @Override
            public void onPageScrollStateChanged(int state){
            }
        });
        resetSize();
        if (cadl != null){
            if (cadl.size()>0){
                vpi.setCount(cadl.size());
                vpi.setCurrentPosition(0);
            }
            ada.setDatas(cadl);
        }
    }
    private void resetSize(){
        int sw = getScreenWidth(mContext);
        int adLheight = sw/2;
        ViewGroup.LayoutParams adlp = adBannerLay.getLayoutParams();
        adlp.width = sw;
        adlp.height = adLheight;
        adBannerLay.setLayoutParams(adlp);
    }
    public static int getScreenWidth(Activity context){
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }
    private void initAdData(){
        cadl = new ArrayList<CourseBean>();
        for (int i=0;i<3;i++){
            CourseBean bean = new CourseBean();
            bean.id = (i+1);
            switch (i){
                case 0:
                    bean.icon="banner_1";
                    break;
                case 1:
                    bean.icon="banner_2";
                    break;
                case 2:
                    bean.icon="banner_3";
                    break;
                default:
                    break;
            }
            cadl.add(bean);
        }
    }
    private void getCourseData(){
        try {
            InputStream is = mContext.getResources().getAssets().open("chaptertitle.xml");
            cbl = AnalysisUtils.getCouresInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public View getView(){
        if (mCurrentView == null){
            createView();
        }
        return mCurrentView;
    }
    public void showView(){
        if (mCurrentView == null){
            createView();
        }
        mCurrentView.setVisibility(View.VISIBLE);
    }
}
