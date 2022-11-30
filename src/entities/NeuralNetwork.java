package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeuralNetwork {

    private List<Patient> list = new ArrayList<>();
    private List<Patient> training = new ArrayList<>();
    private double w[] = new double[7];
    private int y = 0;
    private int bias = -1;
    private int epoch = 0;
    private double n = 0.05;

    public NeuralNetwork(List<Patient> list, List<Patient> training) {
	super();
	this.list = list;
	this.training = training;
    }

    public void training() {
//	boolean erroI = false;

	startWeights(w);
	double u = 0;
	System.out.print("Pessos: ");
	for (int j = 0; j < w.length; j++) {
	    System.out.print(w[j] + " ");
	}

	int acerto = 0, erro = 0, cA = 0, cB = 0, cM = 0;

	System.out.println("\n\nValor de u: \n");
	do {

	    for (int i = 151; i < 301; i++) {

		u = activationPotential(i, bias, training, w);

		if (i <= 150) {
		    System.out.println("Alto Risco: " + u);
		    cA++;

		} else if (i > 150 && i <= 300) {
		    System.out.println("Baixo Risco: " + u);
		    cB++;
		} else {
		    System.out.println("Médio risco: " + u);
		    cM++;
		}
		// System.out.println("B = " + cB + " M = " + cM + " A = " + cA);
		y = activationFunction(u, y);
		int d = training.get(i).getRiskLevel();
		if (d != y) {
		    erro++;
		    recalculatingWeights(w, n, y, i, bias, training);
//		    erroI = false;
		} else {
		    acerto++;
//		    erroI = true;
		}

	    }
	    epoch++;

	} while (epoch == 1000);

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
//	    w[i] = rand.nextDouble(0, 1);
	    w[i] = rand.nextDouble(1);
	    if (i % 2 == 0) {
		w[i] *= -1;
	    }
	}

    }

    public static double activationPotential(int i, int bias, List<Patient> training, double[] w) {// Potencial
												   // de
												   // ativação
	return w[0] * bias + w[1] * training.get(i).getAge() + w[2] * training.get(i).getSystolicBP()
		+ w[3] * training.get(i).getDiastolicBP() + w[4] * training.get(i).getBS()
		+ w[5] * training.get(i).getBodyTemp() + w[6] * training.get(i).getHeartRaate();
    }

    public static int activationFunction(double u, int y) {// Degrau Bipolar
	if (u < 300)
	    return 1;
	else if (u > 000)
	    return -1;
	else
	    return 0;
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
