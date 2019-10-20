
 --- ����Զ���ӱ�
drop table tableNumbers

select * from tableNumbers

create table tableNumbers(
tableNumName varchar2(32) primary key,
tableNumNumber int,
tableOther varchar2(100)
)

insert into tableNumbers values( 'Ship',100 , 'none');
insert into tableNumbers values( 'Company',100 , 'none');
insert into tableNumbers values( 'ShipNumbers',100 , 'none');
insert into tableNumbers values( 'monsterNumbers',100 , 'none');

--- ������ �����Զ��ڲ����+1
create or replace trigger tri_ship_insert
       after insert on ship
for each row 
  
declare
       old_ship_id tableNumbers.Tablenumnumber%type;
       new_ship_id tableNumbers.Tablenumnumber%type;
begin
       select tableNumbers.Tablenumnumber into old_ship_id from tableNumbers where tableNumbers.Tablenumname = 'Ship' ;
       new_ship_id := old_ship_id + 1;
       update tableNumbers set tableNumbers.Tablenumnumber = new_ship_id where tableNumbers.Tablenumname = 'Ship';
end;


create or replace trigger tri_company_insert
       after insert on Company
for each row 
  
declare
       old_company_id tableNumbers.Tablenumnumber%type;
       new_company_id tableNumbers.Tablenumnumber%type;
begin
       select tableNumbers.Tablenumnumber into old_company_id from tableNumbers where tableNumbers.Tablenumname = 'Company' ;
       new_company_id := old_company_id + 1;
       update tableNumbers set tableNumbers.Tablenumnumber = new_company_id where tableNumbers.Tablenumname = 'Company';
end;


--��ѯ��
select* from COMPANY;
select* from SHIP;
select* from TRANSPORT_ORDER;
select* from CONSTRUCT_ORDER;
select* from MANAGER;

--ɾ����

drop table CONSTRUCT_ORDER;
drop table TRANSPORT_ORDER;
drop table SHIP;
drop table MANAGER; 
drop table COMPANY;



--������ҵ��

create table Company(
    Company_id       Varchar2(16) primary key,    --��ҵ��� 6λ
    Company_name     Varchar2(50) not null,       --��ҵ����
    Company_tel      Varchar2(16) ,               --��ҵ�绰 11λ
    Company_address  Varchar2(99) ,               --��ҵ��ַ
    Company_type     Varchar2(50)                 --��ҵ���� ֻ������
                     check(Company_type in('��Ӧ��ҵ','������ҵ','������ҵ','��վ','����')),
    Company_state    Varchar2(50)                 --��ҵ����״̬/����״̬/���״̬ false����δ��� official�����ڲ� ����������й�˾
);

insert into Company values('GY0001','������Ӧ��˾','32000035248','�й��Ͼ�','��Ӧ��ҵ','�ǳ���');
insert into Company values('GY000X','������Ӧ��˾','11100352482','�й��人','��Ӧ��ҵ','�ǳ���');
insert into Company values('YS0001','˳�����乫˾','95338953380','�й��㶫','������ҵ','�ܺ�');
insert into Company values('JZ0001','�й��������칫˾','10107888123','�й�����','������ҵ','��');
insert into Company values('GY0002','���Ĺ�Ӧ��˾','00033335248','�й��㶫','��Ӧ��ҵ','��');
insert into Company values('YS0002','�������乫˾','40060655000','�й�����','������ҵ','�ܺ�');
insert into Company values('YS0010','�ൺ���乫˾','44260655022','�й�ɽ��','������ҵ','false');
insert into Company values('JZ0002','�й��������칫˾','12345678987','�й��人','������ҵ','��');
insert into Company values('GY0003','���Ź�Ӧ��˾','10107888123','�й�����','��Ӧ��ҵ','�����¹�');
insert into Company values('YS0003','��ͨ���乫˾','95311953111','�й��Ϻ�','������ҵ','��');
insert into Company values('JZ0003','�й���ʱ�����칫˾','14345678987','�й�֣��','������ҵ','false');
insert into Company values('GY0004','������Ӧ��˾','40083653151','�й��㶫','��Ӧ��ҵ','false');
insert into Company values('YS0004','�ϴ����乫˾','95546955460','�й��Ϻ�','������ҵ','��');
insert into Company values('JZ0004','�й����Ͻ��칫˾','33348978987','�й�ʯ��ׯ','������ҵ','�ǳ���');
insert into Company values('WZ0001','����������ҵѧԺ','13872631673','�й�����','��վ','official');
insert into Company values('ZF0001','����ʡʮ��������','13872164544','����ʮ��','����','official');



