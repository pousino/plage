package fr.sparks.plage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.sparks.plage.business.File;

public interface FileDao extends JpaRepository<File, Long> {




}
