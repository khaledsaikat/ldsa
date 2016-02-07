//"ldsa" = "link-data-stream-analysis"
DROP KEYSPACE IF EXISTS ldsa;

CREATE KEYSPACE IF NOT EXISTS ldsa
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};    //change the "1" for the amount of how many
                                                                            //copies should be distributed among the
                                                                            //nodes.

USE ldsa;

CREATE TABLE socialNetworks (
    id INT PRIMARY KEY,
    name VARCHAR,
    homeURL VARCHAR,
    logo BLOB
);

//Example:
//CREATE TABLE whatever(id BIGINT PRIMARY KEY, snId INT, contentTimestamp TIMESTAMP, crawlingTimestamp TIMESTAMP);

CREATE TABLE profileFeeds (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    profileId BIGINT,
    rawStoryText VARCHAR,
    likerIds LIST<BIGINT>,
    sharerIds LIST<BIGINT>,
    hashtags LIST<VARCHAR>,
    links LIST<VARCHAR>,
    locationId INT,
    mediaId INT,
    taggedUserIds LIST<BIGINT>,
    commentIds LIST<BIGINT>
);

CREATE TABLE media (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    crawlingPath VARCHAR,
    filename VARCHAR,
    bytes BLOB
);

CREATE TABLE locations (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    name VARCHAR,
    city VARCHAR,
    country VARCHAR,
    latidue DOUBLE,
    longitude DOUBLE,
    isInId BIGINT,
    interestKinds LIST<INT>
);

CREATE TABLE coopLocations (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    name VARCHAR,
    city VARCHAR,
    country VARCHAR,
    latidue DOUBLE,
    longitude DOUBLE,
    isInId BIGINT,
    organisationProfileId BIGINT,
    interestKinds LIST<INT>
);

CREATE TABLE coopProfiles (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    username VARCHAR,
    profileURL VARCHAR,
    fullname VARCHAR,
    bio VARCHAR,
    interestIds LIST<BIGINT>,
    userEmail VARCHAR,
    userWebsite VARCHAR,
    profilePhotoMediaId BIGINT,
    lastUpdateProfileFeedId BIGINT,
    hometownLocationId BIGINT,
    followedIds LIST<BIGINT>,
    followedByIds LIST<BIGINT>,
    friendIds LIST<BIGINT>,
    profileFeedIds LIST<BIGINT>,
    allComments LIST<BIGINT>,
    attendingEventIds LIST<BIGINT>,
    linkedOtherProfileIds LIST<BIGINT>,
    dateFounded TIMESTAMP,
    interestKinds LIST<INT>
);

CREATE TABLE humanProfiles (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    username VARCHAR,
    profileURL VARCHAR,
    fullname VARCHAR,
    bio VARCHAR,
    interestIds LIST<BIGINT>,
    userEmail VARCHAR,
    userWebsite VARCHAR,
    profilePhotoMediaId BIGINT,
    lastUpdateProfileFeedId BIGINT,
    hometownLocationId BIGINT,
    followedIds LIST<BIGINT>,
    followedByIds LIST<BIGINT>,
    friendIds LIST<BIGINT>,
    profileFeedIds LIST<BIGINT>,
    allComments LIST<BIGINT>,
    attendingEventIds LIST<BIGINT>,
    linkedOtherProfileIds LIST<BIGINT>,
    sex INT,
    birthday TIMESTAMP,
    relationshipStatus INT,
    relationshipPersons LIST<BIGINT>,
    interestKinds LIST<INT>
);

CREATE TABLE events (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    name VARCHAR,
    hostIds LIST<BIGINT>,
    locationId BIGINT,
    invitedIds LIST<BIGINT>,
    attendingIds LIST<BIGINT>,
    eventText VARCHAR,
    interestKinds LIST<INT>
);

CREATE TABLE comments (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    text VARCHAR,
    commenter BIGINT,
    mediaId BIGINT,
    hashtagNames LIST<VARCHAR>,
    likerIds LIST<BIGINT>,
    commentIds LIST<BIGINT>
);

CREATE TABLE hashtags (
    title VARCHAR PRIMARY KEY
);


// Cassandra is unable to use non-primary key columns in a WHERE query (WHY???), therefore we have to create a bunch of
// indexes right here.

CREATE INDEX humanProfile_socialNetwork ON humanprofiles (snid);
CREATE INDEX coopProfile_socialNetwork ON coopprofiles (snid);
CREATE INDEX profileFeed_socialNetwork ON profilefeeds (snid);
CREATE INDEX media_socialNetwork ON media (snid);
CREATE INDEX comment_socialNetwork ON comments (snid);
CREATE INDEX location_socialNetwork ON locations (snid);
CREATE INDEX coopLocations_socialNetwork ON coopLocations (snid);
CREATE INDEX event_socialNetwork ON events (snid);

CREATE INDEX profileFeeds_hashtagIndex ON profilefeeds (hashtags);
CREATE INDEX comments_hashtagIndex ON comments (hashtagnames);

CREATE INDEX profileFeeds_profileIndex ON profilefeeds (profileid);
CREATE INDEX comments_commenterIndex ON comments (commenter);

CREATE INDEX events_locationIndex ON events (locationid);