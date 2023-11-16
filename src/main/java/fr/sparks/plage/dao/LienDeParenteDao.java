package fr.sparks.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sparks.plage.business.LienDeParente;

public interface LienDeParenteDao extends JpaRepository<LienDeParente, Long> {

	// Requête par dérivation
	// (Query method)
	// Le nom de la méthode ainsi que ses paramètres vont être interprétés par Spring Data JPA
    LienDeParente findByNom(String nom);

}
