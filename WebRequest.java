package com.hearfoncustomer.Helper;

import android.app.Activity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sagar24v.App;
import com.sagar24v.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WebRequest {


    public void postMethod(final Activity activity, final String url, final HashMap<String,String> param, final WebResponse.Listener<String> webResponse){

        if(!Utils.isInternet()){
            Utils.noInternetConnectionDialog(activity);
            webResponse.onResponse(false,"");
        }else{
            Utils.displayProgressDialog(activity);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.147/admin/apis/"+url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Utils.stopProgressDialog();
                    new Logger().appendLog(activity,"URL : "+ url +" RESPONSE : "+s);

                    try {
                        JSONObject object = new JSONObject(s);
                        boolean status = object.getBoolean("status");
                        if (status) {
                            webResponse.onResponse(true,""+s);
                        } else {
                            JSONArray jsonArray = object.getJSONArray("data");
                            String msg = "";
                            for (int i = 0; i < jsonArray.length(); i++) {
                                msg = msg + jsonArray.get(i);
                            }
                            Utils.displayDialog(activity,"Error msg");
                            webResponse.onResponse(false,"");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        new Logger().appendLog(activity,"URL : "+ url +" JSON ERROR : "+e.toString());
                        Utils.exceptionDialog(activity);
                        webResponse.onResponse(false,"");
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Utils.stopProgressDialog();
                    volleyError.printStackTrace();
                    new Logger().appendLog(activity,"URL : "+ url +" VOLLY ERROR : "+volleyError.toString());
                    Utils.exceptionDialog(activity);
                    webResponse.onResponse(false,"");
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    new Logger().appendLog(activity, "URL : "+ url +" PARAM : " + param.toString());
                    return param;
                }
            };
            stringRequest.setShouldCache(false);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    30000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            App.getInstance().addToRequestQueue(stringRequest, url); ///
        }

    }

}
