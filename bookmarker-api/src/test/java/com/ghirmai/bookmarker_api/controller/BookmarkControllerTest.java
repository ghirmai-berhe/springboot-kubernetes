package com.ghirmai.bookmarker_api.controller;


import com.ghirmai.bookmarker_api.domain.Bookmark;
import com.ghirmai.bookmarker_api.repository.BookmarkRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// -----> integration testing <------------------
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  // ----> this is good when to app is used by jenkin or other conflict of pick port wouldn't happen
@AutoConfigureMockMvc

//-----> h2 is used to test the database is used by default no testcontainer like postgres is used since we didn't mention to use test container
//    we need to add the below annotation <--------------


//    ----> configuring test container <---------
    @TestPropertySource(
            properties = {
                    "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"

//                    NB tc refers to test container, docker container fo
            }
    )
class BookmarkControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private BookmarkRepository bookmarkRepository;
    private List<Bookmark> list;

    @BeforeEach
    void setUp(){
        bookmarkRepository.deleteAllInBatch();
        list = new ArrayList<>();
        list.add(new Bookmark(null,"java","https://www.oracle.com/", Instant.now()));
        list.add(new Bookmark(null,"springBlog","https://spring.io/blog", Instant.now()));
        list.add(new Bookmark(null,"Quarkus","https://quarkus.io/", Instant.now()));
        list.add(new Bookmark(null,"Micronaut","https://micronaut.io/", Instant.now()));
        list.add(new Bookmark(null,"JooQ","https://www.ojooq.org/", Instant.now()));
        list.add(new Bookmark(null,"vladMihalcea","https://www.vladMihalcea.com/", Instant.now()));
        list.add(new Bookmark(null,"Thoughts on Java","https://www.throben-janssen.com/", Instant.now()));
        list.add(new Bookmark(null,"Dzone","https://Dzone.com/", Instant.now()));
        list.add(new Bookmark(null,"DevOpsBookmarks","https://www.devOpsBookmarks.com/", Instant.now()));
        list.add(new Bookmark(null,"Kubernetes docs","https://kubernetes.io/docs/home/", Instant.now()));
        list.add(new Bookmark(null,"Expressjs","https://expressjs.com/", Instant.now()));
        list.add(new Bookmark(null,"Marcobehler","https://www.marcobehler.com/", Instant.now()));
        list.add(new Bookmark(null,"baeldung","https://www.baeldung.com/", Instant.now()));
        list.add(new Bookmark(null,"devglan","https://www.devglan.com/", Instant.now()));
        list.add(new Bookmark(null,"linuxize","https://linuxize.com/", Instant.now()));

        bookmarkRepository.saveAll(list);
    }

//    -----> unparameterized test <--------------
    @Test
    void shouldGetBookmarks() throws Exception {
       mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks"))
               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)));
               .andExpect(jsonPath("$.totalElements",CoreMatchers.equalTo(5)))
               .andExpect(jsonPath("$.totalPages",CoreMatchers.equalTo(3)))
               .andExpect(jsonPath("$.currentPage",CoreMatchers.equalTo(1)))
               .andExpect(jsonPath("$.hasNext",CoreMatchers.equalTo(true)))
               .andExpect(jsonPath("$.hasPrev",CoreMatchers.equalTo(false)));


    }

//  ---->  to do parameterized test  in our case when pass the page parameter <----
//    @ParameterizedTest
//    @CsvSource({
//            "1",
//            "2",
//            "3"
//    })
//    void shouldGetBookmarksWithPageProvided(int pageNo) throws Exception{
//        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNo))
//                .andExpect(status().isOk())
////               .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)));
//                .andExpect(jsonPath("$.totalElements",CoreMatchers.equalTo(5)))
//                .andExpect(jsonPath("$.totalPages",CoreMatchers.equalTo(3)))
//                .andExpect(jsonPath("$.currentPage",CoreMatchers.equalTo(1)))
//                .andExpect(jsonPath("$.hasNext",CoreMatchers.equalTo(true)))
//                .andExpect(jsonPath("$.hasPrev",CoreMatchers.equalTo(false)));
//
//
//    }

    //----->. best way <--------

    @ParameterizedTest
    @CsvSource({
            "1,5,3,1,true,false",
            "2,5,3,2,true,true",
            "3,5,3,3,false,true"
    })
    void shouldGetBookmarksWithPageProvided_2(int pageNo,int totalElements,int totalPages ,int currentPage,boolean hasNext ,boolean hasPrev) throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/api/bookmarks?page="+pageNo))
                .andExpect(status().isOk())
//               .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(15)));
                .andExpect(jsonPath("$.totalElements",CoreMatchers.equalTo(totalElements)))
                .andExpect(jsonPath("$.totalPages",CoreMatchers.equalTo(totalPages)))
                .andExpect(jsonPath("$.currentPage",CoreMatchers.equalTo(currentPage)))
                .andExpect(jsonPath("$.hasNext",CoreMatchers.equalTo(hasNext)))
                .andExpect(jsonPath("$.hasPrev",CoreMatchers.equalTo(hasPrev)));


    }
    @Test
    void shouldCreateBookmarkSucessfully()throws Exception{
      this.mvc.perform(
              post("/api/bookmarks")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content("""

{"title": "freedom fighters of 70s",
"url": "www.bahgna.com"}
""")
      ).andExpect(status().isCreated())
              .andExpect(jsonPath("$.id",notNullValue()))
              .andExpect(jsonPath("$.title",is("freedom fighters of 70s")))
              .andExpect(jsonPath("$.url",is("www.bahgna.com")));
    }

    @Test
    void shouldFailToCreateBookmarkWhenUrlIsNotPresent()throws Exception{
        this.mvc.perform(
                post("/api/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
{"title": "zombies"}
""")
        ).andExpect(status().isBadRequest())
//                --> below details we get from postman response & header when fail end with 400 status
                .andExpect(header().string("content-Type",is("application/problem+json")))
                .andExpect(jsonPath("$.type",is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title",is("Constraint Violation")))
                .andExpect(jsonPath("$.status",is(400)))
                .andExpect(jsonPath("$.violations",hasSize(2)));


    }

    @Test
    void shouldFailToCreateBookmarkWhenTitleIsNotPresent() throws Exception{
        this.mvc.perform(
                post("/api/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
{"url": "www.gomida.com"}
""")
        ).andExpect(status().isBadRequest())
                .andExpect(header().string("content-type",is("application/problem+json")))
                .andExpect(jsonPath("$.type",is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title",is("Constraint Violation")))
                .andExpect(jsonPath("$.status",is(400)))
                .andExpect(jsonPath("$.violations",hasSize(2)));
    }

}