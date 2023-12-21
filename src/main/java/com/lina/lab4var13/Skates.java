package com.lina.lab4var13;


public class Skates extends SportEquipment {
    private int size;
    private boolean proffessional;

    public Skates(String type, String brand, double price, int sum, boolean proffessional, String sportEquipmentType, int size) {
        super(type, brand, price, sum, sportEquipmentType);
        this.size = size;
        this.proffessional = proffessional;
    }

    public int getSize() {

        return size;
    }

    public boolean getProffessional() {
        return proffessional;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setProffessional(boolean proffessional) {
        this.proffessional = proffessional;
    }

    public String toString() {
        return super.toString() + "; size - " + size + "; proffessional - " + proffessional;
    }
}