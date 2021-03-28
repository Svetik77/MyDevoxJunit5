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

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder( OrderAnnotation.class)
@ExtendWith(MyBookmarkPayloadResolver.class)
public class BookmarkController_ExtendWithTest_4 {
  
	/**new Parameter resolver to able 
	 * directly Inject my own Objects without spring
	 */
	
	
    @Autowired
    private MockMvc mockMvc;
 
/**
 * this init bookmarkPayload go  into resolveParameter() <br>
 *  of MyBookmarkPayloadResolver <br>
 * don't need this ini 
 */
//    private static final BookmarkPayload bookmarkPayload =
//    		new BookmarkPayload(
//            "http://www.test.com",
//            "A test link",
//            singletonList("good-stuff"))  ;

    @Test
    @Order(1)
    public void should_respond_201_when_the_bookmark_is_created(
    		@Autowired ObjectMapper objectMapper, 
    		BookmarkPayload bookmarkPayload) 
    		 throws Exception {
        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON) 
                        .content(objectMapper.writeValueAsString(bookmarkPayload))) // introduse parameter
                .andExpect(status().isCreated());
    }  

    @Test
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

    /**second control not save twice bootstrapping самозагрузка
     * @TestMethodOrder( OrderAnnotation.class) <br>
     * @param bookmarkPayload TODO
     * @throws Exception
     */
    @Test  
    public void should_respond_409_when_the_bookmark_already_exists(
    		@Autowired ObjectMapper objectMapper, 
    		BookmarkPayload bookmarkPayload) 
    		throws Exception {

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