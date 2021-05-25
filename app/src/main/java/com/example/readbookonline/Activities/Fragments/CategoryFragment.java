package com.example.readbookonline.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.readbookonline.Activities.SearchBookActivity;
import com.example.readbookonline.Adapters.ViewPagerCategoryAdapter;
import com.example.readbookonline.R;
import com.google.android.material.tabs.TabLayout;

public class CategoryFragment extends Fragment {

    TabLayout tab_layout;
    ViewPager view_pager;
    String account_id;
    ImageButton search_book;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);
        mapping();
        control();
        return view;
    }

    void mapping(){
        //Nhận các giá trị từ MainActivity
        account_id = getArguments().getString("account_id");

        //Ánh xạ id của tablayout và viewpager và thiết lập adapter cho chúng
        tab_layout = (TabLayout) view.findViewById(R.id.tab_layout);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, account_id);
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);

        // Ánh xạ các biến
        search_book = view.findViewById(R.id.search_book);
    }

    void control(){
        //Thiết lập sự kiện click vào ImageButtom search_book để mở SearchBookAcitivity
        search_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SearchBookActivity.class);
                intent.putExtra("account_id", account_id);
                startActivity(intent);
            }
        });
    }
}
