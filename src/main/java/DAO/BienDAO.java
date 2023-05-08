package DAO;

import java.sql.SQLException;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import models.Bien;

public class BienDAO {
    private EntityManagerFactory emf;

    public BienDAO() {
        emf = Persistence.createEntityManagerFactory("GestionImmobilierPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bien bien) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(bien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bien bien) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.merge(bien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws EntityNotFoundException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bien bien = null;
            try {
                bien = em.getReference(Bien.class, id);
                bien.getId();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("The bien with id " + id + " no longer exists.");
            }
            em.remove(bien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bien> findBienEntities() {
        return findBienEntities(true, -1, -1);
    }

    public List<Bien> findBienEntities(int maxResults, int firstResult) {
        return findBienEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
    private List<Bien> findBienEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select b from Bien b order by b.id desc");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bien findBien(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bien.class, id);
        } finally {
            em.close();
        }
    }

    public int getBienCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(b) from Bien b");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Bien> getAllBiens() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select b from Bien b");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void saveBien(Bien bien) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            if (bien.getId() == 0) {
                em.persist(bien);
            } else {
                em.merge(bien);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Bien getBien(int id) {
        EntityManager em = getEntityManager();
        Bien bien = null;
        try {
        bien = em.find(Bien.class, id);
        } finally {
        em.close();
        }
        return bien;
        }
    public List<Bien> getBiensByVille(String ville) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Bien b WHERE b.ville = :ville");
            q.setParameter("ville", ville);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Bien> getBiensByType(String type) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Bien b WHERE b.type = :type");
            q.setParameter("type", type);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Bien> getBiensByPrix(double prixMin, double prixMax) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Bien b WHERE b.prix >= :prixMin AND b.prix <= :prixMax");
            q.setParameter("prixMin", prixMin);
            q.setParameter("prixMax", prixMax);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Bien> getBiensBySurface(double surfaceMin, double surfaceMax) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Bien b WHERE b.surface >= :surfaceMin AND b.surface <= :surfaceMax");
            q.setParameter("surfaceMin", surfaceMin);
            q.setParameter("surfaceMax", surfaceMax);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static Bien getBienById(int id) throws SQLException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;
        Bien bien = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            bien = entityManager.find(Bien.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
        return bien;
    }

    public List<Bien> getBiensByNbPieces(int nbPiecesMin, int nbPiecesMax) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT b FROM Bien b WHERE b.nbPieces >= :nbPiecesMin AND b.nbPieces <= :nbPiecesMax");
            q.setParameter("nbPiecesMin", nbPiecesMin);
            q.setParameter("nbPiecesMax", nbPiecesMax);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public void updateBien(Bien bien) throws EntityNotFoundException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bien = em.merge(bien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void deleteBien(Bien bien) throws EntityNotFoundException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            bien = em.merge(bien);
            em.remove(bien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}