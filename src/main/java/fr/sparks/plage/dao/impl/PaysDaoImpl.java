package fr.sparks.plage.dao.impl;

import fr.sparks.plage.business.Pays;
import fr.sparks.plage.dao.PaysDao;
import fr.sparks.plage.utils.PaysResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Repository // Pour dire à Spring que c'est un composant qui gère les données
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)// Pour que les méthodes soient transactionnelles , il fait un auto commit à la fin de chaque méthode
public class PaysDaoImpl implements PaysDao {
    
    //hibernate pur
    
    @PersistenceContext
    private EntityManager entityManager;
    
    //Session session = entityManager.unwrap(Session.class); // Pour récupérer la session Hibernate
    //SessionFactory sf = session.getSessionFactory();
    
    
    /*@Override
    public Optional<Pays> findByCode(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Pays pays = session.find(Pays.class, id);
        return Optional.ofNullable(pays);
    }*/
    
    @Override
    public Optional<Pays> findByCode(String code) {
        Session session = entityManager.unwrap(Session.class);
        Pays pays = session.createQuery("from Pays where code = :code", Pays.class).setParameter("code", code).getSingleResult();
        return Optional.ofNullable(pays);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public Optional<Pays> findById(String code) {
        Session session = entityManager.unwrap(Session.class);
        List<Pays> pays = session.createQuery("FROM Pays p WHERE p.code=:code").setParameter("code", code).list();
        if (pays.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(pays.get(0));
        }
    }
    
    @Override
    public Pays findByNom(String nom) {
        Session session = entityManager.unwrap(Session.class);
        
        List<Pays> listPays = session.createQuery("from Pays where nom = :nom", Pays.class).setParameter("nom", nom).list();
        return listPays.isEmpty() ? null : listPays.get(0);
    }
    
    
    @Override
    public Pays save(Pays pays) {
        Session session = entityManager.unwrap(Session.class);
        session.save(pays);   //commit    //flush à chaque fois
        return pays;
    }
    
    
    @Override
    public List<Pays> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Pays").getResultList();
    }
    
    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);
        long nbPays = session.createQuery("select count(*) from Pays", Long.class).getSingleResult();
        //session.createStoredProcedureCall()  procedure stockée
        return nbPays;
    }
    
    @Override
    public List<Pays> saveAll(List<Pays> paysList) {
        Session session = entityManager.unwrap(Session.class);
        paysList.forEach(pays -> {
                session.save(pays);
                System.out.println("Nombre de Pays ajoutés " + count()); //flush à chaque fois
        });
        return paysList;
    }
    
    @Override
    public List<PaysResult> findNombreClientsParPays() {
        
        return null;
    }
    
    //@Override
    /*public List<Pays> saveAll(List<Pays> paysList) {
        Session session = entityManager.unwrap(Session.class);
        SessionFactory sf = session.getSessionFactory();
        try{
            Session nouvelleSession = sf.openSession();
            nouvelleSession.beginTransaction();
            paysList.forEach(pays -> {
                nouvelleSession.createNativeQuery("insert into Pays (code, nom) values (?, ?)")
                        .setParameter("code", pays.getCode())
                        .setParameter("nom", pays.getNom())
                        .executeUpdate();
            });
            System.out.println("Nombre de Pays ajoutés avant commit" + count()); //flush à chaque fois
            nouvelleSession.getTransaction().commit();
            System.out.println("Nombre de Pays ajoutés après commit" + count()); //flush à chaque fois
        }catch (Exception e){
            System.out.println("Erreur lors de l'ajout des Pays");
        }
        return paysList;
    }*/
    
    
}
