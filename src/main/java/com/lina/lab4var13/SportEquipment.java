package com.lina.lab4var13;

public class SportEquipment {
    private String type;
    private String brand;
    private double price;
    private int sum;
    private String sportEquipmentType;


    public SportEquipment(String type, String brand, double price, int sum, String sportEquipmentType) {
        this.type = type;
        this.brand = brand;
        this.price = price;
        this.sum = sum;
        this.sportEquipmentType = sportEquipmentType;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getSum() {
        return sum;
    }

    public String getSportEquipmentType() {
        return sportEquipmentType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setSportEquipmentType(String sportEquipmentType) {
        this.sportEquipmentType = sportEquipmentType;
    }

    public String toString() {
        return "Тип товара - " + getType() + "; производитель - " + getBrand() + "; цена - " + getPrice() + "; количество - "
                + getSum() + "\n";
    }

}

