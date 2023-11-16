package fr.sparks.plage.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Parasol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parasol_generator")  //generator = "parasol_generator" pour qu'il ait sa propre sequence
    @SequenceGenerator(name = "parasol_generator")
    private Long id;
    
    @NonNull
    private byte numEmplacement;
    
    @ManyToOne
    @NonNull
    private File file;
    
    @ManyToMany
    private List<Reservation> reservations;
    
}
