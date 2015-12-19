package de.due.ldsa.db.model;

import java.net.URL;
import java.util.ArrayList;

/**
 *
 */
public class ProfileFeed extends SocialNetworkContentImpl
{
    Profile profile;
    String rawStoryText;
    ArrayList<Profile> liker;
    ArrayList<Profile> shares;
    //The type "Hashtag" is missing in the Content-Model
    //ArrayList<Hashtag> hashtags;
    ArrayList<URL> links;
    Location location;
    Media media;
    ArrayList<Profile> taggedUser;
    //The type "Comment" is missing in the Content-Model
    //ArrayList<Comment> comments;
}
