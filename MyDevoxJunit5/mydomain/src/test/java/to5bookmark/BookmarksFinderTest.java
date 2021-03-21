package to5bookmark;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.stream.Stream;

import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.BookmarksFinder;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BookmarksFinderTest {
                                                                        
    private static Bookmarks bookmarks;

    @ParameterizedTest
    @MethodSource("testCases2")
    public void should_find_bookmarks_by_tag(String tag, Bookmark[] expected) {
        FindBookmarks findBookmarks = new BookmarksFinder(bookmarks);
        Collection<Bookmark> bookmarks = findBookmarks.by(tag);

        assertThat(bookmarks).containsExactlyInAnyOrder(expected);
    }

    public static Stream<Arguments> testCases2() {
        bookmarks = new InMemoryBookmarks();

        Bookmark junit = saveBookmark("https://junit.org", "JUnit", "tests");
        Bookmark cucumber = saveBookmark("https://cucumber.io", "Cucumber", "tests", "bdd");
        Bookmark bdd = saveBookmark("https://en.wikipedia.org/wiki/BDD", "BDD", "bdd", "methodo");
 
        return   Stream.of(Arguments.arguments("tests", new Bookmark[]{junit, cucumber} ),
        		Arguments.arguments( "bdd", new Bookmark[]{cucumber, bdd}),
        		Arguments.arguments("methodo", new Bookmark[]{bdd} )
        		);
    }

    private static Bookmark saveBookmark(String url, String name, String... tags) {
        Bookmark bookmark = Bookmark.create(url, name, asList(tags));
        return bookmarks.save(bookmark);
    }

}