package com.ghirmai.bookmarker_api.controller;

import ch.qos.logback.core.util.StringUtil;
import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.dto.BookmarkDTO;
import com.ghirmai.bookmarker_api.dto.BookmarkRequestDTO;
import com.ghirmai.bookmarker_api.dto.BookmarkResponse;
import com.ghirmai.bookmarker_api.exception.BadRequestException;
import com.ghirmai.bookmarker_api.service.BookmarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService service;

//    @GetMapping
//    public ResponseEntity<BookmarkResponse> getbookmarks(@RequestParam(name = "page",defaultValue = "1",
//    required = false)Integer page){
//       return new ResponseEntity<>(service.getBookmarks(page),HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<BookmarkResponse> getbookmarks(@RequestParam(name = "page",defaultValue = "1", required = false)Integer page,
                                                         @RequestParam(name="query",defaultValue = "",required = false) String query){

        if(StringUtil.isNullOrEmpty(query)){
            return new ResponseEntity<>(service.getBookmarks(page),HttpStatus.OK);
        }
        return  new ResponseEntity<>(service.searchBookmarks(page,query),HttpStatus.OK);
    }

    @PostMapping
public ResponseEntity<BookmarkDTO> createBookmark(@Valid  @RequestBody BookmarkRequestDTO dto /*, BindingResult result */){

//           if(result.hasErrors()){
//             String error = result.getAllErrors().stream().map(ObjectError::toString).reduce("",(a,b)->a+b);
//             throw  new BadRequestException(error);
//           }
           return new ResponseEntity<>(service.createBookmark(dto),HttpStatus.CREATED);
}


}
