package com.sonu.ipltracker.iplplayertracker.controller;
import com.sonu.ipltracker.iplplayertracker.dto.PlayerPageResponse;


import com.sonu.ipltracker.iplplayertracker.model.MatchStats;
import com.sonu.ipltracker.iplplayertracker.model.Player;
import com.sonu.ipltracker.iplplayertracker.service.PlayerService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;


import java.util.Map;                                                                                                                         //PlayerController handles all REST API requests from frontend. It manages CRUD operations, pagination, image upload, and stats aggregation. Each API maps to a specific HTTP method and delegates business logic to the service layer.
@CrossOrigin(origins = "http://localhost:3000")


@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerService service;

    // we r creating

    public PlayerController(PlayerService service) {
        this.service = service;
    }
    @PostMapping("/{id}/upload")
    public Player uploadImage(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws Exception {

        String folder = "uploads/";
        java.io.File dir = new java.io.File(folder);
        if (!dir.exists()) dir.mkdirs();

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        java.nio.file.Path path = java.nio.file.Paths.get(folder + filename);

        java.nio.file.Files.write(path, file.getBytes());

        Player player = service.getPlayerById(id);
        player.setImageUrl(filename);
        return service.save(player);
    }

    @GetMapping
    public PlayerPageResponse getPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Player> playerPage = service.getAllPlayers(pageable);

        return new PlayerPageResponse(
                playerPage.getContent(),
                playerPage.getNumber(),
                playerPage.getTotalElements(),
                playerPage.getTotalPages()
        );
    }



    // Creating Players

    @PostMapping
    public Player createPlayer(@Valid @RequestBody Player player) {
        return service.addPlayer(player);
    }

    @PostMapping("/{playerId}/stats")
    public MatchStats addMatchStats(
            @PathVariable Long playerId,
            @RequestBody MatchStats stats) {

        return service.addMatchStats(playerId, stats);
    }



    // GET AGGREGATED STATS
    @GetMapping("/{id}/stats")
    public Map<String, Object> getStats(@PathVariable Long id) {
        return service.getPlayerStats(id);
    }
    @PutMapping("/{id}")
    public Player updatePlayer(
            @PathVariable Long id,
            @RequestBody Player player) {
        return service.updatePlayer(id, player);
    }
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        service.deletePlayer(id);
    }



}
