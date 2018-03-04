package com.atfalnaa.atfalna;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends  AppCompatActivity {

    EditText Login_name, Login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Login_name=findViewById(R.id.txt_email);
        Login_password=findViewById(R.id.txt_password);

        Button btn_Login = findViewById(R.id.btn_login);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login(view);
            }
        });
    }

    public void Login(View view)
        {
        String Log_in_email=Login_name.getText().toString().trim();
        String Log_in_password=Login_password.getText().toString().trim();

        Response.Listener<String>responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success){
                        Toast.makeText(login.this, "تم تسجيل الدخول", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),createpostfound.class));
                    }else {
                        Toast.makeText(login.this, "البيانات غير صحيحة", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Send_Data_Login send_data =new Send_Data_Login(Log_in_email,Log_in_password,responseListener);
        RequestQueue queue = Volley.newRequestQueue(login.this);
        queue.add(send_data);

    }

    /*private boolean validatePhone(String phones)
    {
        String emailpattery = "^([0]{1})([1]{1})([0125]{1})([0-9]{8})$";
        Pattern pattern = Pattern.compile(emailpattery);
        Matcher matcher = pattern.matcher(phones);
        return  matcher.matches();
    }

    private boolean validateEmail(String email)
    {
        String emailpattery = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailpattery);
        Matcher matcher = pattern.matcher(email);
        return  matcher.matches();
    }*/

    //text her   هنا
    public void Registeration(View view) {
        startActivity(new Intent(getApplicationContext(),register.class));
    }
}
