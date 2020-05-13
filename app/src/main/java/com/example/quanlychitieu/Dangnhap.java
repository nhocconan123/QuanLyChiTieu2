package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Dangnhap extends AppCompatActivity {
    Button btndangnhap;
    EditText edtusername,edtpassword;
    String login="http://192.168.1.4/Android/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        edtusername=(EditText) findViewById(R.id.username);
        edtpassword=(EditText) findViewById(R.id.edtpassword);
        btndangnhap=(Button) findViewById(R.id.btndangnhap);
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dangnhap.this, "an vào đang nhap", Toast.LENGTH_SHORT).show();
                Login();
            }
        });

    }
    private void Login()
    {
        String username =edtusername.getText().toString().trim();
        String password=edtpassword.getText().toString().trim();

        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "nhạp vào user name", Toast.LENGTH_SHORT).show();
            edtusername.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(username))
        {
            Toast.makeText(this, "nhạp vào password", Toast.LENGTH_SHORT).show();
            edtpassword.requestFocus();
            return;
        }
        else{
            login(login);

        }
    }
    private void login(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(Dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Dangnhap.this,MainActivity.class));
                }
                else {
                    Toast.makeText(Dangnhap.this, "loi cap nhat", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Dangnhap.this,MainActivity.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Dangnhap.this, "chưa kết nối đến", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("name",edtusername.getText().toString().trim());
                params.put("password",edtpassword.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
