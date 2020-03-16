insert into chores(name) values ('mosogatás')
insert into chores(name) values ('kutyasétáltatás')
insert into chores(name) values ('porszívózás')
insert into chores(name) values ('ablakpucolás')
insert into chores(name) values ('bevásárlás')
insert into chores(name) values ('vasalás')

insert into users(name,email,password,role) values('anya','anya@anya.com','$2a$10$c/.qCnqvl7plMmDpIkn.tuT9BaNvEwdifDvK0naj.unS3FNL0d5.a','ADMIN')
insert into users(name,email,password,role) values('apa','apa@apa.com','$2a$10$c/.qCnqvl7plMmDpIkn.tuT9BaNvEwdifDvK0naj.unS3FNL0d5.a','USER')
insert into users(name,email,password,role) values('bori','bori@bori.com','$2a$10$c/.qCnqvl7plMmDpIkn.tuT9BaNvEwdifDvK0naj.unS3FNL0d5.a','USER')
insert into users(name,email,password,role) values('pisti','pisti@pisti.com','$2a$10$c/.qCnqvl7plMmDpIkn.tuT9BaNvEwdifDvK0naj.unS3FNL0d5.a','USER')

insert into my_group(id) values(1)
insert into my_group(id) values(2)
insert into my_group(id) values(3)

insert into to_dos(id,user,chores,from_date,to_date,group_id,ready)values(1,'anya@anya.com','mosogatas',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),1,FALSE)
insert into to_dos(id,user,chores,from_date,to_date,group_id,ready)values(2,'apa@apa.com','porszívózás',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),1,FALSE)
insert into to_dos(id,user,chores,from_date,to_date,group_id,ready)values(3,'bori@bori.com','vasalás',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),1,FALSE)
insert into to_dos(id,user,chores,from_date,to_date,group_id,ready)values(4,'pisti@pisti.com','ablakpucolás',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),2,FALSE)
insert into to_dos(id,user,chores,from_date,to_date,group_id,ready)values(5,'pisti@pisti.com','bevásárlás',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),2,FALSE)
insert into to_dos(id,user,chores,from_date,to_date,group_id,ready)values(6,'bori@bori.com','kutyasétáltatás',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP(),2,FALSE)

insert into users_groups(users_email,groups_id)values('anya@anya.com',1)
insert into users_groups(users_email,groups_id)values('apa@apa.com',1)
insert into users_groups(users_email,groups_id)values('bori@bori.com',1)
insert into users_groups(users_email,groups_id)values('bori@bori.com',2)
insert into users_groups(users_email,groups_id)values('pisti@pisti.com',1)
insert into users_groups(users_email,groups_id)values('pisti@pisti.com',2)