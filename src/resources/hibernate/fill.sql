INSERT into user(id,balance,registered,email,password,username, role) values (1,100, '2015-02-25 12:50:36','Admin@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','admin', 'ROLE_ADMIN');
INSERT into user(id,balance,registered,email,password,username, role) values (2,50, '2015-02-27 12:50:36','User2@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user1', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (3,50, '2015-02-28 12:50:36','User3@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user2', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (4,50, '2015-03-01 12:50:36','User4@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user3', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (5,50, '2015-03-01 12:50:36','User5@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user4', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (6,50, '2015-03-02 12:50:36','User6@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user5', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (7,50, '2015-03-02 12:50:36','User7@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user6', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (8,50, '2015-03-03 12:50:36','User8@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user7', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (9,50, '2015-03-03 12:50:36','User9@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user8', 'ROLE_USER');
INSERT into user(id,balance,registered,email,password,username, role) values (10,50, '2015-03-03 12:50:36','User10@gmail.com','$2a$10$EiWmbgVJs4gLFVp2Nu58Wu37AqWCKAveh1bNxstFGUms0Bn3Jnaxu','user9', 'ROLE_USER');

INSERT into propose(id,description,fullName,shortName,iconPath,price) values(1,'Largest social network','Vkontakte','vk','vk.png',1.00);
INSERT into propose(id,description,fullName,shortName,iconPath,price) values(2,'Largest social network','Twitter','tw','tw.png',0.90);
INSERT into propose(id,description,fullName,shortName,iconPath,price) values(3,'Largest social network','Instagram','ig','ig.png',0.80);

CREATE VIEW lastRegistered AS select id AS 'id',registered AS 'registered',count(*) AS 'count' from user group by cast(registered as date);

CREATE VIEW lastRequests AS select id AS 'id',finished AS 'finished',sum((case status when 'COMPLETED' then 1 else 0 end)) AS 'done',sum((case status when 'NUMBER_REJECT' then 1 else 0 end)) AS 'fail' from request group by cast(finished as date) ;

CREATE VIEW proposeGeneralStatistic AS select propose.id AS 'id', propose.fullName as 'fullName', count(*) as 'count' from request inner join propose on request.propose_id=propose.id where request.status='COMPLETED' group by request.propose_id;

CREATE VIEW lastServices AS select request.id,started,propose.fullName,count(*) as 'count' from request inner join propose on request.propose_id=propose.id and status='COMPLETED' group by cast(started as Date), propose_id order by started;