package com.example.demo.clas;

import com.example.demo.repository.CarteRepository;
import com.example.demo.repository.ProdottoRepository;
import com.example.demo.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

@Service
public class Scontrino {

    private final CarteRepository carteRepository;
    private final ProdottoRepository prodottoRepository;
    private final CarteService carteService;
    private Carte carta;
    private List<Prodotto> prodotti;
    private float prezzoTotale;
    private int puntiGuadagnati;

    @Autowired
    public Scontrino(
            CarteRepository carteRepository,
            ProdottoRepository prodottoRepository,
            CarteService carteService) {
        this.carteRepository = carteRepository;
        this.prodottoRepository = prodottoRepository;
        this.carteService = carteService;
    }

    public void generaScontrino() {
        Random random = new Random();

        System.out.println("Scontrino:");
        System.out.println("Scontrino:");

        // Cerca una carta esistente nel database
        List<Carte> carteDisponibili = carteRepository.findAll();
        if (!carteDisponibili.isEmpty()) {
            carta = carteDisponibili.get(random.nextInt(carteDisponibili.size()));
            System.out.println("Carta: " + carta.getNumeroCarta());
        } else {
            System.out.println("Nessuna carta disponibile");
        }


        System.out.println("Prodotti:");

        // Preleva da 1 a 10 prodotti random
        prodotti = new ArrayList<>();
        int numeroProdotti = random.nextInt(10) + 1;
        for (int i = 0; i < numeroProdotti; i++) {
            List<Prodotto> prodottiDisponibili = prodottoRepository.findAll();
            if (!prodottiDisponibili.isEmpty()) {
                Prodotto prodotto = prodottiDisponibili.get(random.nextInt(prodottiDisponibili.size()));
                prodotti.add(prodotto);
            }
        }

        // Calcola il prezzo totale e i punti guadagnati
        prezzoTotale = 0;
        for (Prodotto prodotto : prodotti) {
            prezzoTotale += prodotto.getPrice();
        }
        puntiGuadagnati = (int) (prezzoTotale * 0.20); // 20% del prezzo totale

        // Aggiungi i punti guadagnati alla carta
        if (carta != null) {
            carta.setPunti(carta.getPunti() + puntiGuadagnati);
            carteRepository.save(carta);
        }

        // Stampa i dettagli dello scontrino
        for (Prodotto prodotto : prodotti) {
            System.out.println("- " + prodotto.getDescription() + " x1");
        }
        System.out.println("Prezzo Totale: " + prezzoTotale);
        System.out.println("Punti Guadagnati: " + puntiGuadagnati);
    }

    }


