package com.songxuanyi209050926.personalfinance.activity.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.service.UserService;
import com.songxuanyi209050926.personalfinance.service.impl.UserServiceImpl;
import com.songxuanyi209050926.personalfinance.util.GetTime;


public class DashboardFragment extends Fragment {

    private TextView mTimeText;
    private Button mIncomeButton;
    private Button mExpensesButton;
    private EditText mProNameText;
    private EditText mMoneyText;
    private EditText mTypeText;
    private EditText mRemakeText;

    private String proName, type, remake, date;
    private int oof;
    private Money money;
    private UserService service = new UserServiceImpl();
    private String username ="宋";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        date = GetTime.getTime();
        //获取当地日期
        mTimeText = root.findViewById(R.id.local_time);
        mTimeText.setText(date);

        mProNameText = root.findViewById(R.id.pro_name_text);
        mMoneyText = root.findViewById(R.id.money_text);
        mTypeText = root.findViewById(R.id.type_text);
        mRemakeText = root.findViewById(R.id.remake_text);


        mIncomeButton = root.findViewById(R.id.income_button);
        mIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proName = mProNameText.getText().toString();
                oof = Integer.parseInt(mMoneyText.getText().toString());
                type = mTypeText.getText().toString();
                remake=mRemakeText.getText().toString();
                money = new Money(username,oof,proName,type,remake,date);
                int i = service.addMoneyInDB(getContext(),money);
                if (i>0) {
                    Log.d("DB","插入成功");
                }
            }
        });
        return root;
    }

}