package com.sonu.ipltracker.iplplayertracker.dto;

import java.util.List;

public class PlayerPageResponse {

    private List<?> players;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public PlayerPageResponse(List<?> players, int currentPage,
                              long totalItems, int totalPages) {
        this.players = players;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<?> getPlayers() {
        return players;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
