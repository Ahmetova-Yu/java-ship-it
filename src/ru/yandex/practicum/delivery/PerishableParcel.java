package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel{
    private final int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public int getBaseCost() {
        return 3;
    }

    public boolean isExpired(int currentDay) {
        int sumLiving = getSendDay() + timeToLive;

        return sumLiving < currentDay;
    }
}
