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

    public double pagoSepe;
    public double pagoFiber;

    public double bsRedu;
    public double totalPagoSepeReduccion;
    public double totalPagoFiberReduccion, prorrateoRedu;

    public LogicaNormal() {

    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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
        this.dias = Math.ceil(dias * 1.25);//redondeo al multiplicar por  1.25
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


        //  CalcularPrestaciones();
        return prestaciones;
    }

    public String[] CalcularPrestaciones() {
        String[] prestaciones = new String[3];
        //me quedo en calcular prestaciones

        double diasFiber=30;
        double salarioDia=precioGrupo/420;

        Log.i("salarioDia: ", String.valueOf(round (salarioDia,2)));

        Double salarioBruto=salarioDia*(diasFiber-this.getDias());
        Log.i("salarioBruto: ", String.valueOf(round (salarioBruto,2)));

        //salario base
        double salarioBase = salarioBruto * 0.66;
        Log.i("salarioBase: ", String.valueOf(round(salarioBase, 2)));

        //incentivos
        double incentivos = salarioBruto * 0.2;
        Log.i("incentivos: ", String.valueOf(round(incentivos, 2)));

        //quinquenios

        double quinquenios = ((salarioDia * 0.02) * getQuin()) * (diasFiber - getDias());
        // Log.i("incentivosData: ", String.valueOf(round (getDias(),2)));
        Log.i("quinquenios: ", String.valueOf(round(quinquenios, 2)));

        //prima Produccion
        double prima = salarioBruto * 0.14;
        Log.i("primaP: ", String.valueOf(round(prima, 2)));

        //plusturnos

        double plusTurnos = (precioTurno / 30) * (diasFiber - getDias());
        Log.i("plusTurnos: ", String.valueOf(round(plusTurnos, 2)));

        //total devengado
        double totalDevengado = salarioBase + incentivos + quinquenios + prima + plusTurnos;
        Log.i("totalDevengado: ", String.valueOf(round(totalDevengado, 2)));

        //prorrateo
        double prorrateo = (salarioBase + incentivos + quinquenios + prima) / 6;
        Log.i("prorrateo: ", String.valueOf(round(prorrateo, 2)));

        //baseCC
        double baseCC = totalDevengado + prorrateo;
        Log.i("baseCC: ", String.valueOf(round(baseCC, 2)));

        //baseIrpf
        double baseIrpf = totalDevengado;
        Log.i("baseIrpf: ", String.valueOf(round(baseIrpf, 2)));

        //deduciones, aqui me quedo

        double CC = baseCC * 0.047;
        double des = baseCC * 0.0155;
        double form = baseCC * 0.001;

        double deducciones = CC + des + form + (baseIrpf * (irpf / 100));
        Log.i("deducciones: ", String.valueOf(round(deducciones, 2)));

        //compensacionERTE

        // double pagoSepe=
        double sb = salarioDia * 30;
        double baseRSepe = baseReguladoraSepe(sb);
        double sbSepe = baseRSepe * 0.7;
        bsRedu = baseCC;
        Log.i("sbSepeX: ", String.valueOf(round(sbSepe, 2)));
        ajusteTablasSepe(sbSepe);

        //me quedo en la compensacion ERTE

        double compERTE = compensacionErte(salarioDia);

        pagoFiber = (salarioBase + incentivos + quinquenios + prima + plusTurnos + compERTE) - deducciones;
        Log.i("pagoFiber: ", String.valueOf(round(pagoFiber, 2)));
        prestaciones[0] = String.valueOf(round(pagoSepe, 2));
        prestaciones[1] = String.valueOf(round(pagoFiber, 2));
        prestaciones[2] = String.valueOf(round((pagoSepe + pagoFiber), 2));

        return prestaciones;
    }

    public double baseReguladoraSepe(double sb) {
        double salarioBrutoSepe = sb;
        double salarioBase = salarioBrutoSepe * 0.66;
        double quinSepe = ((sb * 0.02) * getQuin());
        double plusSepe = (precioTurno / 30) * 30;
        double incentivos = salarioBrutoSepe * 0.2;
        double prima = salarioBrutoSepe * 0.14;
        double prorrateo = (salarioBase + incentivos + quinSepe + prima) / 6;
        double baseRSpepe = salarioBase + quinSepe + plusSepe + incentivos + prima + prorrateo;
        Log.i("baseRsepe: ", String.valueOf(round(baseRSpepe, 2)));
        return baseRSpepe;


    }

    public void ajusteTablasSepe(double sbSepe) {
        // precioSepeMes=0;
        if (precioSepeMes > sbSepe) {
            precioSepeMes = sbSepe;
            Log.i("precioSepeMes: ", String.valueOf(round(precioSepeMes, 2)));
        } else {
            Log.i("precioSepeMes: ", String.valueOf(round(precioSepeMes, 2)));
        }
        //precioSeoemes;

    }

    public double compensacionErte(double salarioDia) {
        Log.i("salarioDia", String.valueOf(round(salarioDia, 2)));
        double diarioSepe = precioSepeMes / 30;
        double reteSepe = diarioSepe * ((irpf - 1) / 100);
        Log.i("CreteSepe: ", String.valueOf(round(reteSepe, 2)));
        double sepeDia = diarioSepe - reteSepe;
        Log.i("sepeDia: ", String.valueOf(round(sepeDia, 2)));

        double compensacionDiaria = (salarioDia * 0.8) - (diarioSepe);
        if (compensacionDiaria < 0) {
            compensacionDiaria = 0;
        } else {
            double reteCompesacion = compensacionDiaria * ((irpf + 6.35) / 100);
            Log.i("reteCompensacion ", String.valueOf(round(reteCompesacion, 2)));
            compensacionDiaria = compensacionDiaria - reteCompesacion;
            Log.i("compensacionDiaria: ", String.valueOf(round(compensacionDiaria, 2)));
        }

        double compErte = compensacionDiaria * getDias();
        Log.i("compenERTE: ", String.valueOf(round(compErte, 2)));
        pagoSepe = sepeDia * getDias();
        Log.i("pagoSepe: ", String.valueOf(round(pagoSepe, 2)));
        //me quedo con la compensacionErtefiber ya calculada solo mostrar totales
        return compErte;
    }

    public String[] calculoReduccion(double redu) {


        String[] prestaciones = this.CalcularPrestacionesReducc();
        Log.i("calcR redu: ", String.valueOf(round(redu, 2)));
        Log.i("calcR bsRedu: ", String.valueOf(round(bsRedu, 2)));
        double bsCorr = (bsRedu * redu);
        double bs2 = bsCorr + (bsCorr * (1 - redu));
        Log.i("calcR realBs: ", String.valueOf(round(bs2, 2)));
        ajusteTablasSepe(bs2 * 0.7);
        Log.i("calcR precioSepeMes: ", String.valueOf(round(precioSepeMes, 2)));
        String[] rJ = new String[3];
        double sepeDia = precioSepeMes / 30;
        Log.i("calcR sepeDia: ", String.valueOf(round(sepeDia, 2)));
        double pagoSepeReducc = sepeDia * getDias();
        double retenSepeReducc = pagoSepeReducc * ((irpf + 4.7) / 100);
        double PGpagoSepeReducc = pagoSepeReducc - retenSepeReducc;
        //me quedo a falta de calcular prestacionFiber
        // double totalSepeReduccion=PGpagoSepeReducc*getDias();
        Log.i("totalSepeReducc ", String.valueOf(round(PGpagoSepeReducc, 2)));
        totalPagoSepeReduccion = PGpagoSepeReducc;
        calculoPagoFiberRedu(bsCorr, redu);
        rJ[0] = String.valueOf(round(totalPagoSepeReduccion, 2));
        rJ[1] = String.valueOf(round(totalPagoFiberReduccion, 2));
        rJ[2] = String.valueOf(round(totalPagoFiberReduccion + totalPagoSepeReduccion, 2));
        return rJ;
    }

    private void calculoPagoFiberRedu(double bscorr, double redu) {
        Log.i("calcFiberRedu: ", String.valueOf(round(bscorr, 2)));

        //aqui hay que hacer decuentos y clacular los dias

        double pagoFiberBccRedu = (bscorr * (30 - getDias())) / 30;
        Log.i("calcFiberReduBcc: ", String.valueOf(round(pagoFiberBccRedu, 2)));
        //deducciones
        double CC = pagoFiberBccRedu * 0.047;
        double des = pagoFiberBccRedu * 0.0155;
        double form = pagoFiberBccRedu * 0.001;
        double baseIrpf = (pagoFiberBccRedu) - ((prorrateoRedu * redu) * (30 - getDias()) / 30);
        double irpfR = baseIrpf * (irpf / 100);
        double deducciones = CC + des + form + (baseIrpf * (irpf / 100));
        totalPagoFiberReduccion = baseIrpf - deducciones;


    }

    private String[] CalcularPrestacionesReducc() {
        String[] prestaciones = new String[3];
        //me quedo en calcular prestaciones

        double diasFiber = 30;
        double salarioDia = precioGrupo / 420;

        Log.i("salarioDia: ", String.valueOf(round(salarioDia, 2)));

        Double salarioBruto = salarioDia * (diasFiber);
        Log.i("salarioBruto: ", String.valueOf(round(salarioBruto, 2)));

        //salario base
        double salarioBase = salarioBruto * 0.66;
        Log.i("salarioBase: ", String.valueOf(round(salarioBase, 2)));

        //incentivos
        double incentivos = salarioBruto * 0.2;
        Log.i("incentivos: ", String.valueOf(round(incentivos, 2)));

        //quinquenios

        double quinquenios = ((salarioDia * 0.02) * getQuin()) * (diasFiber);
        // Log.i("incentivosData: ", String.valueOf(round (getDias(),2)));
        Log.i("quinquenios: ", String.valueOf(round(quinquenios, 2)));

        //prima Produccion
        double prima = salarioBruto * 0.14;
        Log.i("primaP: ", String.valueOf(round(prima, 2)));

        //plusturnos

        double plusTurnos = (precioTurno / 30) * (diasFiber);
        Log.i("plusTurnos: ", String.valueOf(round(plusTurnos, 2)));

        //total devengado
        double totalDevengado = salarioBase + incentivos + quinquenios + prima + plusTurnos;
        Log.i("totalDevengado: ", String.valueOf(round(totalDevengado, 2)));

        //prorrateo
        double prorrateo = (salarioBase + incentivos + quinquenios + prima) / 6;
        Log.i("prorrateo: ", String.valueOf(round(prorrateo, 2)));

        prorrateoRedu = prorrateo;
        //baseCC
        double baseCC = totalDevengado + prorrateo;
        Log.i("baseCC: ", String.valueOf(round(baseCC, 2)));

        //baseIrpf
        double baseIrpf = totalDevengado;
        Log.i("baseIrpf: ", String.valueOf(round(baseIrpf, 2)));

        //deduciones, aqui me quedo

        double CC = baseCC * 0.047;
        double des = baseCC * 0.0155;
        double form = baseCC * 0.001;

        double deducciones = CC + des + form + (baseIrpf * (irpf / 100));
        Log.i("deducciones: ", String.valueOf(round(deducciones, 2)));

        //compensacionERTE

        // double pagoSepe=
        double sb = salarioDia * 30;
        double baseRSepe = baseReguladoraSepe(sb);
        double sbSepe = baseRSepe * 0.7;
        bsRedu = baseCC;
        Log.i("sbSepeX: ", String.valueOf(round(sbSepe, 2)));
        ajusteTablasSepe(sbSepe);

        //me quedo en la compensacion ERTE

        double compERTE = compensacionErte(salarioDia);

        pagoFiber = (salarioBase + incentivos + quinquenios + prima + plusTurnos + compERTE) - deducciones;
        Log.i("pagoFiber: ", String.valueOf(round(pagoFiber, 2)));
        prestaciones[0] = String.valueOf(round(pagoSepe, 2));
        prestaciones[1] = String.valueOf(round(pagoFiber, 2));
        prestaciones[2] = String.valueOf(round((pagoSepe + pagoFiber), 2));

        return prestaciones;
    }

}
