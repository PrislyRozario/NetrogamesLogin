package com.prisly.natrogames.user;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prisly.natrogames.config.ServerConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserAuth {

    private Context context;

    public UserAuth(Context context) {
        this.context = context;
    }

    public String LoginUser(String email, String password){
        final String[] authKey = {""};
        final Map<String, String> jsonBody = new HashMap<>();
        jsonBody.put("email", email);
        jsonBody.put("password", password);

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = ServerConfig.SERVER_URL + "loginUser";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject responseJson = new JSONObject(response);
                    if (responseJson.getString("status") == "success"){
                        authKey[0] = responseJson.getString("authKey");
                    }else {
                        Log.d("LoginFailed", responseJson.getString("message"));
                        Toast.makeText(context, "Failed to Login", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return jsonBody;
            }
        };

        queue.add(stringRequest);

        return authKey[0];
    }
}
