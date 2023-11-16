package fr.sparks.plage.dao;

import java.util.List;
import java.util.Optional;

import fr.sparks.plage.utils.PaysResult;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.sparks.plage.business.Pays;
import org.springframework.data.jpa.repository.Query;

public interface PaysDao  {
    
    // TODO : méthode qui liste les pays sur le nombre décroissant de clients
    Optional<Pays> findByCode(String id);
    
    @SuppressWarnings("unchecked")
    Optional<Pays> findById(String code);
    
    Pays findByNom(String nom);
    Pays save(Pays theme);
    List<Pays> findAll();
    long count();
    List<Pays> saveAll(List<Pays> lesPays);
    
    
    //Projection
    //C'est le DAO qui a écrit la méthode qui donne le nom des pays et le  nombre de clients associés.
    @Query("select new fr.sparks.plage.utils.PaysResult(p.nom, count(c)) from Client c join c.pays p group by p.nom")
    List<PaysResult> findNombreClientsParPays();
    
    //@Query("select p.nom, count(c) from Client c join c.pays p group by p.nom")
    //List<ClientCount> findNombreClientsParPaysByInterface();
    
    //@Query("select p.nom, count(c) from Client c join c.pays p group by p.nom order by count(c) desc")
    //List<Object[]> findNombreClientsParPaysByTab();
    
    /*public interface ClientCount {
        String getPaysNom();
        Long getNombreClients();
    }*/
    
    
    //dans PaysDao, écrire une méthode qui liste les pays pour lesquels nous n'avons pas encore de clients
    //@Query("select p from Pays p where p not in (select c.pays from Client c)")
    //List<Pays> findPaysSansClient();
    
    //@Query("from Pays p where p.clients is empty")
    //List<Pays> findPaysSansClients();
    
    //Nombre d'inscrits par mois et par année
    //@Query("select MONTH(dateHeureInscription) as mois, YEAR(dateHeureInscription) as annee,  count(*) as nombre from Client  group by mois, annee")
    //List<ClientCountByMonth> findNombreClientsParMoisEtParAnnee();
    
    /*public interface ClientCountByMonth {
        int getMois();
        int getAnnee();
        int getNombre();
    }*/
    
}
