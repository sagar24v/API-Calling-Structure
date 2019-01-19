# API-Calling-Structure
Call HTTP Api from one place and handle multiple things
1. Check Internet, if not Display Error message
2. Display Log in Log File in device storage (Url, Param, Response)
3. Error Handling, if Error Display Message 
4. Exception Handing, if Exception occurs Display Exception Message
5. Display Progress dialog while api calling
6. Getting Response Call Back in Activity or fragment

We need to add following class
1. WebRequest for HTTP Request
2. WebResponse for handle response call back
3. Utils for Display Error message, check internet, display progress dialog
4. Logger for store log in device storage
5. App for handle HTTP request queue

Update your manifest

Add Permission

    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />

Add line in application tag

    android:name=".App"

Call This Method from your Activity or Fragment

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("username", "sagar.v@gmail.com");
                    params.put("password", "123456");
                    params.put("device_platform", "android");

                    new WebRequest().postMethod(LogInActivity.this, "user/login", params, new WebResponse.Listener<String>() {
                        @Override
                        public void onResponse(boolean isSuccess, String response) {
                            if(isSuccess){

                            }
                        }
                    });

For HTTP Request we need to add volley in gradle

    compile 'com.android.volley:volley:1.1.1'


