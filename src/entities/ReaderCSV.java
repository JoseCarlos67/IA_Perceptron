package entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderCSV {

    String path = "C:\\Users\\Carlos\\eclipse-workspace\\IA_Perceptron\\src\\file\\Maternal Health Risk Data Set.csv";
    File srcFile = new File(path);
    List<Patient> list = new ArrayList<>();

    public void readFile() {

	// Pega o caminha do arquivo alvo.
	String srcFolder = srcFile.getParent();
	System.out.println(srcFolder);

	// Tentando ler o aquivo.
	try (BufferedReader br = new BufferedReader(new FileReader(path))) {

	    String itemCSV = br.readLine();
	    itemCSV = br.readLine();// Pula a linha de cabeçalho

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
		    RiskLevel = 0;
		} else if (fields[6].equals("mid risk")) {
		    RiskLevel = 1;
		} else if (fields[6].equals("high risk")) {
		    RiskLevel = 2;
		}

		list.add(new Patient(age, systolicBP, diastolicBP, heartRate, RiskLevel, bS, bodyTemp));
		itemCSV = br.readLine();
	    }

	} catch (IOException e) {

	    System.out.println("Error wrinting file: " + e.getMessage());

	}

    }//readFile end

    public void informations() {//NÃO NECESSÁRIO

	for (Patient pt : list) {
	    System.out.println(pt.getBodyTemp() + " --- " + pt.getRiskLevel());
	}

	System.out.println("Tamanho da lista: " + list.size());

    }//

}
