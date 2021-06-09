package com.yang.shape;

public class Point {
    private String name;

    public Point(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                '}';
    }
}
