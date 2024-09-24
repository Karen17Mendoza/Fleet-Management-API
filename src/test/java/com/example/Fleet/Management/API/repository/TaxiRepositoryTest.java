package com.example.Fleet.Management.API.repository;

import com.example.Fleet.Management.API.model.Taxi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaxiRepositoryTest {

    @Autowired
    private TaxiRepository taxiRepository;

    @BeforeEach
    public void setup() {
        Taxi taxi1 = new Taxi();
        taxi1.setPlate("ABC-123");

        Taxi taxi2 = new Taxi();
        taxi2.setPlate("XYZ-456");

        taxiRepository.save(taxi1);
        taxiRepository.save(taxi2);
    }

    @DisplayName("Testing - should find a taxi by your ID")
    @Test
    public void testFindByPlate() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Taxi> taxisPage = taxiRepository.findByPlateContaining("ABC-123", pageable);

        assertEquals(1, taxisPage.getTotalElements());
        assertEquals("ABC-123", taxisPage.getContent().get(0).getPlate());
    }

    @DisplayName("Testing - should save a taxi by your ID")
    @Test
    public void testSaveTaxi() {
        long countBefore = taxiRepository.count(); // Obtiene el conteo antes de la operación

        Taxi newTaxi = new Taxi();
        newTaxi.setPlate("NEW-789");
        taxiRepository.save(newTaxi);

        long countAfter = taxiRepository.count(); // Obtiene el conteo antes de la operación

        assertEquals(countBefore + 1, countAfter); // Verifica que el conteo haya aumentado en 1
    }

    @DisplayName("Testing - should delete a taxi by your ID")
    @Test
    public void testDeleteTaxi() {
        Taxi taxi = taxiRepository.findByPlateContaining("ABC-123", PageRequest.of(0, 1)).getContent().get(0);
        long countBefore = taxiRepository.count();

        taxiRepository.delete(taxi);
        long countAfter = taxiRepository.count();

        assertEquals(countBefore - 1, countAfter);
    }
}
