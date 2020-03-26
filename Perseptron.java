package com.example.lab32;

import java.util.Random;


class Perseptron {

    private double[] w;
    private double cs;

    public int output(double[] input) {
        double sum = 0.0;
        for (int i = 0; i < input.length; i++) {
            sum += w[i] * input[i];
        }
        if (sum > cs) {
            return 1;
        } else {
            return 0;
        }
    }

    public void study(double[][] inputs, int[] outputs,
                      double cs, double lrate, int epoch) {

        this.cs = cs;
        int n = inputs[0].length;
        int p = outputs.length;
        this.w = new double[n];
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            w[i] = r.nextDouble();
        }

        for (int i = 0; i < epoch; i++) {
            int allError = 0;

            for (int j = 0; j < p; j++) {
                int output = output(inputs[j]);
                int error = outputs[j] - output;

                allError += error;

                for (int k = 0; k < n; k++) {
                    double delta = lrate * inputs[j][k] * error;
                    w[k] += delta;
                }
            }
            if (allError == 0) break;
        }
    }

    public String showResult() {
        return "W1 = " + w[0] + " \nW2 = " + w[1];
    }
}