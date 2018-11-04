package com.example.hunglv.demo_webserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edit_id,edit_name, edit_date, edit_address;
    Button btn_send;
    String url_getData = "http://192.168.1.107/getDatabase.php/";
    String url_postData = "http://192.168.1.107/insert.php/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetDataJSon(url_getData);
        anhXa();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostDataJSon(url_postData);
            }
        });
    }
    private void anhXa() {
        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_date = (EditText) findViewById(R.id.edit_date);
        edit_address = (EditText) findViewById(R.id.edit_address);
        btn_send = (Button) findViewById(R.id.btn_send);
    }
    private void PostDataJSon(String url_postData) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_postData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Sucessfull", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String > params = new HashMap<>();
                params.put("id_post", edit_id.getText().toString().trim());
                params.put("name_post", edit_name.getText().toString().trim());
                params.put("date_post", edit_date.getText().toString().trim());
                params.put("address_post", edit_address.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void GetDataJSon(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0; i<response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Student st = new Student(jsonObject.getString("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("date"),
                                jsonObject.getString("address"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
