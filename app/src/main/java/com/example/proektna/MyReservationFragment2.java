package com.example.proektna;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyReservationFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyReservationFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyReservationFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyReservationFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static MyReservationFragment2 newInstance(String param1, String param2) {
        MyReservationFragment2 fragment = new MyReservationFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) getActivity().findViewById(R.id.button);

        Spinner time = getActivity().findViewById(R.id.spinner);
        final String[] t = new String[1];

        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                t[0] = time.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                t[0] = "00:00 - 00:01";
            }
        });

        CalendarView date = getActivity().findViewById(R.id.calendarView2);
        final String[] curDate = {"01", "01", "1970"};
        final String[] fullDate = new String[1];

        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                curDate[0] = String.valueOf(dayOfMonth);
                curDate[1] = String.valueOf(month);
                curDate[2] = String.valueOf(year);
                fullDate[0] = curDate[0] + "/" + curDate[1] + "/" + curDate[2];
            }
        });


        button.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View view1) {
                Toast toast = Toast.makeText(getContext(), "button clicked! THE TIME IS: " + t[0] + fullDate[0], Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getContext(), ParkingPlaces.class);
                intent.putExtra("time", t[0]);
                intent.putExtra("date", fullDate[0]);
                startActivity(intent);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_reservation2, container, false);
    }

}