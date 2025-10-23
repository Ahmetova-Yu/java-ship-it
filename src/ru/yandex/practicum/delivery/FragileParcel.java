package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel{

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println(String.format("Посылка <<%s>> обёрнута в защитную плёнку", getDescription()));
        System.out.println(getMessPackageItem());
    }

    @Override
    public int getBaseCost() {
        return 4;
    }
}
