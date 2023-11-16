package fr.sparks.plage.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class PaysResult {
    
    private String nomPays;
    
    private Long nombreClients;
    
    public PaysResult(String nomPays, Long nombreClients) {
        this.nomPays = nomPays;
        this.nombreClients = nombreClients;
    }
}
