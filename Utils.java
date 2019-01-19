package com.sagar24v.Helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.sagar24v.App;

public class Utils {

    public static boolean isInternet() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e("KeyBoardUtil", e.toString(), e);
        }
    }

    private static ProgressDialog pd = null;
    public static void displayProgressDialog(Context context) {
        try {
            if (pd != null && pd.isShowing()) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pd = new ProgressDialog(context);
            pd.setMessage("Please Wait...!");
            pd.setCancelable(false);
            pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stopProgressDialog() {
        try {
            if (pd != null && pd.isShowing()) {
                pd.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void noInternetConnectionDialog(Context context) {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Oops...");
            dialog.setMessage("No internet connection!");
            dialog.setPositiveButton(android.R.string.ok, null);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void exceptionDialog(Context context) {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Oops...");
            dialog.setMessage("Something went wrong here!");
            dialog.setPositiveButton(android.R.string.ok, null);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayDialog(Context context, String msg) {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setMessage(msg);
            dialog.setPositiveButton(android.R.string.ok, null);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
