package com.example.demo.service;

import com.example.demo.clas.Carte;
import com.example.demo.repository.CarteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarteService {

    private final CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    @Transactional
    public void aggiornaPuntiCarta(Carte carta, int puntiGuadagnati) {
        try {
            int nuoviPunti = carta.getPunti() + puntiGuadagnati;
            carta.setPunti(nuoviPunti);
            carteRepository.save(carta); // Assicurati che carteRepository sia iniettato correttamente
        } catch (Exception e) {
            // Gestisci l'eccezione in modo appropriato, ad esempio registrandola o lanciando un'eccezione personalizzata
            throw new RuntimeException("Errore durante l'aggiornamento dei punti della carta", e);
        }
    }
}
