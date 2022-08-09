package com.aril.newsletter.repositories;

import com.aril.newsletter.entities.MailGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMailGroupRepository extends JpaRepository<MailGroup, Long> {
}
