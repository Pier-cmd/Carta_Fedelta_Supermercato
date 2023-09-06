package com.example.demo.clas;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carte")
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_carta", unique = true)
    private String numeroCarta;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissione")
    private Date dataEmissione;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_creazione")
    private Date dataCreazione;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_scadenza")
    private Date dataScadenza;

    @Column(name = "stato")
    private int stato; // 0-Creata / 1-Attiva / 2-Bloccata / 3-Eliminata

    @Column(name = "punti")
    private int punti;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "date_attive")
    private boolean dateAttive; // campo per gestire lo stato delle date

    public Carte() {
        dateAttive = false; // Le date sono disabilitate di default
    }

    public boolean areDateAttive() {
        return dateAttive;
    }

    public void abilitaDate() {
        dateAttive = true;
    }

    public void disabilitaDate() {
        dateAttive = false;
    }

    // Metodo per impostare la data di emissione solo se le date sono attive
    public void setDataEmissione(Date dataEmissione) {
        if (dateAttive) {
            this.dataEmissione = dataEmissione;
        }
    }

    // Metodo per impostare la data di scadenza solo se le date sono attive
    public void setDataScadenza(Date dataScadenza) {
        if (dateAttive) {
            this.dataScadenza = dataScadenza;
        }
    }



    public Long getId() {
        return id;
    }

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public Date getDataEmissione() {
        return dataEmissione;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }


    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public int getStato() {
        return stato;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
