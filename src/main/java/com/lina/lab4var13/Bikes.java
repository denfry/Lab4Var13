package com.lina.lab4var13;

public class Bikes extends SportEquipment {
    private int radius;

    public Bikes(String type, String brand, double price, int sum, int radius, String sportEquipmentType) {
        super(type, brand, price, sum, sportEquipmentType);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String toString() {
        return super.toString() + "; Радиус колес - " + radius;
    }
}