--����������
create table Ship(
    Ship_id              Varchar2(16) Primary key,   --������� 6λ
    Ship_name            Varchar2(50) Not null,      --��������
    Ship_type            Varchar2(50) Not null,      --��������
    Ship_load            number,                     --�������� ��λ ��
    Ship_weight          number,                     --������λ ��λ ��
    Construct_Company_id Varchar2(16) ,              --������ҵ��� 6λ
    Owner_Company_id     Varchar2(16) ,              --������ҵ��� 6λ
    Ship_construct_date  Varchar2(50) ,              --������������ 8λ
    Ship_value           number,                     --������ǰ��ֵ ��Ԫ
    Ship_state           Varchar2(50) ,              --��������״̬
    constraint ship_c_c_id foreign key (Construct_Company_id) references Company(Company_id),
    constraint ship_o_c_id foreign key (Owner_Company_id) references Company(Company_id)
);

insert into ship values ('S00001','������','�Ṥ���䴬',10000,2000,'JZ0001','YS0001','20190501',200,'��');
insert into ship values ('S00002','�����','�ع����䴬',20000,4000,'JZ0001','YS0002','20190621',300,'��');
insert into ship values ('S00003','���Ϻ�','ʳƷ���䴬',15000,3000,'JZ0003','YS0001','20190620',150,'��');
insert into ship values ('S00004','���Ǻ�','�������䴬',12000,6000,'JZ0002','YS0002','20190619',120,'��');
insert into ship values ('S00005','������','ˮ�����䴬',50000,5500,'JZ0004','YS0004','20190622',400,'��');
insert into ship values ('S00006','�����','ʳƷ���䴬',60000,8000,'JZ0002','YS0003','20190623',400,'��');
insert into ship values ('S00007','Զ����','ˮ�����䴬',10000,5200,'JZ0003','YS0001','20190624',400,'��');
insert into ship values ('S00008','������','ʳƷ���䴬',20000,5000,'JZ0004','YS0004','20190625',400,'��');
insert into ship values ('S00011','������','�Ṥ���䴬',10000,2000,'JZ0001','YS0001','20190626',200,'�ϲ�');
insert into ship values ('S00012','�ۺ���','�ع����䴬',20000,4000,'JZ0001','YS0002','20190627',300,'��');
insert into ship values ('S00013','Զ����','ʳƷ���䴬',15000,3000,'JZ0003','YS0001','20190628',150,'�����¹�');
insert into ship values ('S00014','�ɹ���','�������䴬',12000,6000,'JZ0002','YS0002','20190618',120,'��');
insert into ship values ('S00015','�¶���','ˮ�����䴬',50000,5500,'JZ0004','YS0004','20190629',400,'��');
insert into ship values ('S00016','�����','ʳƷ���䴬',60000,8000,'JZ0002','YS0003','20190630',400,'��');
insert into ship values ('S00017','�ٶȺ�','ʳƷ���䴬',61000,6600,'JZ0004','YS0004','20190630',100,'��');



--�������䶩����
create table Transport_order( 
    Transport_order_id       Varchar2(20) Primary key,   --���䶩����� 20λ
    Ship_id                  Varchar2(16) ,              --������� 6λ
    Order_Company_id         Varchar2(16) ,              --������ҵ��� 6λ
    Transport_Company_id     Varchar2(16) ,              --������ҵ��� 6λ
    Transport_Order_date     Varchar2(50) ,              --���䶩������ 8λ
    Transport_start          Varchar2(50) ,              --�������
    Transport_order_state    Varchar2(50) ,              --��������״̬
    Transport_order_deal     Varchar2(50) ,              --��������״̬ / ��������
    Transport_value          Number,                     --�����ܼ�
    constraint TO_s_id foreign key (Ship_id) references ship(ship_id),
    constraint TO_O_c_id foreign key (Order_Company_id) references Company(Company_id),
    constraint TO_T_c_id foreign key (Transport_Company_id) references Company(Company_id)
);



