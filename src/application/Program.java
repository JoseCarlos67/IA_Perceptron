package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entities.NeuralNetwork;
import entities.Patient;
import entities.ReaderCSV;

public class Program {

    public static void main(String[] args) {

	Locale.setDefault(Locale.US);

	List<Patient> list = new ArrayList<>();
	List<Patient> training = new ArrayList<>();

	ReaderCSV rd = new ReaderCSV();
	rd.readFile(list);
	rd.generateTrainingList(list, training);
	rd.informations(list, training);

	NeuralNetwork nw = new NeuralNetwork(list, training);

	nw.training();
	nw.operationPhase();

    }

}
