package com.ghirmai.bookmarker_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghirmai.bookmarker_api.domain.Bookmark;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data

public class BookmarkResponse {
    private List<BookmarkDTO> data;
    private long totalElements;
    private int totalPages;
    private int currentPage;
    @JsonProperty("isFirst ?")
    private boolean isFirst;
    @JsonProperty("isLast ?")
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrev;

    public BookmarkResponse(Page<BookmarkDTO> page){
        this.setData(page.getContent());
        this.setTotalElements(page.getNumberOfElements());
        this.setTotalPages(page.getTotalPages());
        this.setCurrentPage(page.getNumber()+1);
        this.setFirst(page.isFirst());
        this.setLast(page.isLast());
        this.setHasNext(page.hasNext());
        this.setHasPrev(page.hasPrevious());
    }
}
