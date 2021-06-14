package com.songxuanyi209050926.personalfinance.activity.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.activity.MainActivity;
import com.songxuanyi209050926.personalfinance.service.UserService;
import com.songxuanyi209050926.personalfinance.service.impl.UserServiceImpl;
import com.songxuanyi209050926.personalfinance.util.GetTime;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DashboardFragment extends Fragment {

    private TextView mTimeText;
    private Button mIncomeButton;
    private Button mExpensesButton;
    private EditText mProNameText;
    private EditText mMoneyText;
    private EditText mTypeText;
    private EditText mRemakeText;

    private String proName, type, remake, nowDate;
    private double oof;
    private Money money;
    private UserService service = new UserServiceImpl();
    private String username;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //获取当地日期
        nowDate = GetTime.getTime();
        mTimeText = root.findViewById(R.id.local_time);
        mTimeText.setText(nowDate);




        mProNameText = root.findViewById(R.id.pro_name_text);
        mMoneyText = root.findViewById(R.id.money_text);
        mTypeText = root.findViewById(R.id.type_text);
        mRemakeText = root.findViewById(R.id.remake_text);

        //收入
        mIncomeButton = root.findViewById(R.id.income_button);
        mIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                proName = mProNameText.getText().toString();
                oof = Integer.parseInt(mMoneyText.getText().toString());
                type = mTypeText.getText().toString();
                remake = mRemakeText.getText().toString();
                money = new Money(username, oof, proName, type, remake, nowDate);
                int i = service.addMoneyInDB(getContext(), money);
                if (i > 0) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    Log.d("DB", "收入插入成功");
                }
            }
        });

        //支出
        mExpensesButton = root.findViewById(R.id.exepenses_button);
        mExpensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proName = mProNameText.getText().toString();
                oof = -Integer.parseInt(mMoneyText.getText().toString());
                type = mTypeText.getText().toString();
                remake = mRemakeText.getText().toString();
                money = new Money(username, oof, proName, type, remake, nowDate);
                int i = service.addMoneyInDB(getContext(), money);
                if (i > 0) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);

                    Log.d("DB", "支出插入成功");
                }
            }
        });

        //Date
        mTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(date);
                dialog.setTargetFragment(DashboardFragment.this,0);
                dialog.show(manager,"DialogDate");
            }
        });
        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Activity activity) {
        super.onAttach(activity);
        username = ((MainActivity) activity).findUsername();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (resultCode!=Activity.RESULT_OK){
            return;
        }
        if (requestCode== 0){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
            Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            nowDate = formatter.format(date);
            mTimeText.setText(nowDate);
        }
    }
}