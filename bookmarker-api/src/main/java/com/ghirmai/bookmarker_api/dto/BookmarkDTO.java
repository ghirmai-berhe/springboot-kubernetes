package com.ghirmai.bookmarker_api.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@Builder
@AllArgsConstructor
//@NoArgsConstructor  , it will fail the JPA repository method in Repository interface
public class BookmarkDTO {

    private Long id;

    private String title;

    private String url;
    private Instant createdAt;
}
