package entities;

public class Patient {

    private int age, systolicBP, diastolicBP, heartRaate, riskLevel;
    private double BS, bodyTemp;

    // Constructor start
    public Patient() {

    }

    public Patient(int age, int systolicBP, int diastolicBP, int heartRaate, int riskLevel, double bS,
	    
	double bodyTemp) {
	this.age = age;
	this.systolicBP = systolicBP;
	this.diastolicBP = diastolicBP;
	this.heartRaate = heartRaate;
	this.riskLevel = riskLevel;
	this.BS = bS;
	this.bodyTemp = bodyTemp;
	
    } // Constructor end

    // Getters and Setters start
    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public int getSystolicBP() {
	return systolicBP;
    }

    public void setSystolicBP(int systolicBP) {
	this.systolicBP = systolicBP;
    }

    public int getDiastolicBP() {
	return diastolicBP;
    }

    public void setDiastolicBP(int diastolicBP) {
	this.diastolicBP = diastolicBP;
    }

    public int getHeartRaate() {
	return heartRaate;
    }

    public void setHeartRaate(int heartRaate) {
	this.heartRaate = heartRaate;
    }

    public int getRiskLevel() {
	return riskLevel;
    }

    public void setRiskLevel(int riskLevel) {
	this.riskLevel = riskLevel;
    }

    public double getBS() {
	return BS;
    }

    public void setBS(double bS) {
	this.BS = bS;
    }

    public double getBodyTemp() {
	return bodyTemp;
    }

    public void setBodyTemp(double bodyTemp) {
	this.bodyTemp = bodyTemp;
    }

    // Getters and Setters end

}
