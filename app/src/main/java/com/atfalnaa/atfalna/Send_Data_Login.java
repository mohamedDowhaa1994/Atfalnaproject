package com.atfalnaa.atfalna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohamed on 3/3/2018.
 */

public class Send_Data_Login  extends StringRequest {

    private static final String SEND_DATA_URL = "http://192.168.1.102/cases/login.php";
    private Map<String,String> MapData;

    public Send_Data_Login(String Login_name, String Login_password, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();
        MapData.put("Login_name", Login_name);
        MapData.put("Login_password", Login_password);
    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}