package fr.sparks.plage.business;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "Client")
public class Client extends Utilisateur {

    private LocalDateTime dateHeureInscription;
    
    @ManyToOne
    @JoinColumn(name = "lePays")
    private Pays pays;
    
    @ManyToOne
    @JoinColumn(name = "leLienDeParente")
    private LienDeParente lienDeParente;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;
    
    @ManyToOne
    @JoinColumn(name = "ville_de_naissance_id")
    private Ville villeDeNaissance;
    
    @ManyToOne
    @JoinColumn(name = "ville_de_residence_id")
    private Ville villeDeResidence;
    
    
    
}
