//"ldsa" = "link-data-stream-analysis"
DROP KEYSPACE IF EXISTS ldsa;

CREATE KEYSPACE IF NOT EXISTS ldsa
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

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
    hashtagIds LIST<INT>,
    links LIST<VARCHAR>,
    locationId INT,
    mediaId INT,
    taggedUserIds LIST<BIGINT>,
    commentIds LIST<INT>
);

CREATE TABLE media (
    id INT PRIMARY KEY,
    crawlingPath VARCHAR,
    filetyp INT,
    bytes BLOB
);

CREATE TABLE locations (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    name VARCHAR,
    timesUsed INT,
    city VARCHAR,
    country VARCHAR,
    latidue DOUBLE,
    longitude DOUBLE,
    isInId BIGINT
);

CREATE TABLE organisationPlaces (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    name VARCHAR,
    timesUsed INT,
    city VARCHAR,
    country VARCHAR,
    latidue DOUBLE,
    longitude DOUBLE,
    isInId BIGINT,
    organisationProfileId BIGINT
);


CREATE TABLE coopProfiles (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    userId BIGINT,
    username VARCHAR,
    profileURL VARCHAR,
    fullname VARCHAR,
    bio VARCHAR,
    interestIds LIST<INT>,
    userEmail VARCHAR,
    userWebsite VARCHAR,
    profilePhotoMediaId INT,
    lastUpdateProfileFeedId INT,
    followedIds LIST<INT>,
    followedByIds LIST<INT>,
    friendIds LIST<INT>,
    profileFeedIds LIST<INT>,
    attendingEventIds LIST<INT>,
    kind INT
);


CREATE TABLE humanProfiles (
    id BIGINT PRIMARY KEY,
    snId INT,
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    userId BIGINT,
    username VARCHAR,
    profileURL VARCHAR,
    fullname VARCHAR,
    bio VARCHAR,
    interestIds LIST<INT>,
    userEmail VARCHAR,
    userWebsite VARCHAR,
    profilePhotoMediaId INT,
    lastUpdateProfileFeedId INT,
    followedIds LIST<INT>,
    followedByIds LIST<INT>,
    friendIds LIST<INT>,
    profileFeedIds LIST<INT>,
    attendingEventIds LIST<INT>,
    sexId INT,
    birthday TIMESTAMP,
    relationshipStatusId INT,
    relationshipPersons LIST<BIGINT>    //this is a LIST and not a single number because open relationships may include
                                        //more than two people.
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
    eventText VARCHAR
);

CREATE TABLE comments (

    id BIGINT PRIMARY KEY,
    snId INT,
    text VARCHAR,
    commenter BIGINT,
    mediaId BIGINT,
    hastagsIds LIST<BIGINT>,
    likerIds LIST<BIGINT>,
    commentsIds LIST<BIGINT>,

);

CREATE TABLE hastags (

    id BIGINT PRIMARY KEY,
    snId INT,
    title VARCHAR,
    usedAtListIds LIST<BIGINT>,

);