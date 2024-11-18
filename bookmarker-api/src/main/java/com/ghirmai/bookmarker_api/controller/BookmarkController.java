package com.ghirmai.bookmarker_api.controller;

import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.dto.BookmarkResponse;
import com.ghirmai.bookmarker_api.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService service;

    @GetMapping
    public ResponseEntity<BookmarkResponse> getbookmarks(@RequestParam(name = "page",defaultValue = "1",
    required = false)Integer page){
       return new ResponseEntity<>(service.getBookmarks(page),HttpStatus.OK);
    }
}
