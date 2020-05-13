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

public class updateActivity extends AppCompatActivity {
    EditText editName,editNote,editPrice;
    Button buttonThen,buttonHuy;
    int id=0;
    String update="http://192.168.1.4/Android/updateQLCT.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent=getIntent();
        Deal deal= (Deal) intent.getSerializableExtra("detail");
        Toast.makeText(this, deal.getName(), Toast.LENGTH_SHORT).show();
        Anhxa();
        id= (int) deal.getId();
        editName.setText(deal.getName());
        editNote.setText(deal.getNote());
        editPrice.setText(deal.getPrice()+"");
//        Deal deal=  intent.getSerializableExtra("detail");
        Toast.makeText(this, deal.getName(), Toast.LENGTH_SHORT).show();
        buttonThen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editName.getText().toString().trim();
                String note=editNote.getText().toString().trim();
                String price=editPrice.getText().toString().trim();
                if(name.equals("")||note.equals("")||price.equals("")){
                    Toast.makeText(updateActivity.this, "vui lòng nhập vào đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    themdulieu(update);
                }
            }
        });
    }
    private void themdulieu(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(updateActivity.this, "cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(updateActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(updateActivity.this, "loi cap nhat", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(updateActivity.this, "loi update", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("id",String.valueOf(id));
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
