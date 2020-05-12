package com.alb.fibererte.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alb.fibererte.MainActivity;
import com.alb.fibererte.MinMaxFilter;
import com.alb.fibererte.R;


import java.util.Arrays;

import static android.widget.ArrayAdapter.createFromResource;
import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentNormal#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNormal extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] categorias = {"G1B", "G2", "G3", "G4", "G5"};
    String[] turnos = {"1","2","3"};
    String[] hijos = {"0","1","2"};
    Spinner spcat;
    Spinner spturn;
    Spinner sphijos;
    EditText editQuin;
    EditText editDias;
    EditText editIrpf;

    public FragmentNormal() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNormal.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNormal newInstance(String param1, String param2) {
        FragmentNormal fragment = new FragmentNormal();
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

       // ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_normal, container, false);
        editQuin=(EditText) rootView.findViewById(R.id.editQuinquenios);
        editDias=(EditText) rootView.findViewById(R.id.editDias);
        editIrpf=(EditText) rootView.findViewById(R.id.editirpf);
        //Log.d("Valor ET", editQuin.getText().toString());

        GestionEditText();



                //spinner categorias
        spcat = (Spinner) rootView.findViewById(R.id.spinnerGrupo);
        spcat.setOnItemSelectedListener(this);
        //spinner turnos
        spturn = (Spinner) rootView.findViewById(R.id.spinnerTurnos);
        spturn.setOnItemSelectedListener(this);
        //spinner hijos
        sphijos = (Spinner) rootView.findViewById(R.id.spinnerHijos);
        sphijos.setOnItemSelectedListener(this);

        //loadSpinnerData();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item,
                categorias);
        @SuppressLint("ResourceType") ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_item,
                turnos);
       //  @SuppressLint("ResourceType") ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(
       //         this.getActivity(), R.drawable.spinner_item,
        //        hijos);
        ArrayAdapter<CharSequence> dataAdapter3 = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.hijos, R.layout.spinner_item);

        // Drop down layout style - list view with radio

        dataAdapter
                .setDropDownViewResource(R.layout.spinner_item);
        dataAdapter.getItem(3);
        dataAdapter2
                .setDropDownViewResource(R.layout.spinner_item);
        dataAdapter.getItem(2);
        dataAdapter3
                .setDropDownViewResource(R.layout.spinner_item);
        dataAdapter.getItem(2);
        // attaching data adapter to spinner
        spcat.setAdapter(dataAdapter);
        spcat.setSelection(3);
        spturn.setAdapter(dataAdapter2);
        spturn.setSelection(1);
        sphijos.setAdapter(dataAdapter3);
        sphijos.setSelection(2);
        return rootView;

    }

    private void GestionEditText() {

        editQuin.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "6" )}) ;
        editDias.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "30" )}) ;
        editIrpf.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "25" )}) ;

             TextWatcher generalTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editQuin.getText().hashCode()==charSequence.hashCode()){
                    Log.i("Valor quinqueniosBefore", editQuin.getText().toString());
                }else if(editDias.getText().hashCode()==charSequence.hashCode()){
                    Log.i("Valor diasBefore", editDias.getText().toString());
                }else if(editIrpf.getText().hashCode()==charSequence.hashCode()){
                    Log.i("Valor diasBefore", editIrpf.getText().toString());
                }


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(editQuin.getText().hashCode()==charSequence.hashCode()){
                    Log.i("quinquenioschanged", editQuin.getText().toString());
                    Log.i("quinquenioschanged", editQuin.getText().hashCode() + " ff " + charSequence.hashCode() );
                }
                else if(editDias.getText().hashCode()==charSequence.hashCode()){
                    Log.i("dias Changer", editDias.getText().toString());
                    Log.i("diaschanged", editQuin.getText().hashCode() + " ff " + charSequence.hashCode() );
                }
                else if(editIrpf.getText().hashCode()==charSequence.hashCode()){
                    Log.i("irpfChanger", editIrpf.getText().toString());
                    Log.i("irpfchanged", editQuin.getText().hashCode() + " ff " + charSequence.hashCode() );
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editQuin.getText().hashCode()==editable.hashCode()){
                    Log.i("EditWuinafterchanged", editQuin.getText().toString());
                }
                else if(editDias.getText().hashCode()==editable.hashCode()){
                    Log.i("EditDiasafterchanged", editDias.getText().toString());
                }
                else if(editIrpf.getText().hashCode()==editable.hashCode()){
                    Log.i("EditIrpfafterchanged", editIrpf.getText().toString());
                }

            }
        };
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        editQuin.addTextChangedListener(generalTextWatcher);
        editDias.addTextChangedListener(generalTextWatcher);
        editIrpf.addTextChangedListener(generalTextWatcher);
        editQuin.setInputType(InputType.TYPE_CLASS_PHONE);
        editDias.setInputType(InputType.TYPE_CLASS_PHONE);
        editIrpf.setInputType(InputType.TYPE_CLASS_PHONE);
        mgr.showSoftInput(editQuin, InputMethodManager.SHOW_FORCED);
        mgr.showSoftInput(editDias, InputMethodManager.SHOW_FORCED);
        mgr.showSoftInput(editIrpf, InputMethodManager.SHOW_FORCED);
     /*   editQuin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("Valor quinqueniosBefore", editQuin.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.i("quinqueniosOnchenage", editQuin.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("quinqueniosafterChange", editQuin.getText().toString());
            }
        });
        editQuin.setInputType(InputType.TYPE_CLASS_PHONE);
        editQuin.requestFocus();
        InputMethodManager mgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        mgr.showSoftInput(editQuin, InputMethodManager.SHOW_FORCED);*/

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getId() == R.id.spinnerGrupo)
        {
            //do this
            Log.i(TAG, "onItemSelected: SpinnerGrupo " + adapterView.getSelectedItem().toString() + " int"+  i + " lon" +  l );
        }
        else if(adapterView.getId() == R.id.spinnerTurnos)
        {
            //do this
            Log.i(TAG, "onItemSelected: SpinnerTurnos " + adapterView.getSelectedItem().toString()  + " int"+  i + " lon" +  l );
        }
        else if(adapterView.getId() == R.id.spinnerHijos)
        {
            //do this
            Log.i(TAG, "onItemSelected: SpinnerHijos " + adapterView.getSelectedItem().toString()  + " int"+  i + " lon" +  l );
        }
       // Log.i(TAG, "onItemSelected: " + adapterView.getAdapter().toString() + " int"+  i + " lon" +  l );
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
