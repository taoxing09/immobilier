package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import models.*;

public class VisiteDAO {
  
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public VisiteDAO() {
        emf = Persistence.createEntityManagerFactory("nom_de_votre_persistence_unit");
        em = emf.createEntityManager();
    }
    
    public void create(Visite visite) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.persist(visite);
        transac.commit();
    }
    
    public void update(Visite visite) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.merge(visite);
        transac.commit();
    }
    
    public void delete(Visite visite) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        em.remove(em.contains(visite) ? visite : em.merge(visite));
        transac.commit();
    }
    
    public Visite getVisiteById(int id) {
        TypedQuery<Visite> query = em.createQuery("SELECT v FROM visite v WHERE v.id = :id", Visite.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
    
    public List<Visite> getAllVisites() {
        TypedQuery<Visite> query = em.createQuery("SELECT v FROM visite v", Visite.class);
        return query.getResultList();
    }
    
    public List<Visite> getVisitesByBien(Bien bien) {
        TypedQuery<Visite> query = em.createQuery("SELECT v FROM visite v WHERE v.bien = :bien", Visite.class);
        query.setParameter("bien", bien);
        return query.getResultList();
    }
    
    public List<Visite> getVisitesByLocataire(Locataire locataire) {
        TypedQuery<Visite> query = em.createQuery("SELECT v FROM visite v WHERE v.locataire = :locataire", Visite.class);
        query.setParameter("Locataire", locataire);
        return query.getResultList();
    }
    
    public void close() {
        em.close();
        emf.close();
    }

    public static void saveVisite(Visite visite) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        String insertSql = "INSERT INTO visites (id_bien, id_locataire, date_visite) VALUES (?, ?, ?)";

        try {
            preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setInt(1, visite.getIdBien());
            preparedStatement.setInt(2, visite.getIdLocataire());
            preparedStatement.setDate(3, new java.sql.Date(visite.getDateVisite().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void updateVisite(Visite visite) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        Visite existingVisite = em.find(Visite.class, visite.getId());
        existingVisite.setLocataire(visite.getLocataire());
        existingVisite.setBien(visite.getBien());
        existingVisite.setDateVisite(visite.getDateVisite());
        existingVisite.setHeureVisite(visite.getHeureVisite());
        existingVisite.setCommentaire(visite.getCommentaire());
        transac.commit();
    }

    public void deleteVisite(Visite visite) {
        EntityTransaction transac = em.getTransaction();
        transac.begin();
        Visite existingVisite = em.find(Visite.class, visite.getId());
        em.remove(existingVisite);
        transac.commit();
    }

}
