package com.example.demo.controller;

import com.example.demo.clas.Carte;
import com.example.demo.clas.Scontrino;
import com.example.demo.repository.CarteRepository;
import com.example.demo.repository.ProdottoRepository;
import com.example.demo.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scontrini")
public class ScontrinoController {

    private final CarteRepository carteRepository;
    private final ProdottoRepository prodottoRepository;
    private final CarteService carteService;
    private final Scontrino scontrino;

    @Autowired
    public ScontrinoController(
            CarteRepository carteRepository,
            ProdottoRepository prodottoRepository,
            CarteService carteService,
            Scontrino scontrino) {
        this.carteRepository = carteRepository;
        this.prodottoRepository = prodottoRepository;
        this.carteService = carteService;
        this.scontrino = scontrino;
    }


    @PostMapping("/genera-scontrino")
    public void generaScontrino() {
        scontrino.generaScontrino();
    }
}

