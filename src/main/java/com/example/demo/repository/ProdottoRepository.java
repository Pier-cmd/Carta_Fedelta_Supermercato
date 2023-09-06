package com.example.demo.repository;

import com.example.demo.clas.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
}
