package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DeliveryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    //Создание коробок
    private static ParcelBox<StandardParcel> standardParcelParcelBox = new ParcelBox<>(60);
    private static ParcelBox<FragileParcel> fragileParcelParcelBox = new ParcelBox<>(80);
    private static ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(40);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {

            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    System.out.print("Стоимость доставки = ");
                    calculateCosts();
                    System.out.println();
                    break;
                case 4:
                    reportStatusParcels();
                    System.out.println();
                    break;
                case 5:
                    getAllParcelsForBox();
                    System.out.println();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Обновить статус всех отслеживаемых посылок");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        typesForParcelBox("посылка");

        int choice = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите краткое описание посылки: ");
        String description = scanner.nextLine();

        System.out.print("Введите вес(целое число): ");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите адрес места назначения посылки: ");
        String deliveryAddress = scanner.nextLine();

        System.out.print("Введите день месяца, в который посылка была отправлена: ");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                addParcelToParcels(standardParcel);
                standardParcelParcelBox.addParcel(standardParcel);
                break;

            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                addParcelToParcels(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileParcelParcelBox.addParcel(fragileParcel);
                break;

            case 3:
                System.out.print("Введите срок в днях, за который посылка не испортится: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());

                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress,
                                                                         sendDay, timeToLive);
                addParcelToParcels(perishableParcel);
                perishableParcelParcelBox.addParcel(perishableParcel);
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void typesForParcelBox(String obj) {
        System.out.println(String.format("1 — Стандартная %s", obj));
        System.out.println(String.format("2 — Хрупкая %s", obj));
        System.out.println(String.format("3 — Скоропортящаяся %s", obj));
    }

    private static void addParcelToParcels(Parcel parcel) {
        allParcels.add(parcel);
        System.out.println();
        System.out.println("Посылка успешно добавлена!");
    }

    private static void sendParcels() {
        if (allParcels.isEmpty()) {
            System.out.println("Нет посылок для отправления!");
            System.out.println();
            return;
        }

        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
            System.out.println();
        }

        allParcels.clear();
        trackableParcels.clear();
    }

    private static void calculateCosts() {
        int sum = 0;
        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }

        System.out.println(sum);
    }

    private static void reportStatusParcels() {
        System.out.print("Введите новое местоположение посылок: ");
        String newLocation = scanner.nextLine();

        System.out.println();

        for (Trackable parcel : trackableParcels) {
            parcel.reportStatus(newLocation);
        }
    }

    private static void getAllParcelsForBox() {
        System.out.println("Выберите тип коробки:");
        typesForParcelBox("коробка");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                standardParcelParcelBox.getInfoParcels();
                break;
            case 2:
                fragileParcelParcelBox.getInfoParcels();
                break;
            case 3:
                perishableParcelParcelBox.getInfoParcels();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }
}

