package fr.sparks.plage.automate;

import com.github.javafaker.Faker;
import fr.sparks.plage.business.Client;
import fr.sparks.plage.business.LienDeParente;
import fr.sparks.plage.business.Parasol;
import fr.sparks.plage.business.Pays;
import fr.sparks.plage.business.Reservation;
import fr.sparks.plage.business.Statut;
import fr.sparks.plage.dao.ClientDao;
import fr.sparks.plage.dao.FileDao;
import fr.sparks.plage.dao.LienDeParenteDao;
import fr.sparks.plage.dao.ParasolDao;
import fr.sparks.plage.dao.PaysDao;
import fr.sparks.plage.dao.ReservationDao;
import fr.sparks.plage.dao.StatutDao;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
@AllArgsConstructor //équivalent de //autowired partout
public class ReservationAutomate {
    
    private FileDao fileDao;
    private ParasolDao parasolDao;
    private PaysDao paysDao;
    private StatutDao statutDao;
    private ReservationDao reservationDao;
    private LienDeParenteDao lienDeParenteDao;
    private ClientDao clientDao;
    
    //ajouter 1 réservation en choisissant au hasard un client, un parasol disponible, une date au hasard (date debut = date fin) et le statut "en attente"
    //@Scheduled(cron="*/10 * * * * *") //pour lancer des taches auto ttes les sec par Spring
    /*private void ajouterReservation(){
        List<Client> clients = clientDao.findAll();
        List<Parasol> parasols = parasolDao.findAll();
        Random random = new Random();
        Statut statutEnAttente = statutDao.findByNom("en attente de traitement");
        LocalDate dateDebut = LocalDate.of(2020, random.nextInt(12)+1, random.nextInt(28) + 1);
        LocalDate dateFin = dateDebut;
        if(!clients.isEmpty()) {
            Reservation reservation = Reservation.builder().client(clients.get(random.nextInt(clients.size())))
                    .parasols(Arrays.asList(parasols.get(random.nextInt(parasols.size()))))
                    .dateDebut(dateDebut).dateFin(dateFin)
                    .statut(statutEnAttente).build();
            reservationDao.save(reservation);
        }
        
    }*/
    
    @Scheduled(cron="*/2 * * * * *")
    private void ajouterReservationAvecNouveauCLient(){
        Faker faker = new Faker(Locale.FRENCH);
        Random random = new Random();
        
        List<Pays> pays = paysDao.findAll();
        List<Client> clients = clientDao.findAll();
        List<Parasol> parasols = parasolDao.findAll();
        Statut statutEnAttente = statutDao.findByNom("en attente de traitement");
        LocalDate dateDebut = LocalDate.of(2020, random.nextInt(12)+1, random.nextInt(28) + 1);
        LocalDate dateFin = dateDebut;
        LienDeParente lienDeParenteAucun = lienDeParenteDao.findByNom("Aucun");
        
        Client client = Client.builder().nom(faker.name().lastName()).prenom(faker.name().firstName())
                .pays(pays.get(random.nextInt(pays.size()))).email(faker.internet().emailAddress())
                .lienDeParente(lienDeParenteAucun)
                .motDePasse(String.valueOf(random.nextInt(99999999) + 10000000))
                .build();
        
        //clientDao.save(client);
        
            Reservation reservation = Reservation.builder().client(client)
                    .parasols(Arrays.asList(parasols.get(random.nextInt(parasols.size()))))
                    .dateDebut(dateDebut).dateFin(dateFin)
                    .statut(statutEnAttente).build();
            reservationDao.save(reservation);
        
    }
    
    
   
    
}
