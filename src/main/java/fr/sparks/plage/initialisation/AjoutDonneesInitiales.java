package fr.sparks.plage.initialisation;

import fr.sparks.plage.business.Reservation;
import fr.sparks.plage.dao.ReservationDao;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import fr.sparks.plage.business.Client;
import fr.sparks.plage.business.File;
import fr.sparks.plage.business.LienDeParente;
import fr.sparks.plage.business.Parasol;
import fr.sparks.plage.business.Pays;
import fr.sparks.plage.business.Statut;
import fr.sparks.plage.dao.ClientDao;
import fr.sparks.plage.dao.FileDao;
import fr.sparks.plage.dao.LienDeParenteDao;
import fr.sparks.plage.dao.ParasolDao;
import fr.sparks.plage.dao.PaysDao;
import fr.sparks.plage.dao.StatutDao;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor //équivalent de //autowired partout
public class AjoutDonneesInitiales implements CommandLineRunner { //CommandLineRunner permet d'exécuter du code au démarrage de l'application
    
    private FileDao fileDao;
    private ParasolDao parasolDao;
    private PaysDao paysDao;
    private StatutDao statutDao;
    private ReservationDao reservationDao;
    
    private LienDeParenteDao lienDeParenteDao;
    
    private ClientDao clientDao;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ajout données initiales");
        ajouterFiles();
        ajouterParasols();
        ajouterPays();
        ajouterStatuts();
        ajouterLiensDeParente();
        ajouterClients(10);
        
        //ALEXIS
        //clientDao.findClientNamedAlexis().forEach(client -> System.out.println(client));
        //clientDao.findClientByParameterName("Alexis").forEach(client -> System.out.println(client));
        //clientDao.findClientByParameterNameNamed("Alexis").forEach(client -> System.out.println(client));
        
        clientDao.findClientByParameterNameNamed("Alexis").forEach(c -> System.out.println(c));
        
        //ESPAGNE
        //clientDao.findSpanishClients().forEach(client -> System.out.println(client));
        //clientDao.findClientsByPaysCode("ES").forEach(client -> System.out.println(client));
        //clientDao.findClientsByPaysCodeNamed("ES").forEach(client -> System.out.println(client));
        
        //DATE INSCRIPTION
        clientDao.findClientsInscritsApres(LocalDateTime.of(2023, 6, 1, 0, 0))
                .forEach(client -> System.out.println(client));
        
        //Clients portugais dont le nom de naissance commence par A
        //clientDao.findClientsPortugaisDontNomNaissanceCommenceParA()
                //.forEach(client -> System.out.println(client));
        /*clientDao.findClientsByPaysCodeAndNomStartsWith("PT", "A")
                .forEach(client -> System.out.println(client));*/
        
        /*clientDao.findClientsInscritsEntre(LocalDateTime.of(2023, 6, 1, 0, 0), LocalDateTime.of(2023, 6, 30, 0, 0))
                .forEach(client -> System.out.println(client));*/
        
        //clientDao.findClientsInscritsAujourdhui().forEach(client -> System.out.println(client));
        
        //clientDao.findNombreClientsInscritsAujourdhui();
        
        //paysDao.findNombreClientsParPays().forEach(paysResult -> System.out.println(paysResult.getNomPays() + " " +  paysResult.getNombreClients()));
        
        //paysDao.findNombreClientsParPaysByInterface().forEach(result -> System.out.println(result.getPaysNom() + " " +  result.getNombreClients()));
  
        //paysDao.findNombreClientsParPaysByTab().forEach(objects -> System.out.println(objects[0] + " " + objects[1]));
        
        //paysDao.findPaysSansClients().forEach(pays -> System.out.println(pays));
        
        //paysDao.findNombreClientsParMoisEtParAnnee().forEach(clientCountByMonth -> System.out.println(clientCountByMonth.getMois() + "/" + clientCountByMonth.getAnnee() + " : " + clientCountByMonth.getNombre()));
        
        System.out.println("prix moyen " + parasolDao.findPrixMoyenParasol());
        System.out.println("nombre de parasols " + parasolDao.findNombreParasols());
        
        LocalDateTime premier = LocalDateTime.now().with(DayOfWeek.MONDAY);
        
