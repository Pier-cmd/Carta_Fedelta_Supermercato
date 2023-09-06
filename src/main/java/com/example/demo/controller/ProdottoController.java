package com.example.demo.controller;

import com.example.demo.clas.Prodotto;
import com.example.demo.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prodotti")
public class ProdottoController {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @GetMapping("/tutti")
    public List<Prodotto> getTuttiProdotti() {
        return prodottoRepository.findAll();
    }

    // Metodo per ottenere un prodotto per ID
    @GetMapping("/{id}")
    public Prodotto getProdottoById(@PathVariable Long id) {
        return prodottoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con ID: " + id));
    }

    @PostMapping("/crea")
    public Prodotto creaProdotto(@RequestBody Prodotto prodotto) {
        return prodottoRepository.save(prodotto);
    }


    @PutMapping("/aggiorna/{id}")
    public Prodotto aggiornaProdotto(@PathVariable Long id, @RequestBody Prodotto nuovoProdotto) {
        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodotto non trovato con ID: " + id));

        prodotto.setProdottoCode(nuovoProdotto.getProdottoCode());
        prodotto.setDescription(nuovoProdotto.getDescription());
        prodotto.setPrice(nuovoProdotto.getPrice());
        prodotto.setPoints(nuovoProdotto.getPoints());

        return prodottoRepository.save(prodotto);
    }

    @DeleteMapping("/elimina/{id}")
    public void eliminaProdotto(@PathVariable Long id) {
        prodottoRepository.deleteById(id);
    }
}