insert into TRANSPORT_ORDER values('YS2019061111052201','S00001','GY0003','YS0001','20190601','����','δ����','�ȴ�����',500);
insert into TRANSPORT_ORDER values('YS2019051212112406','S00001','GY0003','YS0001','20190625','�Ϻ�','δ����','�ȴ�����',522);
insert into TRANSPORT_ORDER values('YS2019041110062103','S00004','GY0001','YS0002','20190602','����','δ����','�ȴ�����',500);
insert into TRANSPORT_ORDER values('YS2019041109072204','S00005','GY0002','YS0004','20190103','�ɶ�','δ����','�ȴ�����',500);
insert into TRANSPORT_ORDER values('YS2019061108072105','S00001','GY0001','YS0001','20190111','����','������','���׳ɹ�',400);
insert into TRANSPORT_ORDER values('YS2019051207084306','S00001','GY0003','YS0001','20190423','����','������','���׳ɹ�',500);
insert into TRANSPORT_ORDER values('YS2019041106323507','S00004','GY0001','YS0002','20190918','����','δ����','�ȴ�����',110);
insert into TRANSPORT_ORDER values('YS2019041105322108','S00005','GY0001','YS0001','20190526','����','δ����','�ȴ�����',500);
insert into TRANSPORT_ORDER values('YS2019061104212409','S00001','GY0004','YS0001','20190017','�Ϻ�','������','���׳ɹ�',500);
insert into TRANSPORT_ORDER values('YS2019051203433210','S00001','GY0002','YS0001','20190319','֣��','������','���׳ɹ�',500);
insert into TRANSPORT_ORDER values('YS2019041102343511','S00004','GY0003','YS0002','20190620','ʮ��','������','�������',520);
insert into TRANSPORT_ORDER values('YS2019041101233412','S00005','GY0004','YS0004','20190602','�人','δ����','�ȴ�����',500);
insert into TRANSPORT_ORDER values('YS2019061100321213','S00001','GY0003','YS0001','20190601','��ɳ','������','�������',300);
insert into TRANSPORT_ORDER values('YS2019051205214314','S00001','GY0001','YS0002','20190606','����','δ����','�ȴ�����',500);
insert into TRANSPORT_ORDER values('YS2019041102431215','S00004','GY0001','YS0002','20190605','����','������','�ȴ�����',333);
insert into TRANSPORT_ORDER values('YS2019041101323256','S00005','GY0002','YS0003','20191111','���','������','���׳ɹ�',211);
insert into TRANSPORT_ORDER values('YS2019041107343236','S00003','GY0002','YS0004','20190511','���','δ����','�ȴ�����',311);
insert into TRANSPORT_ORDER values('YS2019041107312256','S00003','GY0003','YS0004','20190121','���','������','�ȴ�����',911);
insert into TRANSPORT_ORDER values('YS2019041106353206','S00006','GY0001','YS0003','20190510','���','δ����','�ȴ�����',150);
insert into TRANSPORT_ORDER values('YS2019041107313216','S00007','GY0004','YS0002','20190601','���','δ����','�ȴ�����',111);


--�������충���� / ά�ޱ�
create table Construct_order( 
    Construct_order_id       Varchar2(20) Primary key,   --���충����� 12λ
    Construct_Company_id     Varchar2(16) ,              --������ҵ��� 6λ
    Order_Company_id         Varchar2(16) ,              --������ҵ��� 6λ
    Construct_order_date     Varchar2(50) ,              --���충������ 8λ
    Ship_type                Varchar2(50) Not null,      --��������
    Construct_value          number       ,              --�����ܼ� ��Ԫ
    Construct_order_state    Varchar2(50) ,              --��������״̬
    Construct_order_deal     Varchar2(50) ,              --��������״̬ ����
    constraint CO_c_c_id foreign key (Construct_Company_id) references Company(Company_id),
    constraint CO_o_c_id foreign key (Order_Company_id) references Company(Company_id)
);


