package com.sonu.ipltracker.iplplayertracker.repository;

import com.sonu.ipltracker.iplplayertracker.model.MatchStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchStatsRepository extends JpaRepository<MatchStats, Long> {

    @Query("""
        SELECT 
            COUNT(m.id),
            SUM(m.runs),
            SUM(m.wickets),
            SUM(m.balls)
        FROM MatchStats m
        WHERE m.player.id = :playerId
    """)
    List<Object[]> getAggregatedStats(Long playerId);
}
// Aggregation (GROUP BY concept)