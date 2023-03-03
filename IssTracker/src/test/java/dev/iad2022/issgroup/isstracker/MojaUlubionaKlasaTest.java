package dev.iad2022.issgroup.isstracker;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MojaUlubionaKlasaTest {
    @Test
    public void testDodajDwa() {
        MojaUlubionaKlasa mojaUlubionaKlasa = new MojaUlubionaKlasa();
        assertEquals(10, mojaUlubionaKlasa.dodajDwa(5,5));
    }

    @Test
    public void testOdejmijDwa() {
        assertEquals(5, MojaUlubionaKlasa.odejmijDwa(10, 5));
    }
}