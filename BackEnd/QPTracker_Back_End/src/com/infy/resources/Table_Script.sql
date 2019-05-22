DROP TABLE Login;
drop table QPdetails1;
drop table requests;
drop table batchDetails;
create table Login(
	username varchar(15) primary key,
	password varchar(20) NOT NULL,
	category varchar(10) NOT NULL,
	sQuestion varchar(50),
	sAnswer varchar(20)
);
create table QPdetails1(id number(6) primary key,
qpId varchar(50),
qpName varchar(100) NOT NULL,
file1 BLOB,
remarks varchar(100),
reviewedDate date,
reviewerName varchar(50),
status varchar(5),
rolled_out_date date,
creation_date date,
creator_name varchar(30),
eta_name varchar(30)
);
create table batchDetails(
batch varchar(30)
);  
CREATE TABLE requests(reqId number(6) primary key,
emptype varchar(15),
component varchar(10),
creator_name varchar(30),
reviewer_name varchar(30),
deadline_date date
);

select * from requests;
insert into batchDetails values('May19-Trainee-Batch1');
insert into batchDetails values('Aug19-Interns-Batch1');
insert into QPdetails1 values (125,'Jee-java','MSDBikespot',null,'hkghjg',null,'vishal17.TRNN','no',null);
insert into QPdetails1 values ('128','seaqueenboats',null,'hi',null,'sanjay13','no',null);
insert into QPdetails1 values (127,'JEE-JUNE2018-Interns-FA-1-103','airflight',null,'hi',null,'sanjay13','yes',null,null,null,null);
insert into QPdetails1 values (127,'JEE-JUNE2018-Interns-FA-1-103','airflight',null,'hi',null,'sanjay13','yes',null,null,null,null);
insert into QPdetails1 values (,'JEE-JUNE2018-Interns-FA-1-3','sasasa',null,'hi',null,'sanjay13','Yes',TO_DATE('2018/06/03 21:02:44', 'yyyy/mm/dd hh24:mi:ss'),null,null,null);
insert into Login values ('vishal17.TRN','12345678','ETA','What is Your Favourite food?','Chicken');
--insert into Login values ('vishal17.TRNN','12345678','Reviewer','What is Your Favourite food?','paneer');
insert into Login values ('sanjay13.TRN','12345678','Reviewer','What is Your Favourite food?','potato');
insert into Login values ('prince04.TRN','12345678','Reviewer','What is Your Favourite food?','potato');
insert into Login values ('saurav13.TRN','12345678','QPEditor','What is Your Favourite food?','cauli');
insert into Login values ('vishal16.TRN','12345678','ETA','What is Your Favourite food?','ladies fingur');
insert into Login values ('sanjay03.TRN','12345678','ETA','What is Your Favourite food?','potato');
SELECT * FROM Login;
SELECT qpId,rolled_out_date,status FROM QPdetails1;
SELECT id,qpId,qpName,creation_date,remarks from QPdetails1;
select id,qpId,qpName,reviewedDate,creation_date,remarks,status,reviewerName,creator_name,rolled_out_date from QPdetails1;
delete from QPdetails1 where id=2;
update QPdetails1 set reviewedDate="2019/05/14" where id=130;
--select qpId,remarks FROM QPdetails;updat
SELECT qpId FROM QPdetails1 WHERE reviewerName='sanjay13';
--select qpId,remarks,reviewedDate FROM QPdetails;
select * from batchDetails;
