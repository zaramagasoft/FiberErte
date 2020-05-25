package com.alb.fibererte.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alb.fibererte.MinMaxDoubleFilter;
import com.alb.fibererte.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentReducc#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentReducc extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static EditText editReduccion;
    public static double redu;
    static TextView textSepeR, textFiberR, texTotalR;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentReducc() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentReducc.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentReducc newInstance(String param1, String param2) {
        FragmentReducc fragment = new FragmentReducc();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reducc, container, false);
        editReduccion = (EditText) rootView.findViewById(R.id.editReduccion);
        textFiberR = (TextView) rootView.findViewById(R.id.textFiberRedu);
        textSepeR = (TextView) rootView.findViewById(R.id.textSepeRedu);
        texTotalR = (TextView) rootView.findViewById(R.id.textTotalRedu);
        gestionEditText();
        return rootView;
    }

    private void gestionEditText() {
        // editReduccion.setFilters( new InputFilter[]{ new MinMaxFilter( "0" , "100" )}) ;
        editReduccion.setFilters(new InputFilter[]{new MinMaxDoubleFilter("0", "100"), new InputFilter.LengthFilter(5) {
        }});

        editReduccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("quinqueniosbefore", editReduccion.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("quinquenioschanged", editReduccion.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("quinqueniosafter", editReduccion.getText().toString());
                if (!editReduccion.getText().toString().isEmpty()) {
                    redu = Double.parseDouble(editReduccion.getText().toString());
                    redu = redu / 100;
                }

                Log.i("redu", String.valueOf(redu));

            }


        });


    }

    public void mostrarCalculos(String[] resRedu) {
        textFiberR.setText("");
        textSepeR.setText("");
        texTotalR.setText("");
        textSepeR.setText("Cantidad SEPE :  " + resRedu[0]);
        textFiberR.setText("Cantidad Fiber :  " + resRedu[1]);
        texTotalR.setText("Total: " + resRedu[2]);
    }
}
