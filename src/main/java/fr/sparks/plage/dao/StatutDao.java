package fr.sparks.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sparks.plage.business.Statut;

public interface StatutDao extends JpaRepository<Statut, Long> {
    
    Statut findByNom(String statut);
}
