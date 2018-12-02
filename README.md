# internet_banking
cource project for distributed system
### role:
* 0 - main cus
* 1 - phu cus
* 2 - banker

### SQL create example

insert into user (id, username, password, role, gender, account_ac_no) values
(1, 'thangnd197', 123456, 2, 1, ),
(2, 'thang', 123456, 1, 1, 1),
(3, 'thangnd', 123456, 0, 1, 1);

insert into account (ac_no, balance, open_date) values
(1, 1111111, '2018-10-12'),
(2, 2222222, '2018-09-01'),
(3, 3333333, '2018-05-30');