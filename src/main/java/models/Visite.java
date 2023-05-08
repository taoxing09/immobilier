package models;

import java.sql.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "visite")
public class Visite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_visite", nullable = false)
    private Date dateVisite;

    @Column(name = "heure_visite", nullable = false)
    private Time heureVisite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locataire_id", nullable = false)
    private Locataire locataire;
    
    @Column(name = "commentaire")
    private String commentaire;
    
    public Visite() {
    	
    }
    
    public Visite(Integer id, Locataire locataire, Bien bien, Date dateVisite, Time heureVisite, String commentaire) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = 0;
        }
        this.locataire = locataire;
        this.bien = bien;
        this.dateVisite = dateVisite;
        this.heureVisite = heureVisite;
        this.commentaire = commentaire;
    }
    
    public Visite(Locataire locataire, Bien bien, Date dateVisite, Time heureVisite, String commentaire) {
        this.id = 0;
        this.locataire = locataire;
        this.bien = bien;
        this.dateVisite = dateVisite;
        this.heureVisite = heureVisite;
        this.commentaire = commentaire;
    }

    
    
    // Getters, setters et autres méthodes
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(Date dateVisite) {
        this.dateVisite = dateVisite;
    }

    public Time getHeureVisite() {
        return heureVisite;
    }

    public void setHeureVisite(Time heureVisite) {
        this.heureVisite = heureVisite;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getIdLocataire() {
        return locataire.getId();
    }

    public int getIdBien() {
        return bien.getId();
    }

}
