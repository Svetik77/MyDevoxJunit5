package org.craftsrecords.rememberme.config;

import org.craftsrecords.rememberme.api.ICreateBookmark;
import org.craftsrecords.rememberme.api.IFindBookmarks;
import org.craftsrecords.rememberme.bookmark.BookmarkCreator;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.BookmarksFinder;
import org.craftsrecords.rememberme.repository.BookmarkRepository;
import org.craftsrecords.rememberme.repository.JpaBookmarkRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public Bookmarks bookmarks(JpaBookmarkRepository jpaBookmarkRepository) {
        return new BookmarkRepository(jpaBookmarkRepository);
    }

    @Bean
    public ICreateBookmark createBookmark(Bookmarks bookmarks) {
        return new BookmarkCreator(bookmarks);
    }

    @Bean
    public IFindBookmarks findBookmarks(Bookmarks bookmarks) {
        return new BookmarksFinder(bookmarks);
    }

}
