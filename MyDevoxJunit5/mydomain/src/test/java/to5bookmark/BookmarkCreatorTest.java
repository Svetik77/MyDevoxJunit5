package to5bookmark;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.Set;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.BookmarkCreator;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class BookmarkCreatorTest {

    private static String url;
    private static String name;
    private static Set<String> tags;

    private CreateBookmark createBookmark;
    private Bookmarks bookmarks;

    @BeforeAll
    public static void global_set_up() {
        url = "http://www.test.com";
        name = "Some name";
        tags = singleton("tag");
    }

    @BeforeEach
    public void set_up() {
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
    }

    @AfterEach
    public void tear_down() {
        bookmarks = null;
        createBookmark = null;
    }

    @Test
      void should_create_the_bookmark() {
        createBookmark.forResource(url, name, tags);

        Optional<Bookmark> saved = bookmarks.getBy(url);

        Bookmark expected = Bookmark.create(url, name, tags);
        assertThat(saved).hasValue(expected);
    }

    @Test
      void should_return_the_bookmark_after_creating_it() {
        Bookmark createdBookmark = createBookmark.forResource(url, name, tags);

        assertThat(createdBookmark).isNotNull();
    }

    @Test
//    @Ignore("Reason why this is not run")
    @Disabled("Reason why this is not run")
    public void should_not_be_run() {
//    	Assert.fail();
        Assertions.fail();
    }

}