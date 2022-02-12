package com.example.pasarmalam.User;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pasarmalam.Database.FoodMenu;
import com.example.pasarmalam.R;

import java.util.List;

public class ListAdapter3 extends ArrayAdapter {


    private Activity mContext;
    List<FoodMenu> foodMenuList;


    public ListAdapter3(Activity mContext, List<FoodMenu> foodMenuList) {
        super(mContext, R.layout.list_item2, foodMenuList);
        this.mContext = mContext;
        this.foodMenuList = foodMenuList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item2, null, true);

        TextView tvfdName = listItemView.findViewById(R.id.tvfdName);
        TextView tvfdPrice = listItemView.findViewById(R.id.tvfdPrice);
        TextView tvDescription = listItemView.findViewById(R.id.tvDescription);

        FoodMenu foodMenu = foodMenuList.get(position);

        tvfdName.setText(foodMenu.getFoodName());
        tvfdPrice.setText(foodMenu.getFoodPrice());
        tvDescription.setText(foodMenu.getFoodDescription());

        return listItemView;
    }
}