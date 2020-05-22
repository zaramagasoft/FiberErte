package com.alb.fibererte;

import android.text.InputFilter;
import android.text.Spanned;

public class MinMaxDoubleFilter implements InputFilter {
    private double mIntMin, mIntMax;


    public MinMaxDoubleFilter(int minValue, int maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
    }

    public MinMaxDoubleFilter(String minValue, String maxValue) {
        this.mIntMin = Double.parseDouble(minValue);
        this.mIntMax = Double.parseDouble(maxValue);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            double input = Double.parseDouble(dest.toString() + source.toString());
            if (isInRange(mIntMin, mIntMax, input))
                return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }


}

