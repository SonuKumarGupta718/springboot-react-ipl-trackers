package com.sonu.ipltracker.iplplayertracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player {
    @Column(nullable = true)
    private String imageUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Player name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Team name cannot be empty")
    private String team;

    @NotBlank(message = "Role cannot be empty")
    private String role;
}
