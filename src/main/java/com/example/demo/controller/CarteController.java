package com.example.demo.controller;

import com.example.demo.clas.Carte;
import com.example.demo.clas.Cliente;
import com.example.demo.repository.CarteRepository;
import com.example.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/carte")
public class CarteController {

    @Autowired
    private CarteRepository carteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/genera-iniziali")
    public List<Carte> generaCarteIniziali() {
        List<Carte> carte = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Carte carta = new Carte();
            carta.setNumeroCarta(generaNumeroCartaUnivoco());
            carta.setDataEmissione(new Date());
            carta.setDataCreazione(new Date());
            carta.setDataScadenza(calcolaDataScadenza());
            carta.setStato(0);
            carta.setPunti(0);
            // Non associamo ancora la carta a un cliente

            carte.add(carta);
        }

        return carteRepository.saveAll(carte);
    }

    // genera un numero carta univoco
    private String generaNumeroCartaUnivoco() {
        // Genera un numero di carta casuale di 12 cifre
        Random rand = new Random();
        StringBuilder numeroCarta = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            numeroCarta.append(rand.nextInt(10));
        }
        return numeroCarta.toString();
    }

    // Implementa la logica per calcolare la data di scadenza
    private Date calcolaDataScadenza() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1); // Scadenza tra un anno
        return calendar.getTime();
    }

    @PostMapping("/assegna-a-cliente")
    public Carte assegnaCartaACliente(@RequestParam Long clienteId, @RequestParam Long cartaId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente non trovato con ID: " + clienteId));

        Carte carta = carteRepository.findById(cartaId)
                .orElseThrow(() -> new RuntimeException("Carta non trovata con ID: " + cartaId));

        if (carta.getCliente() != null) {
            throw new RuntimeException("La carta è già stata assegnata a un cliente.");
        }

        carta.setCliente(cliente);
        carta.setStato(1); // Imposta lo stato su "Attiva"

        return carteRepository.save(carta);
    }

    @PostMapping("/disattiva-carta")
    public Carte disattivaCarta(@RequestParam Long cartaId) {
        Carte carta = carteRepository.findById(cartaId)
                .orElseThrow(() -> new RuntimeException("Carta non trovata con ID: " + cartaId));

        if (carta.getStato() == 0) {
            throw new RuntimeException("Non puoi disattivare una carta non ancora assegnata.");
        }

        carta.setStato(2); // Imposta lo stato su "Bloccata"
        return carteRepository.save(carta);
    }

    @PostMapping("/attiva-carta")
    public Carte attivaCarta(@RequestParam Long cartaId) {
        Carte carta = carteRepository.findById(cartaId)
                .orElseThrow(() -> new RuntimeException("Carta non trovata con ID: " + cartaId));

        if (carta.getStato() == 0) {
            throw new RuntimeException("Non puoi attivare una carta non ancora assegnata.");
        }

        carta.setStato(1); // Imposta lo stato su "Attiva"
        return carteRepository.save(carta);
    }
}
