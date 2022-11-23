package application;

import java.util.Locale;

import entities.ReaderCSV;

public class Program {

    public static void main(String[] args) {

	Locale.setDefault(Locale.US);

	ReaderCSV rd = new ReaderCSV();
	rd.readFile();
	rd.informations();
	
    }

}
