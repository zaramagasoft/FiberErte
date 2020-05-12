package com.alb.fibererte;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.alb.fibererte.fragments.FragmentNormal;
import com.alb.fibererte.ui.main.LogicaNormal;
import com.alb.fibererte.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    String[] datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);




        fab.setOnClickListener(new View.OnClickListener() {
           

            @Override
            public void onClick(View view) {
                FragmentNormal f = new FragmentNormal();
                boolean b = f.ComprobrarEdittext();
                Log.i("MainACtiv ", String.valueOf(b));
                String m;
                if (!b){
                    m="revisa campos vacios, y vuelve a intentar";
                }else {
                    LogicaNormal l = new LogicaNormal();
                    datos = new String[6];
                    datos[0] = FragmentNormal.grupoN;

                    datos[1] = FragmentNormal.turnoN;
                    datos[2] = FragmentNormal.hijosN;
                    datos[3] = FragmentNormal.quinN;
                    datos[4] = FragmentNormal.diasN;
                    datos[5] = FragmentNormal.irpfN;
                    String[] valores = l.PasarValoresCalculo(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                    m="Cáculo Aproximado de Ingresos";
                    Log.i("fabButoon ", datos[5].toString());
                }
                Snackbar.make(view, m, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               // ((MainActivity)getActivity()).refrescarListaResinas();

            }
        });



    }




}