package android.gdmec.edu.cn.boxuegu.fragment;

import android.app.Fragment;
import android.gdmec.edu.cn.boxuegu.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by student on 17/12/27.
 */

public class AdBeannerFragment extends Fragment {
    private String ab;
    private ImageView iv;
    public static AdBeannerFragment newInstance(Bundle args){
        AdBeannerFragment af = new AdBeannerFragment();
        af.setArguments(args);
        return af;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arg = getArguments();
        ab = arg.getString("ad");
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onResume(){
        super.onResume();
        if (ab != null){
            if ("banner_1".equals(ab)){
                iv.setImageResource(R.drawable.banner_1);
            }else if ("banner_2".equals(ab)){
                iv.setImageResource(R.drawable.banner_2);
            }else if ("banner_3".equals(ab)){
                iv.setImageResource(R.drawable.banner_3);
            }
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if (iv != null){
            iv.setImageDrawable(null);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        iv = new ImageView(getActivity());
        ViewGroup.LayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        return iv;
    }

}
