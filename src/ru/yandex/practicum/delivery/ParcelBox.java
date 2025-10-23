package ru.yandex.practicum.delivery;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    ArrayList<T> allParcels = new ArrayList<>();

    private final int weight;
    private int currentWeight;

    public ParcelBox(int weight) {
        this.weight = weight;
        currentWeight = 0;
    }

    public void addParcel(T parcel) {
        if ((parcel.getWeight() + currentWeight) > weight) {
            System.out.printf("Превышен вес коробки! Свободно %d, а посылка весит %d",
                              weight - currentWeight, parcel.getWeight());
            System.out.println();
            System.out.println();

            return;
        }

        allParcels.add(parcel);
        System.out.println("Посылка успешно добавлена в коробку!");
        System.out.println();

        currentWeight += parcel.getWeight();
    }

    public int getWeight() {
        return weight;
    }

    public void getInfoParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("В этой коробке нет посылок!");
            return;
        }

        for (int i = 0; i < allParcels.size(); i++) {
            System.out.println(String.format("Посылка %d. %s", i + 1, allParcels.get(i).getDescription()));
        }
    }

    public ArrayList<T> getAllParcels() {
        return allParcels;
    }
}
