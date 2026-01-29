package com.sebastian.crudVehicles.models;

public class Car extends Vehicle {
    private int num_Doors;
    private String pedals;
    private String fuel;

    //Constructor
    public Car(String brand, String model, int year, int num_Doors, String pedals, String fuel) {

        super(brand, model, year); // palabra super va primero

        this.num_Doors = num_Doors;
        this.pedals = pedals;
        this.fuel = fuel;
    }

    // Getters & Setters
    public int getNumDoors() {
        return num_Doors;
    }

    public void setNum_Doors(int numDoors) {
        this.num_Doors = numDoors;
    }

    public String getPedals() {
        return pedals;
    }

    public void setPedals(String pedals) {
        this.pedals = pedals;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.pedals = pedals;
    }

    // Polymorphism

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Doors: " + num_Doors);
        System.out.println("Fuel: " + fuel);
        System.out.println("Pedals: " + pedals);
    }
}