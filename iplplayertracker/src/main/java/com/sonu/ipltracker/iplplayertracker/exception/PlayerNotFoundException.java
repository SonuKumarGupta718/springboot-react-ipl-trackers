package com.sonu.ipltracker.iplplayertracker.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(Long id) {
        super("Player with id " + id + " does not exist");
    }
}
