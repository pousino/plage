package fr.sparks.plage.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Reservation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NonNull
    @Temporal(TemporalType.DATE)
    private LocalDate dateDebut;
    @NonNull
    private LocalDate dateFin;
    @NonNull
    private double montantAReglerEnEuros;
    @Lob //pour les champs de type text long
    private String remarques;
    private String  numeroCarte;
    
    /*@Min(1)
    @Max(12)
    @NonNull*/
    private byte moisExpiration;
    private byte anneeExpiration;
    private String cryptogramme;
    
    @ManyToOne
    private Concessionnaire concessionnaire;
    
    @Version
    private int version;
    
    @ManyToOne
    private Statut statut;
    
    @NonNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Client client;
    
    //mappedBy on le met sur la classe satellite (ici parasol) et on met le nom de l'attribut de la classe principale (ici reservations)
    
    @NonNull
    @ManyToMany(mappedBy = "reservations") //en cas de manyTOMany une seule table de lien ; il faut un seul mapped by
    @Size(min = 1)
    private List<Parasol> parasols;
}
