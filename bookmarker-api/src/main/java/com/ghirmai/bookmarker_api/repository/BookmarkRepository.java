package com.ghirmai.bookmarker_api.repository;

import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.dto.BookmarkDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    @Query("select  new com.ghirmai.bookmarker_api.dto.BookmarkDTO(b.id,b.title,b.url,b.createdAt) from Bookmark  b ")
    Page<BookmarkDTO> findBookmarks(Pageable pageable);
}
