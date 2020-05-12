package com.alb.fibererte;

import android.os.Bundle;

import com.alb.fibererte.fragments.FragmentNormal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.alb.fibererte.ui.main.SectionsPagerAdapter;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {



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
                boolean b=new FragmentNormal().ComprobrarEdittext();
                Log.i("MainACtiv ", String.valueOf(b));
                String m;
                if (!b){
                    m="revisa campos vacios, y vuelve a intentar";
                }else {
                    m="Cáculo Aproximado de Ingresos";
                }
                Snackbar.make(view, m, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
               // ((MainActivity)getActivity()).refrescarListaResinas();

            }
        });



    }




}