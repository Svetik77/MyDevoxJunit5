package to5bookmark;

import static java.util.Collections.emptySet;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Bookmark_BlankTest {

	@ParameterizedTest
	@ValueSource(strings = { "", " ", "\t", "    ", })
	@NullSource
	public void should_not_accept_an_blank_name(String blanks) {
		Assertions.assertThrows(IllegalArgumentException.class,
// junit 4   Bookmark.create("http://www.test.com", "  ", emptySet()));
				() -> Bookmark.create("http://www.test.com", blanks, emptySet()));

	}

	@Test
	public void check() {
//	@Pattern(regexp = "\\A(?!\\s*\\Z).+")
		String someString;

		System.out.println("ABC".isBlank()); // false
		System.out.println("  ".isBlank()); // true

		// if, string length is 0.
		System.out.println("ABC".isEmpty()); // false
		System.out.println("  ".isEmpty()); // false must be true
	}

}