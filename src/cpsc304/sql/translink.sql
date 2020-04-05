drop table passenger_take_vehicle;
drop table passenger_card1;
drop table passenger_card2;
drop table obtain_schedule;
drop table ferry1;
drop table ferry2;
drop table bus;
drop table concession;
drop table card;
drop table port;
drop table stop_at;
drop table vehicle_follow_drive1;
drop table route1;
drop table route2;
drop table vehicle_follow_drive2;
drop table station;


create table station(
	station_id integer primary key,
city char(20) );

create table vehicle_follow_drive2(
	phone char(20) primary key,
	name char(20));
 
create table route2(
	station_num integer primary key,
	approx_time char(20));
 
create table route1(
	route_id integer primary key,
	station_num integer,
	foreign key (station_num) references route2);
 
create table vehicle_follow_drive1(
	vehicle_id integer primary key,
	capacity integer,
	route_id integer not null,
	sin integer not null unique,
	phone char(20) unique,
	license_id char(20),
	foreign key (route_id) references route1,
	foreign key (phone) references vehicle_follow_drive2);

create table stop_at(
	station_id integer,
	vehicle_id integer,
	time char(20),
	primary key (station_id, vehicle_id),
	foreign key (station_id) references station,
	foreign key (vehicle_id) references vehicle_follow_drive1);
	
create table port(
	station_id integer primary key,
	dock_num integer,
	port_name char(20),
	foreign key (station_id) references station(station_id) );

create table card(
	card_num char(20) primary key,
	balance number(10,2),
	cvn char(20) );

create table concession(
	card_num char(20) primary key,
	discount_rate number(10,2),
	foreign key (card_num) references card(card_num) );

create table bus(
	vehicle_id integer primary key,
	plate_num char(20),
	foreign key (vehicle_id) references vehicle_follow_drive1);
 
create table ferry2(
	ferry_num integer primary key,
	max_wave integer);

create table ferry1(
	vehicle_id integer primary key,
	ferry_num integer,
	foreign key (vehicle_id) references vehicle_follow_drive1,
  foreign key (ferry_num) references ferry2);

create table obtain_schedule(
	station_id integer,
	route_id integer,
	valid_date char(10),
	arrival_time timestamp not null,
	primary key (station_id, route_id),
	foreign key (station_id) references station,
	foreign key (route_id) references route1);
 
create table passenger_card2(
	phone char(20) primary key,
	name char(20)); 

create table passenger_card1(
	sin integer primary key,
	phone char(20),
	user_id char(20) not null unique,
	email char(20),
	age integer,
	pin integer,
	card_num char(20) unique,
	foreign key(phone) references passenger_card2,
	foreign key (card_num) references card);

create table passenger_take_vehicle (
    sin integer,
    tk_time timestamp,
    fee number(10,2),
    vehicle_id integer,
    primary key (sin, tk_time, vehicle_id),
    foreign key (sin) references passenger_card1 on delete cascade,
    foreign key (vehicle_id) references vehicle_follow_drive1);
 
 
insert into station
values ( '1' , 'west van' );

insert into station
values ( '2' , 'surrey' );

insert into station
values ( '3' , 'north van' );

insert into station
values ( '4' , 'richmond' );

insert into station
values ( '5' , 'burnaby' );

insert into station
values ( '6' , 'west van' );

insert into station
values ( '7' , 'surrey' );

insert into station
values ( '8' , 'north van' );

insert into station
values ( '9' , 'richmond' );

insert into station
values ( '10' , 'burnaby' );

insert into vehicle_follow_drive2
values ( '1112223333' , 'Alice' );

insert into vehicle_follow_drive2
values ( '4445556666' , 'Ben' );

insert into vehicle_follow_drive2
values ( '7778889999' , 'Cara' );

insert into vehicle_follow_drive2
values ( '1110002222' , 'Drake' );

