package com.ghirmai.bookmarker_api.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkRequestDTO {
    @NotNull(message = "the field \"title\" is required")
   @NotEmpty(message = "title can not be empty")
    private String title;
    @NotNull(message = "the field \"url \" is required")
   @NotEmpty(message = "title can not be empty")
    private String url;
}
