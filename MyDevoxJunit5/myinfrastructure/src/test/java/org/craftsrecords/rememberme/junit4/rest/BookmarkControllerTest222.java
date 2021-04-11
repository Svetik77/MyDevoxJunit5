package org.craftsrecords.rememberme.junit4.rest;

import static java.util.Collections.emptyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.craftsrecords.rememberme.rest.BookmarkPayload;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

/** org.craftsrecords.rememberme.junit4.rest.MyResolver => in resources file*/
/**pic with explains =>  junitextends_globaly.png*/

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder( OrderAnnotation.class)
@ExtendWith(MyResolver.class)
public class BookmarkControllerTest222 {
// pic : junitextends_globaly.png
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void should_respond_201_when_the_bookmark_is_created(BookmarkPayload bookmarkPayload) throws Exception {
        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_respond_400_when_the_request_is_invalid(@MyFake BookmarkPayload bookmarkPayload ) throws Exception {

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_respond_409_when_the_bookmark_already_exists(BookmarkPayload bookmarkPayload) throws Exception {

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isConflict());
    }

    @Test
    public void should_respond_200_when_a_search_is_successfully_done() throws Exception {

        mockMvc.perform(
                get("/bookmarks")
                        .param("tag", "good-stuff"))
                .andExpect(status().isOk());
    }

}