package com.alb.fibererte.ui.main;

import android.util.Log;

public class LogicaNormal {


    //turnos
    public double precioTurno = 0;
    public double turno1=0;
    public double turno2=45.52;
    public double turno3=135.42;
    //Salarios2020
    public double precioGrupo = 0;
    public double grupo1b=19064.71;
    public double grupo2=22271.89;
    public double grupo3=24250.1;
    public double grupo4=28019.75;
    public double grupo5=31881.74;
    //tablasSepe2020
    public double precioSepeMes = 0;
    public double sepehijos0=1098.09;
    public double sepehijos1=1254.96;
    public double sepehijos2=1411.83;
    //totales
    public double totalSepe=0;
    public double totalFiber=0;

    public double quin = 0;
    public double dias = 0;
    public double irpf = 0;

    public LogicaNormal() {

    }

    public double getQuin() {
        return quin;
    }

    public void setQuin(double quin) {
        this.quin = quin;
    }

    public double getDias() {
        return dias;
    }

    public void setDias(double dias) {
        this.dias = dias;
    }

    public double getIrpf() {
        return irpf;
    }

    public void setIrpf(double irpf) {
        this.irpf = irpf;
    }

    public double getTurno1() {
        return turno1;
    }

    public double getTurno2() {
        return turno2;
    }

    public double getTurno3() {
        return turno3;
    }

    public double getGrupo1b() {
        return grupo1b;
    }

    public double getGrupo2() {
        return grupo2;
    }

    public double getGrupo3() {
        return grupo3;
    }

    public double getGrupo4() {
        return grupo4;
    }

    public double getGrupo5() {
        return grupo5;
    }

    public double getSepehijos0() {
        return sepehijos0;
    }

    public double getSepehijos1() {
        return sepehijos1;
    }

    public double getSepehijos2() {
        return sepehijos2;
    }

    public double getTotalSepe() {
        return totalSepe;
    }

    public void setTotalSepe(double totalSepe) {
        this.totalSepe = totalSepe;
    }

    public double getTotalFiber() {
        return totalFiber;
    }

    public void setTotalFiber(double totalFiber) {
        this.totalFiber = totalFiber;
    }

    public String[] PasarValoresCalculo(String grupo, String turnos, String Hijos, String Quinquenios, String diasErte, String irpf) {
        //  Log.i("Grupo pasado 1B: ", String.valueOf(grupo1b) + " " + this.getClass().toString());
        String[] prestaciones = new String[1];
        switch (grupo) {
            case "G1B":
                Log.i("Grupo pasado 1B: ", String.valueOf(grupo1b) + " " + this.getClass().toString());
                precioGrupo = this.getGrupo1b();
                break;
            case "G2":
                precioGrupo = this.getGrupo2();

                break;
            case "G3":
                precioGrupo = this.getGrupo3();

                break;
            case "G4":
                precioGrupo = this.getGrupo4();

                break;
            case "G5":
                precioGrupo = this.getGrupo5();

                break;
        }
        switch (turnos) {
            case "1":
                precioTurno = this.getTurno1();
                break;
            case "2":
                precioTurno = this.getTurno2();
                break;
            case "3":
                precioTurno = this.getTurno3();
                break;


        }
        switch (Hijos) {
            case "0":
                precioSepeMes = this.getSepehijos0();
                break;
            case "1":
                precioSepeMes = this.getSepehijos1();
                break;
            case "2":
                precioSepeMes = this.getSepehijos2();
                break;

        }
        this.setQuin(Double.parseDouble(Quinquenios));
        this.setDias(Double.parseDouble(diasErte));
        this.setIrpf(Double.parseDouble(irpf));


        return prestaciones;
    }

    public String[] CalcularPrestaciones() {
        String[] prestaciones = new String[1];
        //me quedo en calcular prestaciones

        return prestaciones;
    }

}
