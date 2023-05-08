package models;

import java.time.LocalDate;

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
@Table(name = "locataire")
public class Locataire {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;
    
    @Column(nullable = false)
    private String adresse;
    
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String telephone;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien_id", referencedColumnName = "id", nullable = true)
    private Bien bienLoue;

    public Locataire() {}

    public Locataire(int id, String nom, String prenom, LocalDate dateNaissance, String adresse, String email, String telephone, Bien bienLoue) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.bienLoue = bienLoue;
    }
    
    public Locataire(int id) {
    	this.id = id;
    	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Bien getBienLoue() {
        return bienLoue;
    }

    public void setBienLoue(Bien bienLoue) {
        this.bienLoue = bienLoue;
    }
}
