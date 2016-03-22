drop table if exists department;
drop table if exists grad;
drop table if exists staff;
drop table if exists faculty;
drop table if exists undergrad;
drop table if exists address;

create table address (
	addressId integer primary key autoincrement,
	city varchar(15),
	stateAbbr varchar(10),
	zipcode integer(6)
);

create table grad (
	gradId integer primary key autoincrement,
	lastname varchar(25),
	firstname varchar(25), 
	phone integer(10),
	email varchar(30),
	addressId integer,
	office varchar(10),
	url varchar(50),
	gpa varchar(10),
	foreign key(addressId) references address(addressId)
);

create table staff (
	staffId integer primary key autoincrement,
	lastname varchar(25),
	firstname varchar(25),
	phone integer(10),
	email varchar(30),
	office varchar(10)
);

create table faculty (
	facultyId integer primary key autoincrement,
	lastname varchar(25),
	firstname varchar(25),
	phone integer(10),
	email varchar(30),
	office varchar(10)
);

create table undergrad (
	undergradId integer primary key autoincrement,
	lastname varchar(25),
	firstname varchar(25),
	phone integer(10),
	email varchar(30),
	addressId integer,
	gpa varchar(10),
	foreign key(addressId) references address(addressId)
);

create table department (
	deptId integer primary key autoincrement,
	deptname varchar(50),
	gradId integer,
	staffId integer,
	facultyId integer,
	undergradId integer,
	foreign key(gradId) references grad(gradId),
	foreign key(staffId) references staff(staffId),
	foreign key(facultyId) references faculty(facultyId),
	foreign key(undergradId) references undergrad(undergradId)
);