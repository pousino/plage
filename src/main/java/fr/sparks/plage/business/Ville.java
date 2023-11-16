package fr.sparks.plage.business;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Ville {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nom;
    
    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    
    @OneToMany(mappedBy = "villeDeNaissance")
    private List<Client> clientsNatif;
    
    @OneToMany(mappedBy = "villeDeResidence")
    private List<Client> clientsResidents;
    
    
}
