package com.example.proektna;

public class ParkingModel {

    private int id;
    private String parking_name;
    private String city_name;
    private int free;
    private int taken;

    public ParkingModel(int id, String parking_name, String city_name, int free, int taken) {
        this.id = id;
        this.parking_name = parking_name;
        this.city_name = city_name;
        this.free = free;
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "ParkingModel{" +
                "id=" + id +
                ", parking_name='" + parking_name + '\'' +
                ", city_name='" + city_name + '\'' +
                ", free=" + free +
                ", taken=" + taken +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParking_name() {
        return parking_name;
    }

    public void setParking_name(String parking_name) {
        this.parking_name = parking_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public int getTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }
}
