INSERT into user_table (id,name,email) values (1,'sharad','sharadbhaswa@lauda.com')
INSERT into user_table (id,name,email) values (2,'bharat','bharat@yooy.com')

INSERT into group_table (id,group_name,group_code,group_owner_id) values (1001,'sharad_warrios','XXX',1)
INSERT into group_table (id,group_name,group_code,group_owner_id) values (1002,'khatri_warriors','KNIT',2)

insert into USER_GROUP_MAPPING_TABLE  (id, group_id, user_id) values (102, 1001, 1)
insert into USER_GROUP_MAPPING_TABLE  (id, group_id, user_id) values (101, 1002, 2)
insert into USER_GROUP_MAPPING_TABLE  (id, group_id, user_id) values (103, 1001, 2)