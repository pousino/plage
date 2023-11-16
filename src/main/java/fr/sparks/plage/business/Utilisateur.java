package fr.sparks.plage.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Data
@SuperBuilder
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_utilisateur") //comme est en en singleTable, on choisit un nom pour la colonne discriminante
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Utilisateur {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utilisateur_generator")
    protected Long id;
    
    protected String nom;
    protected String prenom;
    
    @Column(unique = true)
    protected String email;
    protected String motDePasse;
}
