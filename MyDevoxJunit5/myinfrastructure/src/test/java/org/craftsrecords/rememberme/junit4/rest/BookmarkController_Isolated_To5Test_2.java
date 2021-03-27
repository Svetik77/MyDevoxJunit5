package org.craftsrecords.rememberme.junit4.rest;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.craftsrecords.rememberme.repository.BookmarkRepository;
import org.craftsrecords.rememberme.rest.BookmarkPayload;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

// @RunWith(SpringRunner.class)
// @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder( OrderAnnotation.class)
public class BookmarkController_Isolated_To5Test {
/**
 * DirtiesContext => save AFTER_EACH_TEST_METHOD 
 * performance issue проблема с производительностью
 * with @TestMethodOrder( OrderAnnotation.class)
 * you don't need !!!   <br>
 */
  
    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper; <== as Parameters of method

//    @Autowired
//    private BookmarkRepository bookmarkRepository;  // with @TestMethodOrder  don't need !!!

    private static final BookmarkPayload bookmarkPayload = new BookmarkPayload(
            "http://www.test.com",
            "A test link",
            singletonList("good-stuff")
    );

    @Test
    @Order(1)
//    public void should_respond_201_when_the_bookmark_is_created() throws Exception {
    public void should_respond_201_when_the_bookmark_is_created(@Autowired ObjectMapper objectMapper) 
    		 throws Exception {
        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isCreated());
    }  

    @Test
//    public void should_respond_400_when_the_request_is_invalid() 
    public void should_respond_400_when_the_request_is_invalid(@Autowired ObjectMapper objectMapper) 
    		throws Exception {
        BookmarkPayload bookmarkPayload = new BookmarkPayload(
                "invalid://url.com",
                "An invalid link",
                emptyList()
        );

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isBadRequest());
    }

    /**
     * @TestMethodOrder( OrderAnnotation.class) <br>
     * @throws Exception
     */
    @Test // second control not save twice bootstrapping самозагрузка
//    public void should_respond_409_when_the_bookmark_already_exists() 
    public void should_respond_409_when_the_bookmark_already_exists(@Autowired ObjectMapper objectMapper) 
    		throws Exception {
/*@TestMethodOrder( OrderAnnotation.class)  don't need all the time save now*/
    	//        bookmarkRepository.save(bookmarkPayload.toBookmark());

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isConflict());
    }

    @Test
    public void should_respond_200_when_a_search_is_successfully_done() throws Exception {
    	/*@TestMethodOrder( OrderAnnotation.class)  don't need all the time save now*/
    	//        bookmarkRepository.save(bookmarkPayload.toBookmark());

        mockMvc.perform(
                get("/bookmarks")
                        .param("tag", "good-stuff"))
                .andExpect(status().isOk());
    }

}