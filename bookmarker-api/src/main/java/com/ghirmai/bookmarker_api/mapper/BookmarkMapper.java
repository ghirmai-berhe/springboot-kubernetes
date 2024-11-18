package com.ghirmai.bookmarker_api.mapper;

import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.dto.BookmarkDTO;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

    public  BookmarkDTO toDTO(Bookmark bookmark){
        return BookmarkDTO.builder()
                .id(bookmark.getId())
                .title(bookmark.getTitle())
                .url(bookmark.getUrl())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}
