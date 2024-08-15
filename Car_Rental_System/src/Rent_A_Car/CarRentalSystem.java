package Rent_A_Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
	
	private List<Car> cars;
	private List<Customer> customers;
	private List<Rental> rentals;
	
	public CarRentalSystem() {
		cars=new ArrayList<Car>();
		customers=new ArrayList<Customer>();
		rentals=new ArrayList<Rental>();
	}
	
	public void addCar(Car car) {
		cars.add(car);
	}
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	public void rentCar(Car car,Customer customer, int days) {
		if(car.getIsAvailable()) {
			car.rent();
			rentals.add(new Rental(car,customer,days));
		}
		else {
			System.out.println("car is not available for rent");
		}
	}
	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove=null;
		for(Rental rental:rentals) {
			if(rental.getCar()==car) {
				rentalToRemove=rental;
				break;
			}
		}
		if(rentalToRemove!=null) {
			rentals.remove(rentalToRemove);
		}
		else {
			System.out.println("car was not rented");
		}
	}
	
	 public void menu() {
	        Scanner sc = new Scanner(System.in);

	        while (true) {
	            System.out.println("===== Car Rental System =====");
	            System.out.println("1. Rent a Car");
	            System.out.println("2. Return a Car");
	            System.out.println("3. Exit");
	            System.out.print("Enter your choice: ");

	            int choice = sc.nextInt();
	            sc.nextLine(); 

	            if (choice == 1) {
	                System.out.println("\n== Rent a Car ==\n");
	                System.out.print("Enter your name: ");
	                String customerName = sc.nextLine();

	                System.out.println("\nAvailable Cars:");
	                for (Car car : cars) {
	                    if (car.getIsAvailable()) {
	                        System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel());
	                    }
	                }

	                System.out.print("\nEnter the car ID you want to rent: ");
	                String carId = sc.nextLine();

	                System.out.print("Enter the number of days for rental: ");
	                int rentalDays = sc.nextInt();
	                sc.nextLine(); 

	                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
	                addCustomer(newCustomer);

	                Car selectedCar = null;
	                for (Car car : cars) {
	                    if (car.getCarId().equals(carId) && car.getIsAvailable()) {
	                        selectedCar = car;
	                        break;
	                    }
	                }

	                if (selectedCar != null) {
	                    double totalPrice = selectedCar.calculatePrice(rentalDays);
	                    System.out.println("\n== Rental Information ==\n");
	                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
	                    System.out.println("Customer Name: " + newCustomer.getName());
	                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
	                    System.out.println("Rental Days: " + rentalDays);
	                    System.out.printf("Total Price: " + totalPrice);

	                    System.out.print("\nConfirm rental (Y/N): ");
	                    String confirm = sc.nextLine();

	                    if (confirm.equalsIgnoreCase("Y")) {
	                        rentCar(selectedCar, newCustomer, rentalDays);
	                        System.out.println("\nCar rented successfully.");
	                    } else {
	                        System.out.println("\nRental canceled.");
	                    }
	                } else {
	                    System.out.println("\nInvalid car selection or car not available for rent.");
	                }
	            } else if (choice == 2) {
	                System.out.println("\n== Return a Car ==\n");
	                System.out.print("Enter the car ID you want to return: ");
	                String carId = sc.nextLine();

	                Car carToReturn = null;
	                for (Car car : cars) {
	                    if (car.getCarId().equals(carId) && !car.getIsAvailable()) {
	                        carToReturn = car;
	                        break;
	                    }
	                }

	                if (carToReturn != null) {
	                    Customer customer = null;
	                    for (Rental rental : rentals) {
	                        if (rental.getCar() == carToReturn) {
	                            customer = rental.getCustomer();
	                            break;
	                        }
	                    }

	                    if (customer != null) {
	                        returnCar(carToReturn);
	                        System.out.println("Car returned successfully by " + customer.getName());
	                    } else {
	                        System.out.println("Car was not rented or rental information is missing.");
	                    }
	                } else {
	                    System.out.println("Invalid car ID or car is not rented.");
	                }
	            } else if (choice == 3) {
	                break;
	            } else {
	                System.out.println("Invalid choice. Please enter a valid option.");
	            }
	        }

	        System.out.println("\nThank you for using the Car Rental System!");
	    }

	public static void main(String[] args) {
		CarRentalSystem crs=new CarRentalSystem();
		
		Car car1=new Car("C001","Toyota","Camry",80.7);
		Car car2=new Car("C002","Honda","Accord",60.3);
		Car car3=new Car("C002","Mahindra","Thar",90.7);
		
		crs.addCar(car1);
		crs.addCar(car2);
		crs.addCar(car3);
		
		crs.menu();
	}

	
}
