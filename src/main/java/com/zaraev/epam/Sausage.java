package com.zaraev.epam;

public class Sausage {

    private String type;

    private int weight;

    private long cost;

    public Sausage(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }

    public Sausage() {
        this.type = "NoName";
        this.weight = 100;
        this.cost = 300;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{"
                 + type +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
