package fr.sparks.plage.business;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Region {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "pays_code")
    private Pays pays;
    
    @OneToMany(mappedBy = "region")
    private List<Ville> villes;
    
    
}
