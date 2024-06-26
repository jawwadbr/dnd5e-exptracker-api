package com.jawbr.dnd5e.exptracker.repository;

import com.jawbr.dnd5e.exptracker.entity.Campaign;
import com.jawbr.dnd5e.exptracker.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("SELECT c FROM Campaign c JOIN c.players p WHERE c.uuid = :campaignUuid AND p.id = :userId")
    Campaign findJoinedCampaignByUuidAndUserId(UUID campaignUuid, Long userId);

    @Query("SELECT c FROM Campaign c JOIN c.creator p WHERE c.uuid = :campaignUuid AND p.id = :userId")
    Campaign findCreatedCampaignByUuidAndUserId(UUID campaignUuid, Long userId);

    @Query("SELECT c FROM Campaign c JOIN c.players p WHERE p.id = :userId")
    Page<Campaign> findJoinedCampaignsByUserId(Long userId, Pageable pageable);

    @Query("SELECT c FROM Campaign c JOIN c.creator cr WHERE cr.id = :userId")
    Page<Campaign> findCreatedCampaignsByUserId(Long userId, Pageable pageable);

    @Query("SELECT p FROM Campaign c JOIN c.players p, User u WHERE c.uuid = :campaignUuid AND u.id = :userId AND u MEMBER OF c.players")
    List<User> findAllJoinedPlayersOnCampaign(UUID campaignUuid, Long userId);

    Campaign findByUuid(UUID campaignUuid);

}
