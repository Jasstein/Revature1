drop user bank cascade;

create user bank
identified by p4ssw0rd
default tablespace users
temporary tablespace temp
quota 10 on users;

grant connect to bank;
grant resource to bank;
grant create session to bank;
grant create table to bank;
grant create view to bank;

create table bank.account(
    account_num number(10) primary key,
    checking_amt number(15,2) not null,
    approved number(1) not null,
    joint number(1) not null
    );

create table bank.customer(
    username varchar2(256) primary key,
    pass varchar2(256) not null,
    account_num number(10) not null
    );
    
create table bank.employee(
    username varchar2(256) primary key,
    pass varchar2(256) not null
    );
    
create table bank.admin(
    username varchar2(256) primary key,
    pass varchar2(256) not null
    );
    
create table bank.auditing(
    auditing number(20) primary key,
    account_num number(10) not null,
    checking_change number(15,2) not null,
    old_approved number(1) not null,
    new_approved number(1) not null,
    old_joint number(1) not null,
    new_joint number(1) not null
);

create sequence bank.audit_key;

create or replace trigger bank.audit_key_trigger
before insert on bank.auditing
for each row
begin
    select bank.audit_key.nextval into :new.auditing from dual;
end;
/

create or replace trigger bank.account_update
before update on bank.account
for each row
declare
    account_n number(10);
    old_num number(15,2);
    new_num number(15,2);
    diff number(15,2);
    old_appr number(1);
    new_appr number(1);
    old_joi number(1);
    new_joi number(1);
begin
    select :old.checking_amt into old_num from dual;
    select :new.checking_amt into new_num from dual;
    select :old.account_num into account_n from dual;
    select :old.approved into old_appr from dual;
    select :old.joint into old_joi from dual;
    select :new.approved into new_appr from dual;
    select :new.joint into new_joi from dual;
    diff := :new.checking_amt - :old.checking_amt;
    insert into bank.auditing (account_num, checking_change, old_approved, old_joint, new_approved, new_joint)
    values (account_n, diff, old_appr, old_joi, new_appr, new_joi);
end;
/

create or replace procedure bank.accountapproval(appr in number, act_num in number)
is
begin
    update bank.account set approved = appr where account_num = act_num;
    commit;
end;
/