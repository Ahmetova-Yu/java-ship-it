package ru.yandex.practicum.delivery;

import static java.lang.String.format;

public abstract class Parcel {

    private final String description;
    private final int weight;
    private final String deliveryAddress;
    private final int sendDay;

    public final String massegePackageItem = format("Посылка <<%s>> упакована", getDescription());

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    public void packageItem() {
        System.out.println(massegePackageItem);
    }

    public void deliver() {
        System.out.println(format("Посылка <<%s>> доставлена по адресу %s", description, deliveryAddress));
    }

    public double calculateDeliveryCost() {
        return weight * getBaseCost();
    }

    public abstract int getBaseCost();

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getSendDay() {
        return sendDay;
    }
}
