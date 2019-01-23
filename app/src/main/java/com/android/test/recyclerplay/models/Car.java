package com.android.test.recyclerplay.models;

public class Car {

    private String brandName;
    private String modelName;

    public Car() {
    }

    public Car(String brandName, String modelName) {
        this.brandName = brandName;
        this.modelName = modelName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
