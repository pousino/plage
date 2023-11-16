package fr.sparks.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sparks.plage.business.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long> {

}
