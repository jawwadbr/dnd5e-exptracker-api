package com.jawbr.dnd5e.exptracker.controller;

import com.jawbr.dnd5e.exptracker.dto.response.RaceDTO;
import com.jawbr.dnd5e.exptracker.service.RaceService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/races")
public class RaceController {

    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public Page<RaceDTO> findAllRaces(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String sortBy
    )
    {
        return raceService.findAllRaces(page, pageSize, sortBy);
    }

    @GetMapping("/{raceUuid}")
    public RaceDTO findByUuid(@PathVariable UUID raceUuid) {
        return raceService.findByUuid(raceUuid);
    }
}
