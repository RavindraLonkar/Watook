
create table CFG_CodeType(
CodeTypeID      	 serial not null,
CodeType             varchar(255) not null,    
CodeTypeDescription   varchar(255) null,
CreatedBy      		int null,
CreatedDate       	timestamp,
LastModifiedBy 		int,
LastModifiedDate	timestamp,
CONSTRAINT cfg_codetype_pkey PRIMARY KEY (CodeTypeID)
)

create table CFG_CodeValue(
CodeValueID           serial NOT NULL,
CodeTypeID      	  int not null,
CodeValue             varchar(255) not null,    
CodeValueDescription  varchar(1024) null,
DisplaySeqNo          int not null,
ParentCodeValueID     int null,
mapped_value1         varchar(255) null,
mapped_value2         varchar(255) null,
mapped_value3         varchar(255) null,
IsDefault             int not null,
IsDisplay             int not null,
IsActive              int not null,
CreatedBy      		  int null,
CreatedDate       	  timestamp,
LastModifiedBy 		  int,
LastModifiedDate	  timestamp,
CONSTRAINT CFG_CodeValue_pkey PRIMARY KEY (CodeValueID),
FOREIGN KEY(CodeTypeID) REFERENCES CFG_CodeType 
)

create table Txn_User(
UserID              serial not null,
FbID                varchar(4000) not null,
LastName      		varchar(510),
MiddleName     		varchar(510),
FirstName     		varchar(510),
DOB                 timestamp,
Age                 int,
GenderID            int,
ContactMobile       varchar(25),
ContactMobile2      varchar(25),
EmailID             varchar(500),
AdvertiseId         varchar(1020),
AboutYou            varchar(4000) not null,
WorkInfo            varchar(4000) not null,
StatusInfo			int not null,
FbImages			text not null,
ProfileImage		varchar(1000) not null,
IsActive            BIT not null,
CreatedBy		    int,
CreatedDate		    timestamp,
LastModifiedBy		int,
LastModifiedDate	timestamp,
CONSTRAINT Txn_User_pkey PRIMARY KEY (UserID) ,
unique (AdvertiseId),
FOREIGN KEY(GenderID) REFERENCES CFG_CodeValue, 
FOREIGN KEY(StatusInfo) REFERENCES CFG_CodeValue
)


CREATE TABLE TXN_Document(
	DocumentID serial NOT NULL,
	DocumentTypeID int NOT NULL,
	DocumentText varchar(4000) NULL,
	DocumentName varchar(1000) NULL,
	DocumentStorageLocationID int NOT NULL,
	FileName varchar(1000) NULL,
	FileStoragePathURI varchar(255) NULL,
	FileTypeID int NULL,
	FileSizeKB int NULL,
	IsDownloadModule bit NULL,
	DownloadModuleID int NULL,
	DownloadDescription varchar(4000) NULL,
	UserID  int not null,
    CreatedBy int NULL,
	CreatedDate timestamp NULL,
	LastModifiedBy int NULL,
	LastModifiedDate timestamp NULL,
	IsActive bit NOT NULL,
CONSTRAINT TXN_Document_pkey PRIMARY KEY (DocumentID) ,    
FOREIGN KEY(userid) REFERENCES txn_user(userid),
FOREIGN KEY(DocumentTypeID) REFERENCES CFG_CodeValue(CodeValueID),
FOREIGN KEY(FileTypeID) REFERENCES CFG_CodeValue(CodeValueID)    
)

create table TXN_UserTracking(
Id 	         serial not null,
UserId 	     int not null,
latitude 	 float8 NOT NULL,
longitude 	 float8 NOT NULL,
createdDate  timestamp NOT NULL,
CONSTRAINT Txn_TrackingData_pkey PRIMARY KEY (Id),
FOREIGN KEY(userid) REFERENCES txn_user(userid)
)

create table TXN_UserSetting(
Id 	             serial not null,
UserId 	         int not null,
DistanceRange    float8 NOT NULL,
DistanceIn       int NOT NULL,
AgeMin 			 int NOT NULL,
AgeMax 	         int NOT NULL,
FemaleInterest   int not null,
MaleInterest     int not null,
createdDate      timestamp NOT NULL,
LastModifiedDate timestamp not null,
CONSTRAINT TXN_UserSetting_pkey PRIMARY KEY (Id),
FOREIGN KEY(userid) REFERENCES txn_user(userid),
FOREIGN KEY(FemaleInterest) REFERENCES CFG_CodeValue, 
FOREIGN KEY(MaleInterest) REFERENCES CFG_CodeValue 
)

create table TXN_UserRequest(
Id 	               serial not null,
RequestBy          int not null,
RequestTo          int not null,    
ReqStatus 	           int NOT NULL,
RejectAttemtCount  int NOT NULL,
createdDate      timestamp NOT NULL,
LastModifiedDate	timestamp not null,
CONSTRAINT TXN_UserRequest_pkey PRIMARY KEY (Id),
FOREIGN KEY(RequestBy) REFERENCES txn_user(userid),
FOREIGN KEY(RequestTo) REFERENCES txn_user(userid),  
FOREIGN KEY(ReqStatus) REFERENCES cfg_codevalue(CodeValueID)      
)


create table TXN_ProfileRating(
RatingId 	           serial not null,
RatingBy          	   int not null,
RatingTo          	   int not null,    
createdDate      	   timestamp NOT NULL,
LastModifiedDate	   timestamp not null,
CONSTRAINT TXN_ProfileRating_pkey PRIMARY KEY (RatingId),
FOREIGN KEY(RatingBy) REFERENCES txn_user(userid),
FOREIGN KEY(RatingTo) REFERENCES txn_user(userid)    
)