insert into vehicle_follow_drive2
values ( '2223334444' , 'Eason' );

insert into vehicle_follow_drive2
values ( '5556667777' , 'Frank' );

insert into vehicle_follow_drive2
values ( '8889990000' , 'Georgia' );

insert into vehicle_follow_drive2
values ( '1113332222' , 'Helen' );

insert into vehicle_follow_drive2
values ( '3334445555' , 'Irene' );

insert into vehicle_follow_drive2
values ( '6667778888' , 'Jack' );

insert into route2
values ( '1' , '10' );

insert into route2
values ( '2' , '20' );

insert into route2
values ( '3' , '30' );

insert into route2
values ( '4' , '40' );

insert into route2
values ( '5' , '50' );

insert into route1
values ( '41' , '1' );

insert into route1
values ( '49' , '2' );

insert into route1
values ( '68' , '3' );

insert into route1
values ( '84' , '4' );

insert into route1
values ( '99' , '5' );

insert into route1
values ( '300' , '1' );

insert into route1
values ( '301' , '2' );

insert into route1
values ( '302' , '3' );

insert into route1
values ( '303' , '4' );

insert into route1
values ( '304' , '5' );

insert into vehicle_follow_drive1
values ( '10' , '50' , '41' , '101' , '1112223333' , 'A10' );

insert into vehicle_follow_drive1
values ( '11' , '60' , '49' , '102' , '4445556666' , 'B10' );

insert into vehicle_follow_drive1
values ( '12' , '30' , '68' , '103' , '7778889999' , 'C10' );

insert into vehicle_follow_drive1
values ( '13' , '80' , '84' , '104' , '1110002222' , 'D10' );

insert into vehicle_follow_drive1
values ( '14' , '80' , '99' , '105' , '2223334444' , 'F10' );

insert into vehicle_follow_drive1
values ( '20' , '100' , '300' , '201' , '5556667777' , 'H10' );

insert into vehicle_follow_drive1
values ( '21' , '110' , '301' , '202' , '8889990000' , 'G10' );

insert into vehicle_follow_drive1
values ( '22' , '120' , '302' , '203' , '1113332222' , 'H10' );

insert into vehicle_follow_drive1
values ( '23' , '130' , '303' , '204' , '3334445555' , 'I10' );

insert into vehicle_follow_drive1
values ( '24' , '140' , '304' , '205' , '6667778888' , 'J10' );

insert into stop_at
values ( '1' , '10' , '12:00' );

insert into stop_at
values ( '2' , '11' , '11:00' );

insert into stop_at
values ( '3' , '12' , '09:00' );

insert into stop_at
values ( '4' , '13' , '08:05' );

insert into stop_at
values ( '5' , '14' , '14:26' );

insert into port
values ( '6' , '1' , 'wavy' );

insert into port
values ( '7' , '2' , 'water' );

insert into port
values ( '8' , '1' , 'gage' );

insert into port
values ( '9' , '2' , 'white' );

insert into port
values ( '10' , '1' , 'rockie' );

insert into card
values ( '1000' , '100' , '110' );

insert into card
values ( '2000' , '101.1' , '120' );

insert into card
values ( '3000' , '102.2' , '130' );

insert into card
values ( '4000' , '103.3' , '140' );

insert into card
values ( '5000' , '104.4' , '150' );

insert into card
values ( '0001' , '12.5' , '160' );

insert into card
values ( '0002' , '15.6' , '170' );

insert into card
values ( '0003' , '155.9' , '180' );

insert into card
values ( '0004' , '987' , '190' );

insert into card
values ( '0005' , '100.8' , '140' );

insert into concession
values ( '0001' , '0.7' );

insert into concession
values ( '0002' , '0.8' );

insert into concession
values ( '0003' , '0.5' );

insert into concession
values ( '0004' , '0.9' );

insert into concession
values ( '0005' , '0.5' );

insert into bus
values ( '10' , '10000' );

