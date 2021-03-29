package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.ICreateBookmark;

import java.util.Collection;

public class BookmarkCreator implements ICreateBookmark {

    private Bookmarks bookmarks;

    public BookmarkCreator(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public Bookmark forResource(String url, String name, Collection<String> tags) {
        Bookmark bookmark = Bookmark.create(url, name, tags);
        return bookmarks.save(bookmark);
    }

}
