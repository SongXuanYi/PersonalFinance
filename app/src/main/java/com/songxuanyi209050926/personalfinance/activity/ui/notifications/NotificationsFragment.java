package com.songxuanyi209050926.personalfinance.activity.ui.notifications;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.activity.MainActivity;
import com.songxuanyi209050926.personalfinance.activity.ui.home.MoneyAdapter;
import com.songxuanyi209050926.personalfinance.service.UserService;
import com.songxuanyi209050926.personalfinance.service.impl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class NotificationsFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    public static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected NotificationsFragment.LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mMoneyRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected MoneyAdapter moneyAdapter;
    protected List<Money> moneyList = new ArrayList<>();

    private Spinner mYearSpinner;
    private Spinner mMouthSpinner;
    private Spinner mDaySpinner;
    private String date = "2020";
    private String username;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //year
        mYearSpinner = (Spinner) root.findViewById(R.id.year_spinner);
        mYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] years = getResources().getStringArray(R.array.year_array);
                date = years[position] + "年";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //mouth
        mMouthSpinner = (Spinner) root.findViewById(R.id.mouth_spinner);
        mMouthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] mouths = getResources().getStringArray(R.array.mouth_array);
                if (!mouths[position].equals("无")) {
                    date = date + mouths[position] + "月";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //day
        mDaySpinner = (Spinner) root.findViewById(R.id.day_spinner);
        mDaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] days = getResources().getStringArray(R.array.day_array);
                if (!days[position].equals("无")) {
                    date = date + days[position] + "日";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button button = root.findViewById(R.id.find_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //findList
                UserService service = new UserServiceImpl();
                moneyList = service.findByDate(getContext(), username, date);
                Collections.reverse(moneyList);
                mMoneyRecyclerView = (RecyclerView) root.findViewById(R.id.rv_find_by_date);
                mCurrentLayoutManagerType = NotificationsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                if (savedInstanceState != null) {
                    // Restore saved layout manager type.
                    mCurrentLayoutManagerType = (NotificationsFragment.LayoutManagerType) savedInstanceState
                            .getSerializable(KEY_LAYOUT_MANAGER);
                }
                setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
                mLayoutManager = new LinearLayoutManager(getActivity());
                moneyAdapter = new MoneyAdapter(moneyList);
                mMoneyRecyclerView.setAdapter(moneyAdapter);
                setRecyclerViewLayoutManager(NotificationsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER);
                mMoneyRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            }
        });


        return root;
    }

    @Override
    public void onAttach(@NonNull @NotNull Activity activity) {
        super.onAttach(activity);
        username = ((MainActivity) activity).findUsername();
    }

    public void setRecyclerViewLayoutManager(NotificationsFragment.LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mMoneyRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mMoneyRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = NotificationsFragment.LayoutManagerType.LINEAR_LAYOUT_MANAGER;


        mMoneyRecyclerView.setLayoutManager(mLayoutManager);
        mMoneyRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}