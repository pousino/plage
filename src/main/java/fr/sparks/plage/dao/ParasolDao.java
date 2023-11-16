package fr.sparks.plage.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import fr.sparks.plage.business.Parasol;
import org.springframework.data.jpa.repository.Query;


public interface ParasolDao extends JpaRepository<Parasol, Long> {
    
    //exercice 1 : écrire la méthode dans ParasolDao qui détermine le prix moyen d'un parasol à la journée en fonction du nombre de parasols
    @Query("select avg(p.file.prixJournalier) from Parasol p")
    Double findPrixMoyenParasol();
    
    @Query("select count(*) from Parasol p")
    Long findNombreParasols();
    
   
    
}                                                                                                                                               
