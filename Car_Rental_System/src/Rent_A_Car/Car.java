package Rent_A_Car;

public class Car {
	private String carId;
	private String brand;
	private String model;
	private double basePricePerDay;
	private boolean isAvailable;
	
	public Car(String carId,String brand,String model,double basePricePerDay) {
		this.carId=carId;
		this.brand=brand;
		this.model=model;
		this.basePricePerDay=basePricePerDay;
		this.isAvailable=true;
	}
	
	public String getCarId() {
		return carId;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	
	public double getBasePricePerDay() {
		return basePricePerDay;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	
	public double calculatePrice(int rentalDays) {
		return basePricePerDay * rentalDays;
	}
	
	public void rent() {
		isAvailable=false;
	}
	public void returnCar() {
		isAvailable=true;
	}
}
