package models;

import java.util.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Loyer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "date_paiement", nullable = false)
    private Date datePaiement;
    
    @Column(name = "montant", nullable = false)
    private float montant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locataire_id")
    private Locataire locataire;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien_id")
    private Bien bien;

    public Loyer() {}
    
    public Loyer(int id, int idBien, int idLocataire, Date dateDebut, Date dateFin, float montant) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.montant = montant;
        this.locataire = null;
        this.bien = null;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Locataire getLocataire() {
        return locataire;
    }

    public void setLocataire(Locataire locataire) {
        this.locataire = locataire;
    }
    
    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public int getIdLocataire() {
        if (locataire != null) {
            return locataire.getId();
        }
        return 0;
    }

    public int getIdBien() {
        if (bien != null) {
            return bien.getId();
        }
        return 0;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_debut", nullable = false)
    private Date dateDebut;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date_fin", nullable = false)
    private Date dateFin;

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public Loyer(int id) {
    	this.id = id;
    }
    
    /**
     * Retourne la date de début du mois suivant la date de fin de ce loyer.
     */
    public Date getDateDebutMoisSuivant() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFin);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
        }

        /**

        Renvoie le montant total du loyer pour le mois donné en paramètre.

        Si le loyer s'étend sur plusieurs mois, le montant sera réparti en fonction du nombre de jours dans chaque mois.

        @param mois le mois pour lequel on veut le montant du loyer

        @return le montant total du loyer pour le mois donné
        */
        public float getMontantMensuel(Date mois) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mois);
        int nbJours = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date debutMois = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        Date debutMoisSuivant = calendar.getTime();

        if (debutMois.before(this.dateDebut) && debutMoisSuivant.after(this.dateFin)) {
        // le mois est entièrement dans la période de location
        return this.montant / nbJours * (this.dateFin.getTime() - this.dateDebut.getTime()) / (1000606024);
        } else if (debutMois.before(this.dateDebut) && debutMoisSuivant.before(this.dateFin)) {
        // le mois commence pendant la période de location
        return this.montant / nbJours * (debutMoisSuivant.getTime() - this.dateDebut.getTime()) / (1000606024);
        } else if (debutMois.after(this.dateDebut) && debutMoisSuivant.after(this.dateFin)) {
        // le mois se termine pendant la période de location
        return this.montant / nbJours * (this.dateFin.getTime() - debutMois.getTime()) / (10006060*24);
        } else {
        // le mois est entièrement en dehors de la période de location
        return 0;
        }
        }

        @Override
        public String toString() {
        return "Loyer{" +
        "id=" + id +
        ", datePaiement=" + datePaiement +
        ", montant=" + montant +
        ", locataire=" + locataire +
        ", bien=" + bien +
        ", dateDebut=" + dateDebut +
        ", dateFin=" + dateFin +
        '}';
        }

}