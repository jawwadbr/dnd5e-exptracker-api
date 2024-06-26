package com.jawbr.dnd5e.exptracker.repository;

import com.jawbr.dnd5e.exptracker.entity.InviteCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface InviteCodeRepository extends JpaRepository<InviteCode, Long> {

    List<InviteCode> findByExpiryDateBefore(ZonedDateTime time);

    boolean existsByCode(String code);

    InviteCode findByCode(String code);
}
