package fr.sparks.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sparks.plage.business.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ClientDao extends JpaRepository<Client, Long> {

    @Query("from Client c where c.prenom = 'Alexis'")
    List<Client> findClientNamedAlexis();
    
    @Query("from Client c where c.prenom = ?1")
    List<Client> findClientByParameterName(String prenom);
    
    @Query("from Client c where c.prenom = :prenom")
    List<Client> findClientByParameterFirstNameNamed(@Param("prenom") String prenom);
    
    @Query("from Client c where c.nom = :nomParameter")
    List<Client> findClientByParameterNameNamed(@Param("nomParameter") String nom);
    
    
    @Query("from Client c where c.pays.code = 'ES'")
    List<Client> findSpanishClients();
    
    @Query("from Client c where c.pays.code = ?1")
    List<Client> findClientsByPaysCode(String code);
    
    @Query("from Client c where c.pays.code = :paysCode")
    List<Client> findClientsByPaysCodeNamed(@Param("paysCode") String paysCode);
    
    @Query("from Client c where c.dateHeureInscription >= :date")
    List<Client> findClientsInscritsApres(@Param("date") LocalDateTime date);
    
    @Query("from Client c where c.dateHeureInscription >= '2023-01-01'")
    List<Client> findClientsInscritsApres2023();
    
    @Query("from Client c where c.pays.code = 'PS' and lower(c.nom) like 'a%'")
    List<Client> findClientsPortugaisDontNomNaissanceCommenceParA();
    
    @Query("from Client c where c.pays.code = :paysCode and lower(c.nom) like :premierelettreNom")
    List<Client> findClientsByPaysCodeAndNomStartsWith(@Param("paysCode") String paysCode, @Param("premierelettreNom") String premierelettreNom);

    @Query("from Client c where c.dateHeureInscription between :dateDebut and :dateFin")
    List<Client> findClientsInscritsEntre(@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);
    
    //écrire la méthode qui liste les clients qui se sont inscrits aujourd'hui
    @Query("FROM Client WHERE day_of_month(dateHeureInscription)=day_of_month(current_date()) " +
            "AND month(dateHeureInscription)=month(current_date()) " +
            "AND year(dateHeureInscription)=year(current_date())")
    List<Client> findClientsWhoRegisteredToday();
    
    //écrire la méthode qui liste les clients qui se sont inscrits aujourd'hui
    @Query("from Client c where c.dateHeureInscription = current_date()")
    List<Client> findClientsInscritsAujourdhui();
    
    //
    @Query("select count(c) from Client c WHERE day_of_month(c.dateHeureInscription)=day_of_month(current_date()) " +
            "AND month(c.dateHeureInscription)=month(current_date()) " +
            "AND year(c.dateHeureInscription)=year(current_date())")
    Long findNombreClientsInscritsAujourdhui();
    
    //écrire la méthode qui liste les clients qui se sont inscrits cette semaine
    @Query("from Client c where c.dateHeureInscription between :dateDebut and :dateFin")
    Long findNombreClientsInscritsCetteSemaine(@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);
    
    //écrire la méthode qui liste les clients qui se sont inscrits cette semaine
    @Query(value = "Select count(*) from CLIENT c where c.dateHeureInscription " +
            "between DATEADD(week, DATEDIFF(week, 0, GETDATE()), 0) " +
            " and DATEADD(DAY, -1, DATEADD(week, DATEDIFF(week, 0, GETDATE()) + 1, 0))", nativeQuery = true)
    Long findNombreClientsInscritsCetteSemaine2();
    
    //DTYPE = discriminator type
    @Query(value = "SELECT * FROM Utilisateur WHERE type_utilisateur  = 'Client' and EXTRACT(WEEK FROM CURRENT_DATE()) = EXTRACT(WEEK FROM Utilisateur.date_heure_inscription)", nativeQuery = true)
    List<Client> findNombreClientsInscritsCetteSemaine3();
    
    
    //@Query("SELECT c FROM Client c WHERE c.id IN (SELECT r.client.id FROM Reservation r WHERE a.city = :city)")
    //List<Client> findPeopleByCity(@Param("city") String city);
}
