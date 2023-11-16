package fr.sparks.plage.business;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Pays {
    
    @Id
    @NonNull
    private String code;
    
    @NonNull
    private String nom;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "pays",cascade = CascadeType.REMOVE)
    private List<Client> clients;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "pays",cascade = CascadeType.REMOVE)
    private List<Region> regions;
    
}
