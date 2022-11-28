package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

	private List<Patient> list = new ArrayList<>();
	private List<Patient> training = new ArrayList<>();
	private double w[] = new double[7];
	private int y = 0;
	private int bias = 1;

	private int epoch = 0;
	private double n = 0.05;

	public NeuralNetwork(List<Patient> list, List<Patient> training) {
		super();
		this.list = list;
		this.training = training;
	}

	public void generateTrainingList(List<Patient> list, List<Patient> training) {

		int low = 0, mid = 0, high = 0;

		for (Patient pat : list) {

			if (pat.getRiskLevel() == -1) {
				if (low < 150) {
					training.add(pat);
					low++;
				}
			}
			if (pat.getRiskLevel() == 0) {
				if (mid < 150) {
					training.add(pat);
					mid++;
				}
			}
			if (pat.getRiskLevel() == 1) {
				if (high < 150) {
					training.add(pat);
					high++;
				}

			}
		}

	}

	public void training() {
		boolean erroI = false;
		
		startWeights(w);
		
		System.out.print("Pessos: ");
		for (int j = 0; j < w.length; j++) {
			System.out.print(w[j] + " ");
		}

		int acerto = 0, erro = 0;
		
		System.out.println("\n\nValor de u: \n");
		do {

			for (int i = 0; i < training.size(); i++) {
				double u = 0;
				u = activationPotential(u, i, bias, training, w);
				System.out.println(u);
				y = activationFunction(u, y);
				int d = training.get(i).getRiskLevel();
				if (y != d) {
					recalculatingWeights(w, n, y, i, bias, training);
					erro++;
//		    erroI = false;
				} else {
					acerto++;
//		    erroI = true;
				}
			}
			epoch++;
		} while (epoch == 100);

		System.out.println();
		System.out.println("Acerto: " + acerto);
		System.out.println("Erro: " + erro);
		System.out.println();
		for (int j = 0; j < w.length; j++) {
			System.out.print(w[j] + " ");
		}

	}

	private static void startWeights(double[] w) {

		Random rand = new Random();

		for (int i = 0; i < w.length; i++) {
			w[i] = rand.nextDouble(0, 1);
		}

	}

	public static double activationPotential(double u, int i, int bias, List<Patient> training, double[] w) {// Potencial
																												// de
																												// ativação
		return w[0] * bias + w[1] * training.get(i).getAge() + w[2] * training.get(i).getSystolicBP()
				+ w[3] * training.get(i).getDiastolicBP() + w[4] * training.get(i).getBS()
				+ w[5] * training.get(i).getBodyTemp() + w[6] * training.get(i).getHeartRaate();
	}

	public static int activationFunction(double u, int y) {// Degrau Bipolar
		if (u > 0)
			return 1;
		else if (u == 0)
			return 0;
		else
			return -1;
	}

	public static void recalculatingWeights(double[] w, double n, int y, int i, int bias, List<Patient> training) {

		w[0] = w[0] + n * (training.get(i).getRiskLevel() - y) * bias;
		w[1] = w[1] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getAge();
		w[2] = w[2] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getSystolicBP();
		w[3] = w[3] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getDiastolicBP();
		w[4] = w[4] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getBS();
		w[5] = w[5] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getBodyTemp();
		w[6] = w[6] + n * (training.get(i).getRiskLevel() - y) * training.get(i).getHeartRaate();

	}
}
