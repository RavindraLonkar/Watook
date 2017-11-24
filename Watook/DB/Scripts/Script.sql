-- document typeID for upload docuement
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 1,'DocumentTypeID','DocumentTypeID',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 1)

-- picture codevalue for docuement type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 101,1,'Picture','Picture', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 1 and CodeValueID=101)
----------------------------------------------------


-- Gender 
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 2,'Gender','Gender',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 2)

--male codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 201,2,'Male','Male', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 2 and CodeValueID=201)

-- female codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 202,2,'Female','Female', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 2 and CodeValueID=202)
--------------------------------------------

-- Salutation 
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 3,'Salutation','Salutation',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 3)

-- Mr codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 301,3,'Mr','Mr', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=301)

-- Mrs codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 302,3,'Mrs','Mrs', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=302)

-- Dr codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 303,3,'Dr','Dr', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=303)

-- M/S codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 304,3,'M/S','M/S', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=304)

-- Master codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 305,3,'Master','Master', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=305)

--Miss codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 306,3,'Miss','Miss', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=306)

-- Prof codevalue for Salutation type
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 307,3,'Prof','Prof', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 3 and CodeValueID=307)
----------------------------------------------------

---------------------------------------------------
-- Status Info 
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 4,'StatusInfo','StatusInfo',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 4)

--online
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 401,4,'Online','Online', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 4 and CodeValueID=401)

--offline
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 402,4,'Offline','Offline', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 4 and CodeValueID=402)

--DND
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 403,4,'Do Not Disturb','Do Not Disturb', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 4 and CodeValueID=403)
-----------------------------------------------------

-----------------------------------------------------
-- Request Status
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 5,'RequestStatus','RequestStatus',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 5)

--Accept
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 501,5,'Accepted','Accepted', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 5 and CodeValueID=501)

-- Reject
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 502,5,'Rejected','Rejected', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 5 and CodeValueID=502)

-- Sent Request
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 503,5,'Liked','Liked', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 5 and CodeValueID=503)

-- Blocked
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 504,5,'Blocked','Blocked', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 5 and CodeValueID=504)

----------------------------------------------------


-- Distance In 
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 6,'Distance In','Distance In',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 6)

--Distance in KM
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 601,6,'KM','KM', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 6 and CodeValueID=601)

-- Distance in miles
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 602,6,'Miles','Miles', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 6 and CodeValueID=602)

-- Distance in meter
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 603,6,'Meter','Meter', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 6 and CodeValueID=603)

--------------------------------------------

-- Isactive Status
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 7,'ActiveStatus','ActiveStatus',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 7)

--Isactive
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 0,7,'Accepted','Accepted', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 7 and CodeValueID=0)


-----------------------------------------------

--terms and condition codetype
insert into cfg_codeType (CodeTypeID,CodeType,CodeTypeDescription,CreatedBy,CreatedDate,LastModifiedBy,LastModifiedDate) 
select 8,'TermsAndCondition','TermsAndCondition',1,now(),1,now()
where NOT EXISTS(select * from cfg_codeType where codeTypeId = 8)

-- terms and condition codevalue 
insert into CFG_CodeValue(CodeValueID, CodeTypeId, CodeValue, CodeValueDescription, DisplaySeqNo, IsDefault, IsDisplay, IsActive, CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate)
select 801,8,'1.Terms And Condition For Watook','1.Terms And Condition For Watook', 1,0,1,1,1,now(),1,now()
where NOT EXISTS(select * from CFG_CodeValue where codeTypeId = 1 and CodeValueID=801)


-----------------------------------------------------




ALTER Table txn_user
ADD Column workemployer varchar(255);

ALTER Table txn_user
ADD Column worklocation varchar(255);

ALTER Table txn_user
ADD Column workposition varchar(255);

ALTER Table txn_user
DROP column workinfo

Alter table txn_user
add column discoverable int

alter table txn_userrequest 
add column isactive int

Alter table txn_usertracking
add column lastmodifieddate timestamp without time zone

Alter table txn_user
add column LastActive timestamp without time zone

alter table txn_user 
alter column dob type character varying(25) using dob::character varying(25)

-----------------------------------------------------
-- change name of the setting table id
ALTER TABLE txn_usersetting RENAME COLUMN id TO settingId

----------------------------------------------------

alter table txn_user 
add column fireBaseToken text

--------------------------------------------------