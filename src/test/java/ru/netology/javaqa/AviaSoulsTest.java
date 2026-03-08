package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;


public class AviaSoulsTest {
    Ticket t1 = new Ticket("SVO", "LED", 5_000, 9, 12);
    Ticket t2 = new Ticket("SVO", "LED", 3_000, 10, 12);

    @Test
    public void shouldCompareByPrice() {
        int actual = t1.compareTo(t2);
        Assertions.assertTrue(actual > 0);
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(t1);
        manager.add(t2);


        Ticket[] actual = manager.search("SVO", "LED");
        Ticket[] expected = new Ticket[]{t2, t1};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyIfNoTickets() {
        AviaSouls manager = new AviaSouls();
        manager.add(t1);
        manager.add(t2);

        Ticket[] actual = manager.search("VKO", "ATH");
        Ticket[] expected = new Ticket[]{};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();
        int actual = comparator.compare(t2, t1);
        Assertions.assertTrue(actual < 0);
    }

    @Test
    public void shouldSearchAndSortByTime() {
        AviaSouls manager = new AviaSouls();
        manager.add(t1);
        manager.add(t2);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.searchAndSortBy("SVO", "LED", comparator);
        Ticket[] expected = new Ticket[]{t2, t1,};

        Assertions.assertArrayEquals(expected, actual);
    }
}
