package de.due.ldsa.db.model;

import java.util.ArrayList;

/**
 *
 */
public class Comment {

    private String text;
    private Profile commenter;
    private Media media;
    private ArrayList<Hashtag> hashtags;
    private ArrayList<Profile> liker;
    private ArrayList<Comment> comments;

    public Media getMedia() {
        return media;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Profile> getLiker() {
        return liker;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
