package com.ghirmai.bookmarker_api.dto;

import java.time.Instant;

public interface BookmarkerVM {
    Long getId();
    String getTitle();
    String getUrl();
    Instant getCreatedAt();

}
