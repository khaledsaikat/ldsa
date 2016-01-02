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
    isInId BIGINT
);

CREATE TABLE organisationPlaces (
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
    organisationProfileId BIGINT
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
    attendingEventIds LIST<BIGINT>,
    linkedOtherProfileIds LIST<BIGINT>,
    dateFounded TIMESTAMP
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
    attendingEventIds LIST<BIGINT>,
    linkedOtherProfileIds LIST<BIGINT>,
    sex INT,
    birthday TIMESTAMP,
    relationshipStatus INT,
    relationshipPersons LIST<BIGINT>
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
    contentTimestamp TIMESTAMP,
    crawlingTimestamp TIMESTAMP,
    text VARCHAR,
    commenter BIGINT,
    mediaId BIGINT,
    hashtagNames LIST<VARCHAR>,
    likerIds LIST<BIGINT>,
    commentIds LIST<BIGINT>
);

CREATE TABLE interests (
    id BIGINT PRIMARY KEY,
    kinds LIST<INT>
);