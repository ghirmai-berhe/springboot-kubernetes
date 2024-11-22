package com.ghirmai.bookmarker_api.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name ="bookmarks")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bookmark {
    @Id
    @SequenceGenerator(name = "bm_id_seq_gen",sequenceName = "bm_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "bm_id_seq_gen")
    private Long id;
    @NotNull
     @Column(/*nullable = false*/)
    private String title;
    @NotNull
     @Column(/*nullable = false */)
    private String url;
    private Instant createdAt;
}