insert into CONSTRUCT_ORDER values('JZ2018061100213401','JZ0001','YS0002','20190114','�Ṥҵ���䴬',1000,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018051101324402','JZ0002','YS0003','20190211','�ع�ҵ���䴬',3000,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018041102232403','JZ0001','YS0001','20190312','�Ṥҵ���䴬',1500,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018061103434304','JZ0001','YS0001','20190115','�Ṥҵ���䴬',1000,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018051204323202','JZ0002','YS0003','20190212','�ع�ҵ���䴬',3000,'������','���׳ɹ�');
insert into CONSTRUCT_ORDER values('JZ2018041305323306','JZ0001','YS0002','20190313','�Ṥҵ���䴬',1500,'������','���׳ɹ�');
insert into CONSTRUCT_ORDER values('JZ2018062506322201','JZ0002','YS0003','20190116','�Ṥҵ���䴬',1000,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018051507213302','JZ0002','YS0001','20190213','�ع�ҵ���䴬',3000,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018041608434203','JZ0003','YS0002','20190314','�Ṥҵ���䴬',1500,'������','���׳ɹ�');
insert into CONSTRUCT_ORDER values('JZ2018060509212301','JZ0001','YS0004','20190117','�Ṥҵ���䴬',1000,'������','���׳ɹ�');
insert into CONSTRUCT_ORDER values('JZ2018051110232508','JZ0004','YS0003','20190214','�ع�ҵ���䴬',3000,'������','�������');
insert into CONSTRUCT_ORDER values('JZ2018041211324113','JZ0001','YS0002','20190315','�Ṥҵ���䴬',1050,'δ����','�ȴ�����');
insert into CONSTRUCT_ORDER values('JZ2018060102323211','JZ0004','YS0004','20190118','�Ṥҵ���䴬',1000,'������','���쳬ʱ');
insert into CONSTRUCT_ORDER values('JZ2018050106213222','JZ0002','YS0001','20190215','�ع�ҵ���䴬',3000,'������','���׳ɹ�');
insert into CONSTRUCT_ORDER values('JZ2018042103432113','JZ0004','YS0001','20190316','�Ṥҵ���䴬',1500,'������','�ȴ�����');
    


--��������Ա��
create table Manager(
   Manager_id             Varchar2(16)  Primary key,   --����Ա�˺�
   Manager_password       Varchar2(32)  Not null,      --����Ա����
   Manager_Company_id     Varchar2(16)  ,              --��ǰ�˺�������ҵ��� 6λ
   Manager_admin_type     Varchar2(16)  ,              --��ǰ��ҵ����Ա����
   Manager_state          Varchar2(16)                 --�˺�����״̬
);

insert into manager values('gyjl01','21232f297a57a5a743894a0e4a801fc3','GY0001','����','ͨ��');
insert into manager values('gyjl02','21232f297a57a5a743894a0e4a801fc3','GY0002','����','ͨ��');
insert into manager values('gyjl03','21232f297a57a5a743894a0e4a801fc3','GY0003','����','ͨ��');
insert into manager values('gyzy01','21232f297a57a5a743894a0e4a801fc3','GY0001','ְԱ','ͨ��');
insert into manager values('ysjl01','21232f297a57a5a743894a0e4a801fc3','YS0001','����','ͨ��');
insert into manager values('ysjl02','21232f297a57a5a743894a0e4a801fc3','YS0002','����','����');
insert into manager values('ysjl03','21232f297a57a5a743894a0e4a801fc3','YS0003','����','ͨ��');
insert into manager values('ysjl04','21232f297a57a5a743894a0e4a801fc3','YS0004','����','ͨ��');
insert into manager values('jzjl01','21232f297a57a5a743894a0e4a801fc3','JZ0001','����','ͨ��');
insert into manager values('wzjl01','21232f297a57a5a743894a0e4a801fc3','WZ0001','����','ͨ��');
insert into manager values('Seacit','21232f297a57a5a743894a0e4a801fc3','WZ0001','����','ͨ��');
insert into manager values('zfjl01','21232f297a57a5a743894a0e4a801fc3','ZF0001','����','ͨ��');



--�ٱ�/���±�

create table Message(
   Message_id varchar2(33)      primary key ,     --���� ���� ���32λ
   Message_name varchar2(16)    ,                 --���� ׫д�� ��������
   Message_type varchar2(16)    ,                 --���� ���� �����Ǿٱ� Ҳ��������Ѷ
   Message_Date varchar2(16)    ,                 --���� ʱ��
   Message_title varchar2(99)   ,                 --���� ����
   Message_detail varchar2(2000),                 --���� ��ϸ����
   Message_state varchar2(99)                     --���� ���״̬
);


