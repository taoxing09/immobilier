package models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bien")
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "designation", nullable = false)
    private String designation;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "superficie", nullable = false)
    private int superficie;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @OneToMany(mappedBy = "bien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Locataire> locataires = new ArrayList<>();

    @OneToOne(mappedBy = "bien", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Loyer loyer;

    public Bien() {}

    public Bien(int id) {
        this.id = id;
    }

    public Bien(int id, String designation, String type, String adresse, int superficie) {
        this.id = id;
        this.designation = designation;
        this.type = type;
        this.adresse = adresse;
        this.superficie = superficie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public List<Locataire> getLocataires() {
        return locataires;
    }

    public void addLocataire(Locataire locataire) {
        locataires.add(locataire);
        locataire.setBienLoue(this);
    }

    public void removeLocataire(Locataire locataire) {
        locataires.remove(locataire);
        locataire.setBienLoue(null);
    }

    public Loyer getLoyer() {
        return loyer;
    }

    public void setLoyer(Loyer loyer) {
        this.loyer = loyer;
    }
}
