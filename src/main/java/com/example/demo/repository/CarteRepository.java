package com.example.demo.repository;


import com.example.demo.clas.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, Long> {
    boolean existsByNumeroCarta(String numeroCarta);
}
