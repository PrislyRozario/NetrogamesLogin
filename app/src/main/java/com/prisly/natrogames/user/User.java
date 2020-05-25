package com.prisly.natrogames.user;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prisly.natrogames.config.ServerConfig;
import org.json.JSONObject;

import java.util.Objects;

public class User {

    public User(Context context, String authKey) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = ServerConfig.SERVER_URL + "getUserDetails/" + authKey;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    }
}
