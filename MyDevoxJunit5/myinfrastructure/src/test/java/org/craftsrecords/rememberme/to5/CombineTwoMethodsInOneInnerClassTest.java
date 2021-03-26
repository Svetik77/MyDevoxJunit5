package org.craftsrecords.rememberme.to5;

//import org.junit.runner.RunWith;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.Optional;

import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.repository.BookmarkEntity;
import org.craftsrecords.rememberme.repository.BookmarkRepository;
import org.craftsrecords.rememberme.repository.JpaBookmarkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CombineTwoMethodsInOneInnerClassTest {

	private static final String URL = "http://www.test.com";


	@Autowired
	private JpaBookmarkRepository jpaBookmarkRepository;

	private BookmarkRepository bookmarkRepository;
	private Bookmark bookmark;

	@BeforeEach
	public void set_up() {
		bookmarkRepository = new BookmarkRepository(jpaBookmarkRepository);
		bookmark = Bookmark.create(URL, "test", singleton("tag"));
	}

	@Test
	public void should_save_bookmark() {
		Bookmark saved = bookmarkRepository.save(bookmark);
		assertThat(saved).isEqualTo(bookmark);
	}

	@Test
	public void should_not_save_an_already_existent_bookmark() {
		/** save twice to check if save exists data */
		bookmarkRepository.save(bookmark);

		Assertions.assertThrows(AlreadyBookmarkedException.class,
				() -> bookmarkRepository.save(bookmark));

	}
//==================================================================
	@Test
	public void should_retrieve_bookmark_by_url() {
		jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

		Optional<Bookmark> retrieved = bookmarkRepository.getBy(URL);
		assertThat(retrieved).hasValue(bookmark);
	}

	@Test
	public void should_retrieve_all_bookmarks() {
		jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

		Collection<Bookmark> retrieved = bookmarkRepository.getAll();
		assertThat(retrieved).contains(bookmark);
	}
 //==================================================================
	//==============================same in better way====================================
	@Nested
	@DisplayName("combine two methods in one inner class")
	class WhenBookMarkIsSaved {
		@BeforeEach
		void setUp() {
			jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));
		}
		
		@Test
		public void should_retrieve_bookmark_by_url2() {
//			jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

			Optional<Bookmark> retrieved = bookmarkRepository.getBy(URL);
			assertThat(retrieved).hasValue(bookmark);
		}

		@Test
		public void should_retrieve_all_bookmarks2() {
//			jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

			Collection<Bookmark> retrieved = bookmarkRepository.getAll();
			assertThat(retrieved).contains(bookmark);
		}
	}
	//==============================same in better way====================================
	
	
	
	
}
