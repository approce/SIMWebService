INSERT into role(id,role) values(1,'ROLE_USER');
INSERT into role(id,role) values(2,'ROLE_ADMIN');

INSERT into user(id,balance,registered,email,password,username) values (1,100, '2015-02-25 12:50:36','Admin@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','admin');
INSERT into user_role(users_id,roles_id) values(1,2);
INSERT into user(id,balance,registered,email,password,username) values (2,50, '2015-02-27 12:50:36','User2@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user1');
INSERT into user_role(users_id,roles_id) values(2,1);
INSERT into user(id,balance,registered,email,password,username) values (3,50, '2015-02-28 12:50:36','User3@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user2');
INSERT into user_role(users_id,roles_id) values(3,1);
INSERT into user(id,balance,registered,email,password,username) values (4,50, '2015-03-01 12:50:36','User4@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user3');
INSERT into user_role(users_id,roles_id) values(4,1);
INSERT into user(id,balance,registered,email,password,username) values (5,50, '2015-03-01 12:50:36','User5@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user4');
INSERT into user_role(users_id,roles_id) values(5,1);
INSERT into user(id,balance,registered,email,password,username) values (6,50, '2015-03-02 12:50:36','User6@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user5');
INSERT into user_role(users_id,roles_id) values(6,1);
INSERT into user(id,balance,registered,email,password,username) values (7,50, '2015-03-02 12:50:36','User7@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user6');
INSERT into user_role(users_id,roles_id) values(7,1);
INSERT into user(id,balance,registered,email,password,username) values (8,50, '2015-03-03 12:50:36','User8@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user7');
INSERT into user_role(users_id,roles_id) values(8,1);
INSERT into user(id,balance,registered,email,password,username) values (9,50, '2015-03-03 12:50:36','User9@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user8');
INSERT into user_role(users_id,roles_id) values(9,1);
INSERT into user(id,balance,registered,email,password,username) values (10,50, '2015-03-03 12:50:36','User10@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user9');
INSERT into user_role(users_id,roles_id) values(10,1);

INSERT into propose(id,description,fullName,shortName,iconPath,price) values(1,'Largest social network','Vkontakte','vk','vk.png',1.00);
INSERT into propose(id,description,fullName,shortName,iconPath,price) values(2,'Largest social network','Twitter','tw','tw.png',0.90);
INSERT into propose(id,description,fullName,shortName,iconPath,price) values(3,'Largest social network','Instagram','ig','ig.png',0.80);

CREATE VIEW lastRegistered AS select id AS 'id',registered AS 'registered',count(*) AS 'count' from user group by cast(registered as date);

CREATE VIEW lastRequests AS select id AS 'id',finished AS 'finished',sum((case status when 'COMPLETED' then 1 else 0 end)) AS 'done',sum((case status when 'NUMBER_REJECT' then 1 else 0 end)) AS 'fail' from request group by cast(finished as date) ;

CREATE VIEW proposeGeneralStatistic AS select propose.id AS 'id', propose.fullName as 'fullName', count(*) as 'count' from request inner join propose on request.propose_id=propose.id where request.status='COMPLETED' group by request.propose_id;

CREATE VIEW lastServices AS select request.id,started,propose.fullName,count(*) as 'count' from request inner join propose on request.propose_id=propose.id and status='COMPLETED' group by cast(started as Date), propose_id order by started;