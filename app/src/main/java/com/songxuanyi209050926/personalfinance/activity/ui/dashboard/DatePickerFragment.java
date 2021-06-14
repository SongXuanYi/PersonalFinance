package com.songxuanyi209050926.personalfinance.activity.ui.dashboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.songxuanyi209050926.personalfinance.R;
import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.songxuanyi209050926.personalfinance.activity.date";
    private DatePicker mDatePicker;

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        DatePicker datePicker = new DatePicker(getActivity());
        Date date = (Date) getArguments().getSerializable("date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date, null);
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_picker);
        mDatePicker.init(year, month, day, null);

        return new AlertDialog.Builder(getActivity()).setTitle("Date of book keep:")
                .setView(v)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int year = mDatePicker.getYear();
                        int mouth = mDatePicker.getMonth();
                        int day = mDatePicker.getDayOfMonth();
                        Date newDate = new GregorianCalendar(year, mouth, day).getTime();
                        sendResult(Activity.RESULT_OK,newDate);
                    }
                })
                .create();
    }

    public static DatePickerFragment newInstance(Date date) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("date", date);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        return datePickerFragment;
    }

    private void sendResult(int resultCode, Date date) {

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
