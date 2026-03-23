package com.sonu.ipltracker.iplplayertracker.repository;

import com.sonu.ipltracker.iplplayertracker.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
