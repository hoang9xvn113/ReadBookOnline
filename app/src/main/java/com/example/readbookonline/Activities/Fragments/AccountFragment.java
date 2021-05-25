package com.example.readbookonline.Activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.Activities.InfoAppActivity;
import com.example.readbookonline.Activities.SigninActivity;
import com.example.readbookonline.R;

public class AccountFragment extends Fragment {

    LinearLayout in4,logout,rate,setting;
    String account_id;
    RequestQueue requestQueue;
    TextView name_account;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_account, container, false);
        mapping();
        loadingData();
        control();
        return v;
    }

    void loadingData(){
        String url = "https://hochoihamhoc.000webhostapp.com/getNameAccount.php?account_id="+account_id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                name_account.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    void mapping(){
        //Nhận các giá trị từ MainActivity
        account_id = getArguments().getString("account_id");

        //Ánh xạ với các id theo layout
        in4 = v.findViewById(R.id.in4);
        logout = v.findViewById(R.id.logout);
        rate = v.findViewById(R.id.rate);
        setting = v.findViewById(R.id.setting);
        name_account = v.findViewById(R.id.name_account);

        //Request Json từ server
        requestQueue = Volley.newRequestQueue(getContext());
    }

    void control(){
        //Hiện thị cài đặt
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Tính năng này sẽ sớm được cập nhật", Toast.LENGTH_SHORT).show();
            }
        });

        //Thông tin ứng dụng
        in4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InfoAppActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        //Đăng xuất
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SigninActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        //Đánh giá cho chúng tôi
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Ứng dụng chưa đẩy lên CHPlay nên không thể đánh giá", Toast.LENGTH_SHORT).show();
            }
        });
    }





}
