package com.atfalnaa.atfalna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity {
    EditText name, email, pass, conpass, phone;
    RadioButton RD_male, RD_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText) findViewById(R.id.txt_name);
        email = (EditText) findViewById(R.id.txt_email);
        pass = (EditText) findViewById(R.id.txt_password);
        conpass = (EditText) findViewById(R.id.txt_re_password);
        phone= (EditText) findViewById(R.id.txt_phone);
        RD_male=(RadioButton) findViewById(R.id.rb_male);
        RD_female=(RadioButton) findViewById(R.id.rb_female);

    }

    public void Registeration(View view) {
        String Ename = name.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Pass = pass.getText().toString().trim();
        String Pass2 = conpass.getText().toString().trim();
        String Phone = phone.getText().toString().trim();
        String g;
        if (RD_male.isChecked()){
            g="ذكر";
        }else
            g="أنثى";

       if (pass.equals(conpass))
        {
            conpass.setError("االرقم السرى غير مطابق");
            conpass.requestFocus();
        }

        else if(Ename.isEmpty())
        {
            name.setError("الاسم فارغ");
            name.requestFocus();
        }
        else if (Email.isEmpty())
        {
            email.setError("البريد الالكترونى فارغ");
            email.requestFocus();
        }

        else if (!validateEmail( email.getText().toString()))
        {
            email.setError("البريد الالكترونى غير صحيح");
            email.requestFocus();
        }
        else if (Pass.isEmpty())
        {
            pass.setError("ارقم السرى فارغ");
            pass.requestFocus();
        }
        else if (Phone.isEmpty())
        {
            phone.setError("الهاتف فارغ");
            phone.requestFocus();
        }
        else if (!validatePhone(phone.getText().toString()))
        {
            phone.setError("رقم الهاتف عير صحيح");
            phone.requestFocus();
        }

        if (!Pass.equals(Pass2)) {
            Toast.makeText(register.this, "اكتب الرقم السري بشكل صحيح", Toast.LENGTH_LONG).show();
        } else {
            Response.Listener<String> responseLisener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            Toast.makeText(register.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(register.this, "يوجد خطأ و لم يتم التسجيل", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            Send_Data_Registration send_Data = new Send_Data_Registration(Ename, Email, Pass,Phone,g,responseLisener);
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(send_Data);
        }
    }

    private boolean validatePhone(String phones)
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
    }
    }
