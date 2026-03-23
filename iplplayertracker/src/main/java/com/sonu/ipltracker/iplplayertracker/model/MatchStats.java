package com.sonu.ipltracker.iplplayertracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "match_stats")
@Getter
@Setter
public class MatchStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Runs cannot be negative")
    private int runs;

    @Min(value = 0, message = "Balls cannot be negative")
    private int balls;

    @Min(value = 0, message = "Wickets cannot be negative")
    private int wickets;

    @NotNull(message = "Player must be provided")
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}


// This index is IMPORTANT for GROUP BY performance