CREATE DATABASE touristattraction;
USE touristattraction;

CREATE TABLE tags (
TagID int(2) NOT NULL AUTO_INCREMENT,
TagName varchar(100) NOT NULL UNIQUE,
PRIMARY KEY (tagID)
);

CREATE TABLE cities (
CityID int(2) NOT NULL AUTO_INCREMENT,
Name varchar(100) NOT NULL UNIQUE,
PRIMARY KEY (CityID)
);

CREATE TABLE attraction (
AttractionID int(2) NOT NULL AUTO_INCREMENT,
Name varchar(100) NOT NULL UNIQUE,
Description varchar(100),
CityID int(2) NOT NULL,
PRIMARY KEY (AttractionID),
FOREIGN KEY (CityID) REFERENCES cities(CityID)
);

CREATE TABLE attraction_tag (
AttractionID int(2) NOT NULL,
TagID int(2) NOT NULL,
FOREIGN KEY (AttractionID) REFERENCES attraction(AttractionID),
FOREIGN KEY (TagID) REFERENCES cities(CityID)
)