package com.atfalnaa.atfalna;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohamed on 3/3/2018.
 */

public class Send_Data_Registration extends StringRequest {

    private static final String SEND_DATA_URL ="http://192.168.1.102/cases/Registration.php";
    private Map<String, String> MapData;

    public Send_Data_Registration(String name, String email, String password,String phone,String gender, Response.Listener<String> listener) {
        super(Method.POST, SEND_DATA_URL, listener, null);
        MapData = new HashMap<>();
        MapData.put("name",name);
        MapData.put("email", email);
        MapData.put("password", password);
        MapData.put("phone", phone);
        MapData.put("gender", gender);


    }

    @Override
    public Map<String, String> getParams() {
        return MapData;
    }
}