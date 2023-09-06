package com.example.demo.clas;

import com.example.demo.repository.CarteRepository;

import java.util.Random;

public class CartaGenerator {
    private final CarteRepository carteRepository; // Inietta repository

    public CartaGenerator(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public String generaNumeroCartaUnivoco() {
        String numeroCarta;
        do {
            numeroCarta = generaNumeroCarta(); // Genera un nuovo numero di carta
        } while (carteRepository.existsByNumeroCarta(numeroCarta)); // Verifica se il numero è già presente

        return numeroCarta;
    }

    //Genera numeri casuali e restituisce 10 carte

    private String generaNumeroCarta() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
