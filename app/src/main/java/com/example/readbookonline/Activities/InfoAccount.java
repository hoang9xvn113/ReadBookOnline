package com.example.readbookonline.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.readbookonline.R;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoAccount extends AppCompatActivity {

    String account_id;
    EditText name, password, email;
    TextView user;
    Button btn;
    RequestQueue requestQueue, requestQueue1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_account);
        mapping();
        loadingData();
        control();
    }

    void mapping(){
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        user = findViewById(R.id.user);
        btn = findViewById(R.id.btn);

        Intent intent = getIntent();
        account_id = intent.getStringExtra("account_id");

        requestQueue = Volley.newRequestQueue(this);
        requestQueue1 = Volley.newRequestQueue(this);
    }

    void loadingData(){
        String url = "https://hochoihamhoc.000webhostapp.com/getInfoAccount.php?account_id=" + account_id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    name.setText(response.getString("name"));
                    password.setText(response.getString("password"));
                    email.setText(response.getString("email"));
                    user.setText("Tài khoản: " + response.getString("user"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    void control(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("") || password.getText().toString().equals("") || email.getText().toString().equals("")){
                    Toast.makeText(v.getContext(), "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    String name_account = name.getText().toString();
                    String password_account = password.getText().toString();
                    String email_account = email.getText().toString();
                    String url = "https://hochoihamhoc.000webhostapp.com/updateInfoAccount.php?account_id="+account_id+"&name="+name_account+"&password="+password_account+"&email="+email_account;
                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("1")){
                                loadingData();
                                Toast.makeText(v.getContext(), "Đã cập nhật thông tin tài khoản thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                loadingData();
                                Toast.makeText(v.getContext(), "Lỗi mạng", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    requestQueue1.add(request);

                }
            }
        });
    }
}