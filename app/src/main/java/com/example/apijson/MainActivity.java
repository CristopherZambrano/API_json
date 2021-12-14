package com.example.apijson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.apijson.Asynchtask;
import com.example.apijson.WebService;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws = new WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET","Public-Merchant-Id","84e1d0de1fbf437e9779fd6a52a9ca18");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtban = (TextView) findViewById(R.id.txtBancos);

        String txtbancos="";
        JSONArray Jsonlist = new JSONArray(result);
        for(int i =0;i<Jsonlist.length();i++){
            JSONObject banco = Jsonlist.getJSONObject(i);
            txtbancos = txtbancos + "\n" + banco.getString("name").toString();
        }
        txtban.setText("Respuesta es" + txtbancos);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}