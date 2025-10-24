package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable{

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.printf("Посылка <<%s>> обёрнута в защитную плёнку%n", getDescription());
        System.out.println(getMessPackageItem());
    }

    @Override
    public int getBaseCost() {
        return 4;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.printf("Хрупкая посылка <<%s>> изменила местоположение на %s%n", getDescription(), newLocation);
    }
}
