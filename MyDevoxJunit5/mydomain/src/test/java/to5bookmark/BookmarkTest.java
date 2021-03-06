package to5bookmark;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.jupiter.api.Assertions;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkTest {

//    @Rule
//    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void should_create_the_bookmark() {
        String url = "http://www.test.com";
        String name = "Some name";
        Set<String> tags = singleton("tag");
        Bookmark createdBookmark = Bookmark.create(url, name, tags);
        
        Assertions.assertAll(
        		() ->         assertThat(createdBookmark.getUrl()).isEqualTo(url) ,
        		 () ->       assertThat(createdBookmark.getName()).isEqualTo(name),
        		 () ->       assertThat(createdBookmark.getTags()).isEqualTo(tags) 
        		 );

//        assertThat(createdBookmark.getUrl()).isEqualTo(url);
//        assertThat(createdBookmark.getName()).isEqualTo(name);
//        assertThat(createdBookmark.getTags()).isEqualTo(tags);
    }

    @Test
    public void should_not_accept_an_invalid_url() {
    	Assertions.assertThrows(IllegalArgumentException.class,
    		() ->	Bookmark.create("invalid_url", "name", emptySet()));
//    	
//        exception.expect(IllegalArgumentException.class);
//        Bookmark.create("invalid_url", "name", emptySet());
    }

    @Test
    public void should_not_accept_an_empty_name() {
    	Assertions.assertThrows(IllegalArgumentException.class, 
    		() ->	Bookmark.create("http://www.test.com", "", emptySet()));
    	
    }

    @Test
    public void should_not_accept_an_blank_name() {
    	Assertions.assertThrows(IllegalArgumentException.class,  
    	() ->		 Bookmark.create("http://www.test.com", "  ", emptySet()));
      
    }

}