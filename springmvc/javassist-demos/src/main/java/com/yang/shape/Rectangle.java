package com.yang.shape;

public class Rectangle {
    private String name;

    public Rectangle(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "name='" + name + '\'' +
                '}';
    }
}