insert into Message values('a123','����','�ٱ�','2019-05-11','ĳ��˾����','ĳʱ��ĳ��˾��ĳ�Ҵ���ĳ�ζ����г�����','����');
insert into Message values('a124','˼֮��','��Ѷ','2019-05-20','��������','ĳʱ��ĳ��˾��ĳ�Ҵ���ĳ�ζ����г�����','����');
insert into Message values('a125','����','�ٱ�','2019-11-21','ĳ��˾����','ĳʱ��ĳ��˾��ĳ�Ҵ���ĳ�ζ����з�����','ͨ��');


--����ҵ���ͻ�ȡ������ͬ��ҵ����Ϣ
create or replace procedure get_company_by_type_proc(
       p_company_type in company.company_type%type, conpany out sys_refcursor)
as

begin 
  open conpany for select company.company_id,company.company_name,company.company_tel,company.company_address
  ,company.company_type, company.company_state from company where company.company_type = p_company_type;
end;


declare
  company sys_refcursor;
  company_id_p system.company.company_id%type;
  company_name_p system.company.company_name%type;
  company_tel_p system.company.company_tel%type;
  company_address_p system.company.company_address%type;
  company_type_p system.company.company_type%type;
  company_state_p system.company.company_state%type;
begin
  system.get_company_by_type_proc('������ҵ',company);
  loop
    fetch company into company_id_p,company_name_p,company_tel_p,company_address_p,company_type_p,company_state_p;
    exit when company%notfound;
    dbms_output.put_line(company_id_p);
    dbms_output.put_line(company_state_p);
  end loop;
end;


-- ������ҵID ��ȡ��ǰ��ҵ�����д��� ��Ҫ��ǰ�ж���ҵ������
create or replace procedure get_ship_by_company_id_proc(
       p_company_id in company.company_id%type, p_ship out sys_refcursor)
as
  p_company_type system.company.company_type%type;
begin 
  select company_type into p_company_type from company where company_id = p_company_id;
  
  if p_company_type = '������ҵ' then
     open p_ship for select ship.ship_id, ship.ship_name, ship.ship_type, ship.ship_load,
          ship.ship_weight, ship.construct_company_id, ship.owner_company_id,
          ship.ship_construct_date, ship.ship_value , ship.ship_state 
     from ship where ship.owner_company_id = p_company_id;
  else
     open p_ship for select ship.ship_id, ship.ship_name, ship.ship_type, ship.ship_load,
          ship.ship_weight, ship.construct_company_id, ship.owner_company_id,
          ship.ship_construct_date, ship.ship_value , ship.ship_state 
     from ship where ship.construct_company_id = p_company_id;
  end if;
end;

declare
  s_ship sys_refcursor;
  s_id system.ship.ship_id%type;
  s_name system.ship.ship_name%type;
  s_type system.ship.ship_type%type;
  s_load system.ship.ship_load%type;
  s_weight system.ship.ship_weight%type;
  s_cid system.ship.construct_company_id%type;
  s_oid system.ship.owner_company_id%type;
  s_date system.ship.ship_construct_date%type;
  s_value system.ship.ship_value%type;
  s_state system.ship.ship_state%type;
begin
  system.get_ship_by_company_id_proc ('YS0001',s_ship);
  loop
    fetch s_ship into s_id,s_name,s_type,s_load,s_weight,s_cid,s_oid,s_date,s_value, s_state;
    exit when s_ship%notfound;
    dbms_output.put_line(s_id);
    dbms_output.put_line(S_name);
    dbms_output.put_line(s_cid);
    dbms_output.put_line(s_state);
  end loop;
end;




-- ������ҵID �жϵ�ǰ��ҵ���� ��õ�ǰ��ҵ���͵Ľ��충��
create or replace procedure get_const_order_by_compId_proc(
       p_company_id in company.company_id%type, p_construct_order out sys_refcursor)
as
  p_company_type system.company.company_type%type;
