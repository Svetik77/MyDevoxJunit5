package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.IFindBookmarks;

import java.util.Collection;
import java.util.stream.Collectors;

public class BookmarksFinder implements IFindBookmarks {

    private Bookmarks bookmarks;

    public BookmarksFinder(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public Collection<Bookmark> by(String tag) {
        return bookmarks.getAll().stream()
                .filter(bookmark -> bookmark.hasTag(tag))
                .collect(Collectors.toSet());
    }

}
