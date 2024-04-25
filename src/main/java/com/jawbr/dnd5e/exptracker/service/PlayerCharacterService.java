package com.jawbr.dnd5e.exptracker.service;

import com.jawbr.dnd5e.exptracker.dto.mapper.PlayerCharacterDTOMapper;
import com.jawbr.dnd5e.exptracker.dto.response.PlayerCharacterDTO;
import com.jawbr.dnd5e.exptracker.entity.PlayerCharacter;
import com.jawbr.dnd5e.exptracker.exception.PlayerCharacterNotFoundException;
import com.jawbr.dnd5e.exptracker.repository.PlayerCharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerCharacterService {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final PlayerCharacterDTOMapper playerCharacterDTOMapper;
    private final CurrentAuthUserService currentAuthUser;

    public PlayerCharacterService(PlayerCharacterRepository characterRepository,
                                  PlayerCharacterDTOMapper playerCharacterDTOMapper,
                                  CurrentAuthUserService currentAuthUser)
    {
        this.playerCharacterRepository = characterRepository;
        this.playerCharacterDTOMapper = playerCharacterDTOMapper;
        this.currentAuthUser = currentAuthUser;
    }

    // User check all his player characters on a campaign that he is part
    public List<PlayerCharacterDTO> findAllUserPlayerCharacterOnSingleCampaign(UUID campaignUuid) {
        List<PlayerCharacter> playerCharacters = Optional.ofNullable(
                        playerCharacterRepository.findAllUserPlayerCharacterOnSingleCampaign(
                                campaignUuid,
                                currentAuthUser.getCurrentAuthUser().getId()))
                .filter(list -> !list.isEmpty())
                .orElseThrow(() -> new PlayerCharacterNotFoundException("No player character found."));

        return playerCharacters.stream().map(playerCharacterDTOMapper).toList();
    }

    // User check specific player character using UUID
    public PlayerCharacterDTO findUserPlayerCharacterByUuid(UUID characterUuid) {
        PlayerCharacter playerCharacter = Optional.ofNullable(
                playerCharacterRepository.findUserPlayerCharacterByUuid(characterUuid,
                        currentAuthUser.getCurrentAuthUser().getId()))
                .orElseThrow(() -> new PlayerCharacterNotFoundException("No player character found."));

        return playerCharacterDTOMapper.apply(playerCharacter);
    }
}