        //clientDao.findNombreClientsInscritsCetteSemaine3().forEach(client-> System.out.println(client));
        
        System.out.println("recherche pays par code " + paysDao.findByCode("FR"));
        System.out.println("recherche pays par code " + paysDao.findById("AA").isPresent());
        
        System.out.println("recherche pays par nom " + paysDao.findByNom("Japon"));
        System.out.println("recherche pays par nom " + paysDao.findByNom("France"));
        
        
        //paysDao.save(Pays.builder().code("AR").nom("Argentine").build());
        //paysDao.saveAll(Arrays.asList(Pays.builder().code("BR").nom("Brésil").build(), Pays.builder().code("CA").nom("Canada").build()));
        System.out.println("nombre de pays " + paysDao.count());
        
        System.out.println("Afficher tous les pays " + paysDao.findAll());
        
        //paysDao.delete(paysDao.findById("IT").get());
    }
    
    
    
    private void ajouterClients(int nbClientsAAjouter) {
        if (clientDao.count() == 0) {
            
            List<Pays> pays = paysDao.findAll();
            LienDeParente lienDeParenteAucun = lienDeParenteDao.findByNom("Aucun");
            Map<String, Client> map = new HashMap<>();
            Calendar calendar = Calendar.getInstance();
            Random random = new Random();
            Faker faker = new Faker(Locale.FRENCH);
            
            while (map.size() != nbClientsAAjouter) {
                Client client = Client.builder().nom(faker.name().lastName()).prenom(faker.name().firstName())
                        .pays(pays.get(random.nextInt(pays.size()))).email(faker.internet().emailAddress())
                        .lienDeParente(lienDeParenteAucun)
                        .motDePasse(String.valueOf(random.nextInt(99999999) + 10000000))
                        .build();
                
                calendar.set(2020, 1, 1);
                Date dateDebut = calendar.getTime();
                calendar = Calendar.getInstance();
                Date dateFin = calendar.getTime();
                Date dateAleatoire = faker.date().between(dateDebut, dateFin);
                calendar.setTime(dateAleatoire);
                LocalDateTime dateHeureInscription = dateAleatoire.toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                client.setDateHeureInscription(dateHeureInscription);
                map.put(client.getEmail(), client);
            }
            clientDao.saveAll(map.values());
            System.out.print("nb de clients ajoutés:" + map.size());
            
        }
    }
    
    private void ajouterLiensDeParente() {
        if (lienDeParenteDao.count() == 0) {
            lienDeParenteDao.save(new LienDeParente("Frère/Soeur", 0.5f));
            lienDeParenteDao.save(new LienDeParente("Cousin/Cousine", 0.75f));
            lienDeParenteDao.save(new LienDeParente("Aucun", 1f));
        }
    }
    
    private void ajouterStatuts() {
        if (statutDao.count() == 0) {
            statutDao.saveAll(Arrays.asList(new Statut("en attente de traitement"), new Statut("acceptée"),
                    new Statut("refusée")));
        }
    }
    
    private void ajouterFiles() {
        // On teste si des files sont déjà en base
        if (fileDao.count() == 0) {
            // il n'y a pas encore de files en base, on ajoute 8 files
            double prixJournalier = 20;
            for (byte i = 1; i <= 8; i++) {
                fileDao.save(new File(i, prixJournalier));
                prixJournalier -= 2;
            }
        }
    }
    
    private void ajouterParasols() {
        if (parasolDao.count() == 0) {
            List<File> files = fileDao.findAll();
            for (File file : files) {
                for (byte i = 0; i <= 7; i++) {
                    parasolDao.save(new Parasol(i, file));
                }
            }
        }
    }
    
    private void ajouterPays() {
        if (paysDao.count() == 0) {
            paysDao.saveAll(Arrays.asList(new Pays("FR", "France"), new Pays("IT", "Italie"),
                    new Pays("GB", "Royaume-Uni"), new Pays("PT", "Portugal"),
                    new Pays("CH", "Suisse"), new Pays("IE", "Irlande"),
                    new Pays("DE", "Allemagne"), new Pays("ES", "Espagne")));
        }
    }
    
}
