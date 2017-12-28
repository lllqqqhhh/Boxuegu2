package android.gdmec.edu.cn.boxuegu.activity;

import android.content.pm.ActivityInfo;
import android.gdmec.edu.cn.boxuegu.R;
import android.gdmec.edu.cn.boxuegu.utils.AnalysisUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by student on 17/12/27.
 */

public class ModifyPswActivity extends AppCompatActivity {
    private TextView tv_main_title;
    private TextView tv_back;
    private EditText et_original_psw, et_new_psw, et_new_psw_again;
    private Button btn_save;
    private String originalPsw, newPsw, newPswAgain;
    private String userName;
    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_modify_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        userName = AnalysisUtils.readLoginUserName(this);
    }

    private void init(){
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("修改密码");
        tv_back = (TextView) findViewById(R.id.tv_back);
        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_new_psw = (EditText) findViewById()
    }
}
