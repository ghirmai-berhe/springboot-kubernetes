package com.ghirmai.bookmarker_api.service;

import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.dto.BookmarkDTO;
import com.ghirmai.bookmarker_api.dto.BookmarkResponse;
import com.ghirmai.bookmarker_api.mapper.BookmarkMapper;
import com.ghirmai.bookmarker_api.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final BookmarkRepository repository;
    private final BookmarkMapper mapper;

@Transactional(readOnly = true)
    public BookmarkResponse getBookmarks(Integer page){
    int pageNo = page<1?0:page-1;
    Pageable pageable = PageRequest.of(pageNo,5, Sort.Direction.DESC,"createdAt");

    // --------> still in the below code we are loading the whole properties of Bookmark entity and take those defined in DTO still not efficient better to use JPQL in repository
//    Page<BookmarkDTO> dtoPage = repository.findAll(pageable).map(mapper::toDTO);

    Page<BookmarkDTO> dtoPage = repository.findBookmarks(pageable);
    return new BookmarkResponse(dtoPage);

    }
}
