package NN;

import java.util.ArrayList;

public class Algo {
	int n, m, l;
	Matrix<Double> Weight_h;
	Matrix<Double> Weight_o;
	ArrayList<Double> Y;
	ArrayList<Double> Out;
	ArrayList<Double> I;
	ArrayList<Double> X;
	double Mue = 0.1;

	public Algo(int m, int l, int n) {
		Weight_h = new Matrix<>(l,m);
		Weight_o = new Matrix<>(n,l);
		Y = new ArrayList<>(n);
		Out = new ArrayList<>(n);
		I = new ArrayList<>(l);
		X = new ArrayList<>(m);
	}

	public double BackPropagation() {

		ArrayList<Double> Sigma = new ArrayList<>();
		ArrayList<Double> Sigma_o = new ArrayList<>();
		ArrayList<Double> Sigma_h = new ArrayList<>();
		double tmp;
		double MSE = 0;
		for (int i = 0; i < Y.size(); i++) {
			Sigma.add(Y.get(i) - Out.get(i));
			tmp = Out.get(i) * (1 - Out.get(i));
			Sigma_o.add(Sigma.get(i) * tmp);
			MSE += Sigma.get(i) * Sigma.get(i);
		}
		for (int i = 0; i < I.size(); i++) {
			tmp = I.get(i) * (1 - I.get(i));
			double sum = 0;
			for (int j = 0; j < Sigma.size(); j++) {
				sum += Sigma.get(j) * Weight_o.getMat().get(j).get(i);
			}
			Sigma_h.add(sum * tmp);
		}

		for (int k = 0; k < Sigma_o.size(); k++) {
			for (int j = 0; j < I.size(); j++) {
				tmp = Mue * Sigma_o.get(k) * I.get(j);
				tmp += Weight_o.getMat().get(k).get(j);
				Weight_o.getMat().get(k).set(j, tmp);
			}
		}

		for (int j = 0; j < Sigma_h.size(); j++) {
			for (int i = 0; i < X.size(); i++) {
				tmp = Mue * Sigma_h.get(j) * X.get(i);
				tmp += Weight_h.getMat().get(j).get(i);
				Weight_h.getMat().get(j).set(i, tmp);
			}
		}
		MSE *= 0.5;
		return MSE;
	}

	public void FeedForword() {
		ArrayList<Double> Net_h = new ArrayList<>();
		ArrayList<Double> Net_o = new ArrayList<>();
		for (int j = 0; j < l; j++) {
			double sum = 0;
			for (int i = 0; i < m; i++) {
				sum += Weight_h.getMat().get(i).get(i) * X.get(i);
			}
			Net_h.add(sum);
			I.set(j, Sigmoid(Net_h.get(j)));
		}

		for (int k = 0; k < n; k++) {
			double sum = 0;
			for (int j = 0; j < l; j++) {
				sum += Weight_o.getMat().get(k).get(j) * I.get(j);
			}
			Net_o.add(sum);
			Out.set(k, Sigmoid(Net_o.get(k)));
		}

	}

	private Double Sigmoid(Double double1) {

		return 1 / 1 + Math.pow(Math.E, -1 * double1);
	}

}
