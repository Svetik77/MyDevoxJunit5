package org.craftsrecords.rememberme.api;

import org.craftsrecords.rememberme.bookmark.Bookmark;

import java.util.Collection;

@FunctionalInterface
public interface ICreateBookmark {
    Bookmark forResource(String url, String name, Collection<String> tags);
}
