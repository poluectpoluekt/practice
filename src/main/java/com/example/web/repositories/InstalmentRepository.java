package com.example.web.repositories;

import com.example.web.models.Instalment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstalmentRepository extends JpaRepository<Instalment, Long> {

    Optional<Instalment> findByOwner(String ownerEmail);

    List<Instalment> findAll();
}
