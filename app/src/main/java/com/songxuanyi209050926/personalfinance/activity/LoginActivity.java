/*
1.	基于Android平台实现简单的个人理财管理的APP
2.	要求提供登录界面给用户
3.	要求有界面让用户输入每天的收入和支出（包括但不限于以下字段：项目名称，金额，录入时间，类别(信用卡/现金)
4.	可以对某笔记录添加文字或者照片备注。
5.	支持按照每日，每月，每年进行统计、查询。
6.	以上内容要求记入SQLITE或者文件系统中，APP重启后不丢失。
 */
package com.songxuanyi209050926.personalfinance.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.songxuanyi209050926.personalfinance.Bean.User;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.service.UserService;
import com.songxuanyi209050926.personalfinance.service.impl.UserServiceImpl;

public class LoginActivity extends AppCompatActivity {

    private EditText mPhoneText;
    private EditText mPasswordText;
    private TextView mRegisterText;
    private Button mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //![1]
        mPhoneText = (EditText) findViewById(R.id.phoneText_login);
        mPasswordText = (EditText) findViewById(R.id.passwordText_login);
        mRegisterText = (TextView) findViewById(R.id.to_login_text);
        mLoginButton = (Button) findViewById(R.id.signupButton);

        //![2]
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserService service = new UserServiceImpl();
                //若账号密码正确则user对象被封装；
                User user = service.isUserByPhoneAndPassword(getApplicationContext(), mPhoneText.getText().toString(), mPasswordText.getText().toString());
                //实现登录界面到记账界面的跳转
                if (user != null) {
                    Toast.makeText(getApplicationContext(), "欢迎您，" + user.getUsername(), Toast.LENGTH_SHORT).show();
                    Log.d("isUser", "登录成功");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    Log.d("isUser", "登录失败");
                }
            }
        });

        //![3]
        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}