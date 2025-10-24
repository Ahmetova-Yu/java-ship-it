package ru.yandex.practicum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.FragileParcel;
import ru.yandex.practicum.delivery.ParcelBox;
import ru.yandex.practicum.delivery.PerishableParcel;
import ru.yandex.practicum.delivery.StandardParcel;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeliveryCostTest {
    @Test
    public void shouldReturnCostStandardParcel() {
        StandardParcel standardParcel = new StandardParcel("Сумки", 10, "г. Москва", 4);
        assertEquals(20, standardParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturnCostPerishableParcel() {
        PerishableParcel perishableParcel = new PerishableParcel("Сыры", 10,
                                                             "г. Калуга", 3, 6);
        assertEquals(30, perishableParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturnCostFragileParcel() {
        FragileParcel fragileParcel = new FragileParcel("Тарелки", 10,"г. Краснодар", 7);
        assertEquals(40, fragileParcel.calculateDeliveryCost());
    }

    @Test
    public void shouldReturnFalseIsExpiredBeforeExpirationDay() {
        PerishableParcel perishableParcel = new PerishableParcel("Йогурт", 10,
                "г. Калуга", 2, 3);

        assertFalse(perishableParcel.isExpired(4));
    }

    @Test
    public void shouldReturnTrueIsExpiredAfterExpirationDay() {
        PerishableParcel perishableParcel = new PerishableParcel("Йогурт", 10,
                "г. Калуга", 2, 3);

        assertTrue(perishableParcel.isExpired(6));
    }

    @Test
    public void shouldReturnFalseIsExpiredOnExpirationDay() {
        PerishableParcel perishableParcel = new PerishableParcel("Йогурт", 10,
                "г. Калуга", 2, 3);

        assertFalse(perishableParcel.isExpired(5));
    }

    @Test
    public void addingParcelToBox() {
        ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(30);
        PerishableParcel perishableParcel = new PerishableParcel("Йогурт", 10,
                "г. Калуга", 3, 2);

        perishableParcelParcelBox.addParcel(perishableParcel);
        assertEquals(1, perishableParcelParcelBox.getAllParcels().size());
    }

    @Test
    public void notAddingParcelToBox() {
        ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(30);
        PerishableParcel perishableParcel = new PerishableParcel("Йогурт", 40,
                "г. Калуга", 3, 2);

        perishableParcelParcelBox.addParcel(perishableParcel);
        assertEquals(0, perishableParcelParcelBox.getAllParcels().size());
    }

    @Test
    public void addingParcelToBoxExactWeight() {
        ParcelBox<PerishableParcel> perishableParcelParcelBox = new ParcelBox<>(30);
        PerishableParcel perishableParcel = new PerishableParcel("Йогурт", 30,
                "г. Калуга", 3, 2);

        perishableParcelParcelBox.addParcel(perishableParcel);
        assertEquals(1, perishableParcelParcelBox.getAllParcels().size());
    }
}