insert into bus
values ( '11' , '20000' );

insert into bus
values ( '12' , '30000' );

insert into bus
values ( '13' , '40000' );

insert into bus
values ( '14' , '50000' );

insert into ferry2
values ( '1' , '100' );

insert into ferry2
values ( '2' , '100' );

insert into ferry2
values ( '3' , '150' );

insert into ferry2
values ( '4' , '150' );

insert into ferry2
values ( '5' , '200' );

insert into ferry1
values ( '20' , '1' );

insert into ferry1
values ( '21' , '2' );

insert into ferry1
values ( '22' , '3' );

insert into ferry1
values ( '23' , '4' );

insert into ferry1
values ( '24' , '5' );

insert into obtain_schedule
values ( '1' , '41' , '2020-01-01' , '26-FEB-20 8:00:00' );

insert into obtain_schedule
values ( '3' , '49' , '2020-01-02' , '26-FEB-20 9:00:00' );

insert into obtain_schedule
values ( '5' , '68' , '2020-01-03' , '26-FEB-20 10:00:00' );

insert into obtain_schedule
values ( '7' , '84' , '2020-01-04' , '26-FEB-20 11:00:00' );

insert into obtain_schedule
values ( '9' , '99' , '2020-01-05' , '26-FEB-20 12:00:00' );

insert into passenger_card2
values ( '7780000000' , 'Ace' );

insert into passenger_card2
values ( '7785556666' , 'Bill' );

insert into passenger_card2
values ( '7788889999' , 'Coco' );

insert into passenger_card2
values ( '7789000000' , 'David' );

insert into passenger_card2
values ( '7783334444' , 'Elsa' );

insert into passenger_card1
values ( '501' , '7780000000' , 'aaaaa' , "20", 'aaaaa@ubc.ca' , '100' , '1000' );

insert into passenger_card1
values ( '502' , '7785556666' , 'bbbbb' , "39", 'bbbbb@ubc.ca' , '200' , '2000' );

insert into passenger_card1
values ( '503' , '7788889999' , 'ccccc' , "66", 'ccccc@ubc.ca' , '001' , '0001' );

insert into passenger_card1
values ( '504' , '7789000000' , 'ddddd' , "28", 'ddddd@ubc.ca' , '300' , '3000' );

insert into passenger_card1
values ( '505' , '7783334444' , 'eeeee' , "10", 'eeeee@ubc.ca' , '002' , '0002' );

insert into passenger_take_vehicle
values ( '501', '26-FEB-20 8:00:00', '2.85', '10');

insert into passenger_take_vehicle
values('502','26-FEB-20 9:00:00', '2.85', '11');

insert into passenger_take_vehicle
values('503', '25-FEB-20 8:00:00', '2.85', '12');

insert into passenger_take_vehicle
values('504', '26-FEB-20 7:00:00', '10', '20');

insert into passenger_take_vehicle
values('505', '26-FEB-20 8:00:00', '10', '21');

insert into passenger_take_vehicle
values('502', '26-FEB-20 7:00:00', '2.85', '10');

insert into passenger_take_vehicle
values('502', '26-FEB-20 3:00:00', '2.85', '12');

insert into passenger_take_vehicle
values('502', '22-FEB-20 3:00:00', '2.85', '13');

insert into passenger_take_vehicle
values('502', '28-FEB-20 3:00:00', '2.85', '14');

insert into passenger_take_vehicle
values('502', '26-FEB-20 11:00:00', '2.85', '20');

insert into passenger_take_vehicle
values('502', '29-FEB-20 10:00:00', '2.85', '21');

insert into passenger_take_vehicle
values('502', '27-FEB-20 5:00:00', '2.85', '22');

insert into passenger_take_vehicle
values('502', '26-MAR-20 8:00:00', '2.85', '23');

insert into passenger_take_vehicle
values('502', '20-FEB-20 9:00:00', '2.85', '24');