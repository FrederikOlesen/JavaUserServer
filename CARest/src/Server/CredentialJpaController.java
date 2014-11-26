/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import Server.exceptions.NonexistentEntityException;
import Server.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Credential;

/**
 *
 * @author Mads
 */
public class CredentialJpaController implements Serializable {

    public CredentialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Credential credential) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(credential);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCredential(credential.getUsername()) != null) {
                throw new PreexistingEntityException("Credential " + credential + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Credential credential) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            credential = em.merge(credential);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = credential.getUsername();
                if (findCredential(id) == null) {
                    throw new NonexistentEntityException("The credential with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credential credential;
            try {
                credential = em.getReference(Credential.class, id);
                credential.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The credential with id " + id + " no longer exists.", enfe);
            }
            em.remove(credential);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Credential> findCredentialEntities() {
        return findCredentialEntities(true, -1, -1);
    }

    public List<Credential> findCredentialEntities(int maxResults, int firstResult) {
        return findCredentialEntities(false, maxResults, firstResult);
    }

    private List<Credential> findCredentialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Credential.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Credential findCredential(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Credential.class, id);
        } finally {
            em.close();
        }
    }

    public int getCredentialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Credential> rt = cq.from(Credential.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
