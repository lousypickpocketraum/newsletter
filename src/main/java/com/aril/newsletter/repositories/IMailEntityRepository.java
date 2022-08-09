package com.aril.newsletter.repositories;

import com.aril.newsletter.entities.MailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMailEntityRepository extends JpaRepository<MailEntity, Long> {
    Optional<MailEntity> findByMailAddress(String mailAddress);

}
