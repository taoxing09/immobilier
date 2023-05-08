package DAO;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import models.Bien;
import models.Locataire;

public class LocataireDAO {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("srvImmobiler");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    
    public void addLocataire(Locataire locataire) {
        tx.begin();
        em.persist(locataire);
        tx.commit();
    }
    
    public void updateLocataire(Locataire locataire) {
        tx.begin();
        em.merge(locataire);
        tx.commit();
    }
    
    public void deleteLocataire(int locataireId) {
        tx.begin();
        Locataire locataire = em.find(Locataire.class, locataireId);
        em.remove(locataire);
        tx.commit();
    }
    
    public Locataire getLocataire(int locataireId) {
        return em.find(Locataire.class, locataireId);
    }
    
    public Locataire getLocataireById(int locataireId) {
        tx.begin();
        Locataire locataire = em.find(Locataire.class, locataireId);
        tx.commit();
        return locataire;
    }

    public Bien getBienById(int bienId) {
        tx.begin();
        Bien bien = em.find(Bien.class, bienId);
        tx.commit();
        return bien;
    }

    
    @SuppressWarnings("unchecked")
	public List<Locataire> getAllLocataires() {
        Query query = em.createQuery("SELECT l FROM Locataire l");
        return query.getResultList();
    }
    
}
