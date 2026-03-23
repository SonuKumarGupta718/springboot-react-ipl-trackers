package com.sonu.ipltracker.iplplayertracker.service;

import com.sonu.ipltracker.iplplayertracker.exception.PlayerNotFoundException;
import com.sonu.ipltracker.iplplayertracker.model.MatchStats;
import com.sonu.ipltracker.iplplayertracker.model.Player;
import com.sonu.ipltracker.iplplayertracker.repository.MatchStatsRepository;
import com.sonu.ipltracker.iplplayertracker.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sonu.ipltracker.iplplayertracker.repository.MatchStatsRepository;



import java.util.HashMap;
import java.util.Map;

@Service
public class PlayerService {



    private final PlayerRepository playerRepository;
    private final MatchStatsRepository matchStatsRepository;

    public PlayerService(PlayerRepository playerRepository,
                         MatchStatsRepository matchStatsRepository) {
        this.playerRepository = playerRepository;
        this.matchStatsRepository = matchStatsRepository;
    }

    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
    public Player getPlayerById(Long id){
        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public Player save(Player p){
        return playerRepository.save(p);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }
    public Player updatePlayer(Long id, Player updatedPlayer) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));

        player.setName(updatedPlayer.getName());
        player.setTeam(updatedPlayer.getTeam());
        player.setRole(updatedPlayer.getRole());

        return playerRepository.save(player);
    }
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
        playerRepository.delete(player);
    }

    public MatchStats addMatchStats(Long playerId, MatchStats stats) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        stats.setPlayer(player);

        return matchStatsRepository.save(stats);
    }



    public Map<String, Object> getPlayerStats(Long playerId) {

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));

        Object[] result = matchStatsRepository.getAggregatedStats(player.getId()).get(0);


        long matches = (long) result[0];
        long runs = result[1] == null ? 0 : (long) result[1];
        long wickets = result[2] == null ? 0 : (long) result[2];
        long balls = result[3] == null ? 0 : (long) result[3];

        double average = matches == 0 ? 0 : (double) runs / matches;
        double strikeRate = balls == 0 ? 0 : (runs * 100.0) / balls;

        Map<String, Object> stats = new HashMap<>();
        stats.put("player", player.getName());
        stats.put("matches", matches);
        stats.put("runs", runs);
        stats.put("wickets", wickets);
        stats.put("average", average);
        stats.put("strikeRate", strikeRate);

        return stats;
    }
}
