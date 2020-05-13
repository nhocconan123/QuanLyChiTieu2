package com.example.quanlychitieu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class Add extends AppCompatActivity {
        EditText editName,editNote,editPrice;
        Button buttonThen,buttonHuy;
    String urlInsert="http://192.168.1.4/Android/insertQLCT.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Anhxa();
        buttonThen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString().trim();
                String note=editNote.getText().toString().trim();
                String price=editPrice.getText().toString().trim();
                if(name.equals("")||note.equals("")||price.equals("")){
                    Toast.makeText(Add.this, "vui lòng nhập vào đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    themdulieu(urlInsert);
                }
            }
        });
        buttonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void themdulieu(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(Add.this, "them thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Add.this,MainActivity.class));
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Add.this, "chua két noi den csdl", Toast.LENGTH_SHORT).show();
                    }
                }){
                protected Map<String,String> getParams()throws AuthFailureError{
                    Map<String,String> params=new HashMap<>();
                    params.put("name",editName.getText().toString().trim());
                    params.put("note",editNote.getText().toString().trim());
                    params.put("price",editPrice.getText().toString().trim());
                    return params;
                }
        };
        requestQueue.add(stringRequest);
    }
    private void Anhxa(){
        editName=(EditText) findViewById(R.id.editName);
        editNote=(EditText) findViewById(R.id.editNote);
        editPrice=(EditText) findViewById(R.id.editPrice);
        buttonThen=(Button) findViewById(R.id.buttonThen);
        buttonHuy=(Button) findViewById(R.id.buttonHuy);
    }
}
