package com.songxuanyi209050926.personalfinance.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.service.UserService;
import com.songxuanyi209050926.personalfinance.service.impl.UserServiceImpl;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNameText;
    private EditText mPhoneText;
    private EditText mPasswordText;
    private Button mSignUpButton;
    private TextView mToLoginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //![1]
        mNameText = (EditText) findViewById(R.id.username_text);
        mPhoneText = (EditText) findViewById(R.id.phoneText_signup);
        mPasswordText = (EditText) findViewById(R.id.passwordText_signup);
        mSignUpButton = (Button) findViewById(R.id.signupButton);
        mToLoginText = (TextView) findViewById(R.id.to_login_text);

        //![2]
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mNameText.getText().toString();
                String phone = mPhoneText.getText().toString();
                String password = mPasswordText.getText().toString();

                if (!isTextEmpty(username, phone, password)) {
                    UserService service = new UserServiceImpl();
                    int i = service.signUp(getApplicationContext(), username, phone, password);
                    if (i > 0) {
                        Toast.makeText(getApplicationContext(), "注册成功!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "注册失败!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "不能为空!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //![3]
        mToLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean isTextEmpty(String username, String phone, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            return true;
        }
        return false;
    }
}