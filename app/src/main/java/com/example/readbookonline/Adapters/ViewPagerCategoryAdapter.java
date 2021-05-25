package com.example.readbookonline.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.readbookonline.Activities.Fragments.GridViewFragment;


public class ViewPagerCategoryAdapter extends FragmentStatePagerAdapter {

    String account_id;
    public ViewPagerCategoryAdapter(@NonNull FragmentManager fm, int behavior, String account_id) {
        super(fm, behavior);
        this.account_id = account_id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment v = new GridViewFragment();
        int pos;
        switch (position){
            case 0:
                pos=1;break;
            case 1:
                pos=2;break;
            case 2:
                pos=3;break;
            case 3:
                pos=4;break;
            case 4:
                pos=5;break;
            default:
                pos = 1;break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("category_id", pos);
        bundle.putString("account_id", account_id);
        v.setArguments(bundle);
        return v;

    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Tâm lý Kĩ năng";break;
            case 1:
                title = "Văn Học";break;
            case 2:
                title = "Triết Học";break;
            case 3:
                title = "Toán Học";break;
            case 4:
                title = "Kinh Tế";break;
        }
        return title;
    }
}
