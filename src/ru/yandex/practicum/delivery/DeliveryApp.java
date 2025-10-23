package ru.yandex.practicum.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();

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
                    calculateCosts();
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
        System.out.println("0 — Завершить");
    }

    private static void addParcel() {
        System.out.println("Выберите тип посылки:");
        System.out.println("1 — Стандартная посылка");
        System.out.println("2 — Хрупкая посылка");
        System.out.println("3 — Скоропортящаяся посылка");

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
                break;

            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                addParcelToParcels(fragileParcel);
                break;

            case 3:
                System.out.print("Введите срок в днях, за который посылка не испортится: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());

                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress,
                                                                         sendDay, timeToLive);
                addParcelToParcels(perishableParcel);
                break;

            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void addParcelToParcels(Parcel parcel) {
        allParcels.add(parcel);
        System.out.println("Посылка успешно добавлена!");
        System.out.println();
    }

    private static void sendParcels() {
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
            System.out.println();
        }
    }

    private static void calculateCosts() {
        int sum = 0;

        for (Parcel parcel : allParcels) {
            sum += parcel.calculateDeliveryCost();
        }

        System.out.println(sum);
    }
}