begin 
  select company_type into p_company_type from company where company_id = p_company_id;
  
  if p_company_type = '������ҵ' then
     open p_construct_order for select Construct_Order.Construct_Order_Id, 
          Construct_Order.Construct_Company_Id, 
          Construct_Order.Order_Company_Id, 
          Construct_Order.Construct_Order_Date, 
          Construct_Order.Ship_Type,
          Construct_Order.Construct_Value ,
          Construct_Order.Construct_Order_State,
          Construct_Order.Construct_Order_Deal
     from Construct_Order where Construct_Order.Order_Company_Id = p_company_id;
  else
     open p_construct_order for select Construct_Order.Construct_Order_Id, 
          Construct_Order.Construct_Company_Id, 
          Construct_Order.Order_Company_Id,  
          Construct_Order.Construct_Order_Date, 
          Construct_Order.Ship_Type, 
          Construct_Order.Construct_Value ,
          Construct_Order.Construct_Order_State,
          Construct_Order.Construct_Order_Deal
     from Construct_Order where Construct_Order.Construct_Company_Id = p_company_id;
  end if;
end;


declare
  co_Construct_Order sys_refcursor;
  co_coid system.Construct_Order.Construct_Order_Id%type;
  co_ccid system.Construct_Order.Construct_Company_Id%type;
  co_ocid system.Construct_Order.Order_Company_Id%type;
  co_cod system.Construct_Order.Construct_Order_Date%type;
  co_st system.Construct_Order.Ship_Type%type;
  co_cv system.Construct_Order.Construct_Value%type;
  co_cos system.Construct_Order.construct_order_state%type;
  co_codl system.Construct_Order.construct_order_deal%type;
begin
  system.get_const_order_by_compId_proc ('JZ0001',co_Construct_Order);
  loop
    fetch co_Construct_Order into co_coid,co_ccid,co_ocid,co_cod,co_st,co_cv,co_cos,co_codl;
    exit when co_Construct_Order%notfound;
    dbms_output.put_line(co_coid);
    dbms_output.put_line(co_ccid);
    dbms_output.put_line(co_ocid);
    dbms_output.put_line(co_cos);
    dbms_output.put_line(co_codl);
  end loop;
end;


-- ������ҵID ��õ�ǰ��ҵ���͵����䶩��
create or replace procedure get_trans_order_by_compId_proc(
       p_company_id in company.company_id%type,p_transport_order out sys_refcursor)
as
  p_company_type system.company.company_type%type;
begin 
  select company_type into p_company_type from company where company_id = p_company_id;
  
  if p_company_type = '��Ӧ��ҵ' then
     open p_transport_order for select transport_order.transport_order_id,
          transport_order.ship_id, 
          transport_order.order_company_id,
          transport_order.transport_company_id, 
          transport_order.transport_order_date,
          transport_order.transport_start ,
          transport_order.transport_order_state ,
          transport_order.transport_order_deal, 
          transport_order.transport_value
          from Transport_Order where Transport_Order.Order_Company_Id = p_company_id;
  else
     open p_transport_order for select transport_order.transport_order_id,
          transport_order.ship_id, 
          transport_order.order_company_id,
          transport_order.transport_company_id, 
          transport_order.transport_order_date,
          transport_order.transport_start , 
          transport_order.transport_order_state, 
          transport_order.transport_order_deal,
          transport_order.transport_value
     from Transport_Order where Transport_Order.Transport_Company_Id = p_company_id;
  end if;
end;

declare
  to_transport_Order sys_refcursor;
  to_toid system.Transport_Order.transport_order_id%type;
  to_sid system.Transport_Order.ship_id%type;
  to_ocid system.Transport_Order.order_company_id%type;
  to_tcid system.Transport_Order.transport_company_id%type;
  to_tod system.Transport_Order.transport_order_date%type;
  to_ts system.Transport_Order.transport_start%type;
  to_tos  system.Transport_Order.transport_order_state%type;
  to_todl system.Transport_Order.transport_order_deal%type;
  to_tv system.Transport_Order.transport_value%type;

begin
  system.get_trans_order_by_compId_proc ('YS0002',to_transport_Order);
  loop
    fetch to_transport_Order into to_toid,to_sid,to_ocid,to_tcid,to_tod,to_ts,to_tos ,to_todl,to_tv;
    exit when to_transport_Order%notfound;
    dbms_output.put_line(to_toid);
    dbms_output.put_line(to_ocid);
    dbms_output.put_line(to_tcid);
    dbms_output.put_line(to_tos);
    dbms_output.put_line(to_tv);
    dbms_output.put_line(to_todl);
  end loop;
end;


select company.company_type from company where company.Company_id ='WZ0001'
