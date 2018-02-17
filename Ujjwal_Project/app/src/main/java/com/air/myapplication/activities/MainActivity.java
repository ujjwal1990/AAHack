package com.air.myapplication.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.air.myapplication.R;
import com.air.myapplication.appfragments.BookingDetailFragment;
import com.air.myapplication.appmodels.booking.BookingParent;
import com.air.myapplication.network.ApiRequestId;
import com.air.myapplication.network.ApiResponseHandler;
import com.air.myapplication.network.AppNetworkService;
import com.air.myapplication.utils.AppConstants;
import com.air.myapplication.utils.GeneralUtil;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements ApiResponseHandler {

    AppNetworkService networkService;
    BookingParent bookingParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        networkService = AppNetworkService.getInstance(this);
        if (GeneralUtil.isNetworkConnectionAvailable(this)) {
            networkService.getBookingHistory(new ArrayMap<String, String>(), this);
        } else {
            Toast.makeText(this, getString(R.string.no_network), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSuccess(String requestUrl, ApiRequestId apiRequestId, String response) {
        if (response != null) {
            if (apiRequestId == ApiRequestId.GET_BOOKING_DETAILS) {
                if (response != null && response.length() > 0) {
                    Gson gson = new Gson();
                    bookingParent = gson.fromJson(response, BookingParent.class);
                }
            }
        }
    }

    @Override
    public void onFailure(int requestCode, String requestUrl, ApiRequestId apiRequestId, String response) {
        Log.i("", "" + response);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_booking) {
            if (bookingParent != null) {
                BookingDetailFragment bookingDetailFragment = new BookingDetailFragment();
                Bundle b = new Bundle();
                b.putSerializable(AppConstants.BookingData, bookingParent);
                bookingDetailFragment.setArguments(b);
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content, bookingDetailFragment, "bookingDetailFragment")
                        .addToBackStack(null)
                        .commit();
            } else {
                Toast.makeText(this, "Please wait getting details", Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
