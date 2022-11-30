package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReaderCSV {

    String path = "C:\\Users\\Carlos\\eclipse-workspace\\IA_Perceptron\\src\\file\\Maternal Health Risk Data Set.csv";
    File srcFile = new File(path);

    public void readFile(List<Patient> list) {

	// Pega o caminha do arquivo alvo.
	String srcFolder = srcFile.getParent();
	System.out.println(srcFolder);

	// Tentando ler o aquivo.
	try (BufferedReader br = new BufferedReader(new FileReader(path))) {

	    String itemCSV = br.readLine();
	    itemCSV = br.readLine();// Pula a linha de cabe�alho

	    while (itemCSV != null) {
		String[] fields = itemCSV.split(",");

		int age = Integer.parseInt(fields[0]);
		int systolicBP = Integer.parseInt(fields[1]);
		int diastolicBP = Integer.parseInt(fields[2]);
		float bS = Float.parseFloat(fields[3]);
		float bodyTemp = Float.parseFloat(fields[4]);
		int heartRate = Integer.parseInt(fields[5]);

		int RiskLevel = 0;
		if (fields[6].equals("low risk")) {
		    RiskLevel = -1;
		} else if (fields[6].equals("mid risk")) {
		    RiskLevel = 0;
		} else if (fields[6].equals("high risk")) {
		    RiskLevel = 1;
		}

		list.add(new Patient(age, systolicBP, diastolicBP, heartRate, RiskLevel, bS, bodyTemp));
		itemCSV = br.readLine();
	    }

	} catch (IOException e) {

	    System.out.println("Error wrinting file: " + e.getMessage());

	}

    }// readFile end

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
	System.out.println("Age, SystolicBP, DiastolicBP, BS,BodyTemp, HeartRate, RiskLevel");
	for (Patient pt : list) {
	    System.out.println(pt.getAge() + " - " + pt.getSystolicBP() + " - " + pt.getDiastolicBP() + " - "
		    + pt.getBS() + " - " + pt.getBodyTemp() + " - " + pt.getHeartRaate() + " - " + pt.getRiskLevel());
	}

    }

    public void informations(List<Patient> list) {// N�O NECESS�RIO

	for (Patient pt : list) {
	    System.out.println(pt.getBodyTemp() + " --- " + pt.getRiskLevel());
	}

	System.out.println("Tamanho da lista: " + list.size());

    }

}
