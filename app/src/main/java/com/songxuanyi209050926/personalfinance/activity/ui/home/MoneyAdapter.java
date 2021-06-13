package com.songxuanyi209050926.personalfinance.activity.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.songxuanyi209050926.personalfinance.Bean.Money;
import com.songxuanyi209050926.personalfinance.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MoneyAdapter extends RecyclerView.Adapter<MoneyAdapter.ViewHolder> {

    private List<Money> monies =new ArrayList<>();
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_rv, parent, false);
        return  new ViewHolder(view);
    }
    //设置数据
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.getItemTextView().setText(monies.get(position).getDate()+"   "+monies.get(position).getProName()+"\n类型:"+
                                         monies.get(position).getType()+"\n"
                                        +"备注:"+monies.get(position).getRemake());
        if (monies.get(position).getOof()>0){
            holder.getOofText().setTextColor(Color.rgb(255, 106 ,106));
            holder.getOofText().setText("+"+monies.get(position).getOof()+"");
        }else if (monies.get(position).getOof() == 0){
            holder.getOofText().setText("+"+monies.get(position).getOof()+"");
        }else {
            holder.getOofText().setTextColor(Color.rgb(	144, 238 ,144));
            holder.getOofText().setText(monies.get(position).getOof()+"");
        }
    }
    //数据长度
    @Override
    public int getItemCount() {
        return monies.size();
    }
    //这里传入数据
    public MoneyAdapter(List<Money> monies){
         this.monies = monies;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView mItemText;
        private final TextView mOofText;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mItemText = (TextView) itemView.findViewById(R.id.item_text);
            mOofText = (TextView) itemView.findViewById(R.id.oof_text);
        }
        public TextView getItemTextView(){
            return mItemText;
        }
        public TextView getOofText(){
            return mOofText;
        }
    }


}
