package com.air.myapplication.appfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.air.myapplication.R;
import com.air.myapplication.appmodels.booking.BookingParent;
import com.air.myapplication.utils.AppConstants;

/**
 * Created by ujjwalbansal on 17/02/18.
 */

public class BookingDetailFragment extends Fragment {
    TextView tvBookingMsg;
    TextView tvPnrVal;
    TextView tvBookingStatus;
    TextView tvPsng1;
    TextView tvPsng2;
    TextView tvCityDetails;
    TextView tvDep;
    TextView tvArrival;

    BookingParent bookingParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_booking_details, container, false);
        tvBookingMsg = rootView.findViewById(R.id.tv_confirmation_msg);
        tvBookingStatus = rootView.findViewById(R.id.tv_booking_status);
        tvPnrVal = rootView.findViewById(R.id.tv_pnr_val);
        tvPsng1 = rootView.findViewById(R.id.tv_psng1);
        tvPsng2 = rootView.findViewById(R.id.tv_psng2);
        tvCityDetails = rootView.findViewById(R.id.tv_city_details);
        tvDep = rootView.findViewById(R.id.tv_dep);
        tvArrival = rootView.findViewById(R.id.tv_arrival);
        if (getArguments() != null && getArguments().containsKey(AppConstants.BookingData)) {
            bookingParent = (BookingParent) getArguments().getSerializable(AppConstants.BookingData);
            setData();
        }
        return rootView;
    }

    private void setData() {
        if (bookingParent != null) {
            String depCity = bookingParent.getJourneyServices().getJourneyService().get(0).getSegments().getSegment().get(0).getDepartureStation();
            String arvCity = bookingParent.getJourneyServices().getJourneyService().get(0).getSegments().getSegment().get(0).getArrivalStation();

            tvBookingMsg.setText("Hello " + bookingParent.getBookingContacts().getBookingContact().get(0).getName().getFirstName() + " " + bookingParent.getBookingContacts().getBookingContact().get(0).getName().getLastName() + "" + ", your " + depCity + " to " + arvCity + " flight has been booked");
            tvBookingStatus.setText(bookingParent.getBookingStatus());
            tvPnrVal.setText(bookingParent.getPnr());

            for (int i = 0; i < bookingParent.getBookingPassengers().getBookingPassenger().size(); i++) {
                if (i == 0) {
                    tvPsng1.setText("Passenger 1 : " + bookingParent.getBookingPassengers().getBookingPassenger().get(i).getPaxPriceType().getPaxType());
                } else if (i == 1) {
                    tvPsng2.setText("Passenger 2 : " + bookingParent.getBookingPassengers().getBookingPassenger().get(i).getPaxPriceType().getPaxType());
                }
            }

            tvDep.setText(bookingParent.getJourneyServices().getJourneyService().get(0).getSegments().getSegment().get(0).getDepartureTime());
            tvArrival.setText(bookingParent.getJourneyServices().getJourneyService().get(0).getSegments().getSegment().get(0).getArrivalTime());
        }
    }
}
