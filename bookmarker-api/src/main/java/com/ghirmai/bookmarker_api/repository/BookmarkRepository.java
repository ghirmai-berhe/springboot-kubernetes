package com.ghirmai.bookmarker_api.repository;

import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.dto.BookmarkDTO;
import com.ghirmai.bookmarker_api.dto.BookmarkRequestDTO;
import com.ghirmai.bookmarker_api.dto.BookmarkerVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {
    @Query("select  new com.ghirmai.bookmarker_api.dto.BookmarkDTO(b.id,b.title,b.url,b.createdAt) from Bookmark  b ")
//    Page<BookmarkDTO> findBookmarks(Pageable pageable);
    Page<BookmarkerVM> findBookmarks(Pageable pageable);

    @Query("""
    select  new com.ghirmai.bookmarker_api.dto.BookmarkDTO(b.id,b.title,b.url,b.createdAt) from Bookmark  b 
     where lower(b.title) like lower(concat('%',:query,'%') ) 
    """)
//    Page<BookmarkDTO> searchBookmarks(Pageable pageable, String query);
    Page<BookmarkerVM> searchBookmarks(Pageable pageable, String query);

//    Page<BookmarkDTO> findBookmarkByTitleContainingIgnoreCase(Pageable pageable, String query);

    Page<BookmarkerVM> findBookmarkByTitleContainingIgnoreCase(Pageable pageable, String query);


}
