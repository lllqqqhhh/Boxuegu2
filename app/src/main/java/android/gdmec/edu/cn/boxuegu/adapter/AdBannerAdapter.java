package android.gdmec.edu.cn.boxuegu.adapter;


import android.gdmec.edu.cn.boxuegu.bean.CourseBean;


import android.gdmec.edu.cn.boxuegu.fragment.AdBeannerFragment;
import android.gdmec.edu.cn.boxuegu.view.CourseView;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by student on 17/12/27.
 */

public class AdBannerAdapter extends FragmentStatePagerAdapter implements View.OnTouchListener {
    private Handler mHandler;
    private List<CourseBean> cabl;
    public AdBannerAdapter(FragmentManager fm){
        super(fm);
        cabl = new ArrayList<CourseBean>();
    }
    public AdBannerAdapter(FragmentManager fm,Handler handler){
        super(fm);
        mHandler = handler;
        cabl = new ArrayList<CourseBean>();
    }
    public void setDatas(List<CourseBean> cabl){
        this.cabl = cabl;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int index) {
        Bundle args = new Bundle();
        if (cabl.size()>0)
            args.putString("ad",cabl.get(index%cabl.size()).icon);
        return AdBeannerFragment.newInstance(args);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    public int getSize(){
        return cabl == null?0:cabl.size();
    }
    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mHandler.removeMessages(CourseView.MSG_AD_SLID);
        return false;
    }
}
