package com.songxuanyi209050926.personalfinance.activity.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.activity.MainActivity;
import com.songxuanyi209050926.personalfinance.service.UserService;
import com.songxuanyi209050926.personalfinance.service.impl.UserServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "Money";
    private RecyclerView mMoneyRecyclerView;
    private MoneyAdapter mAdapter;
    private UserService service = new UserServiceImpl();
    private String username;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mMoneyRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerview_home);
        mMoneyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return root;
    }

    private class MoneyHolder extends  RecyclerView.ViewHolder{

        public MoneyHolder(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_home,parent,false));
        }
    }

    private class MoneyAdapter extends RecyclerView.Adapter<MoneyHolder>{

        private List<Money> mMoney;

        public MoneyAdapter(List<Money> money){
            mMoney = money;
        }

        @NonNull
        @NotNull
        @Override
        public MoneyHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MoneyHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull MoneyHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mMoney.size();
        }
    }

    private void updateUI(){
        List<Money> monies = service.findAllMoney(getContext(),username);
        mAdapter = new MoneyAdapter(monies);
        mMoneyRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "updateUI: "+monies.size());
    }

    @Override
    public void onAttach(@NonNull @NotNull Activity activity) {
        super.onAttach(activity);                               //这里同理，找不到username;
        username=((MainActivity)activity).findUsername();
    }
}