package android.gdmec.edu.cn.boxuegu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.gdmec.edu.cn.boxuegu.R;
import android.gdmec.edu.cn.boxuegu.utils.MD5Utils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by student on 17/12/27.
 */

public class LoginActivity extends AppCompatActivity {
    private TextView tv_main_title;
    private TextView tv_back;
    private TextView tv_register;
    private TextView tv_find_psw;
    private Button btn_login;
    private EditText et_user_name;
    private EditText et_psw;
    private String userName;
    private String psw;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("登陆");
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name =(EditText) findViewById(R.id.et_user_name);
        et_psw = (EditText) findViewById(R.id.et_psw);
        tv_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LoginActivity.this.finish();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
        tv_find_psw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                String md5Psw = MD5Utils.md5(psw);
                String spPsw = readPsw(userName);
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }else if(md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    saveLoginStatus(true,userName);
                    Intent data = new Intent();
                    data.putExtra("isLogin",true);
                    setResult(RESULT_OK,data);
                    LoginActivity.this.finish();
                    return;
                }else if(!TextUtils.isEmpty(spPsw) && !md5Psw.equals(spPsw)){
                    Toast.makeText(LoginActivity.this,"输入的用户名和密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(LoginActivity.this,"此用户不存在",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveLoginStatus(boolean status,String userName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",status);
        editor.putString("loginUserName",userName);
        editor.commit();
    }

    private String readPsw(String userName){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        return sp.getString(userName,"");
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (data!=null){
            String userName = data.getStringExtra("userName");
            if (data!=null){
                et_user_name.setText(userName);
                et_user_name.setSelection(userName.length());
            }
        }
    }

}
