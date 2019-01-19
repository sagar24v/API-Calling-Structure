package com.sagar24v.Helper;

public class WebResponse<T> {

    /**
     * Callback interface for delivering parsed responses.
     */
    public interface Listener<T> {
        public void onResponse(boolean isSuccess, String response);
    }

}
