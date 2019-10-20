
 --- 编号自动添加表
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

--- 触发器 船号自动在插入和+1
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


--查询表
select* from COMPANY;
select* from SHIP;
select* from TRANSPORT_ORDER;
select* from CONSTRUCT_ORDER;
select* from MANAGER;

--删除表

drop table CONSTRUCT_ORDER;
drop table TRANSPORT_ORDER;
drop table SHIP;
drop table MANAGER; 
drop table COMPANY;



--创建企业表

create table Company(
    Company_id       Varchar2(16) primary key,    --企业编号 6位
    Company_name     Varchar2(50) not null,       --企业名称
    Company_tel      Varchar2(16) ,               --企业电话 11位
    Company_address  Varchar2(99) ,               --企业地址
    Company_type     Varchar2(50)                 --企业类型 只有四种
                     check(Company_type in('供应企业','运输企业','建造企业','网站','政府')),
    Company_state    Varchar2(50)                 --企业年审状态/上市状态/审核状态 false代表未审核 official代表内部 其余代表上市公司
);

insert into Company values('GY0001','苏宁供应公司','32000035248','中国南京','供应企业','非常好');
insert into Company values('GY000X','苏宁供应公司','11100352482','中国武汉','供应企业','非常好');
insert into Company values('YS0001','顺丰运输公司','95338953380','中国广东','运输企业','很好');
insert into Company values('JZ0001','中国建筑建造公司','10107888123','中国北京','建造企业','好');
insert into Company values('GY0002','美的供应公司','00033335248','中国广东','供应企业','好');
insert into Company values('YS0002','京东运输公司','40060655000','中国北京','运输企业','很好');
insert into Company values('YS0010','青岛运输公司','44260655022','中国山东','运输企业','false');
insert into Company values('JZ0002','中国中铁建造公司','12345678987','中国武汉','建造企业','良');
insert into Company values('GY0003','美团供应公司','10107888123','中国北京','供应企业','出现事故');
insert into Company values('YS0003','中通运输公司','95311953111','中国上海','运输企业','好');
insert into Company values('JZ0003','中国新时代建造公司','14345678987','中国郑州','建造企业','false');
insert into Company values('GY0004','格力供应公司','40083653151','中国广东','供应企业','false');
insert into Company values('YS0004','韵达运输公司','95546955460','中国上海','运输企业','好');
insert into Company values('JZ0004','中国联合建造公司','33348978987','中国石家庄','建造企业','非常好');
insert into Company values('WZ0001','湖北汽车工业学院','13872631673','中国湖北','网站','official');
insert into Company values('ZF0001','湖北省十堰市政府','13872164544','湖北十堰','政府','official');



--创建船舶表
create table Ship(
    Ship_id              Varchar2(16) Primary key,   --船舶编号 6位
    Ship_name            Varchar2(50) Not null,      --船舶名称
    Ship_type            Varchar2(50) Not null,      --船舶类型
    Ship_load            number,                     --船舶载重 单位 吨
    Ship_weight          number,                     --船舶吨位 单位 吨
    Construct_Company_id Varchar2(16) ,              --建造企业编号 6位
    Owner_Company_id     Varchar2(16) ,              --所属企业编号 6位
    Ship_construct_date  Varchar2(50) ,              --船舶建造日期 8位
    Ship_value           number,                     --船舶当前价值 万元
    Ship_state           Varchar2(50) ,              --船舶年审状态
    constraint ship_c_c_id foreign key (Construct_Company_id) references Company(Company_id),
    constraint ship_o_c_id foreign key (Owner_Company_id) references Company(Company_id)
);

insert into ship values ('S00001','启航号','轻工运输船',10000,2000,'JZ0001','YS0001','20190501',200,'良');
insert into ship values ('S00002','东风号','重工运输船',20000,4000,'JZ0001','YS0002','20190621',300,'优');
insert into ship values ('S00003','济南号','食品运输船',15000,3000,'JZ0003','YS0001','20190620',150,'良');
insert into ship values ('S00004','长城号','材料运输船',12000,6000,'JZ0002','YS0002','20190619',120,'良');
insert into ship values ('S00005','辽宁号','水产运输船',50000,5500,'JZ0004','YS0004','20190622',400,'优');
insert into ship values ('S00006','友谊号','食品运输船',60000,8000,'JZ0002','YS0003','20190623',400,'中');
insert into ship values ('S00007','远望号','水产运输船',10000,5200,'JZ0003','YS0001','20190624',400,'良');
insert into ship values ('S00008','向阳号','食品运输船',20000,5000,'JZ0004','YS0004','20190625',400,'良');
insert into ship values ('S00011','渤海号','轻工运输船',10000,2000,'JZ0001','YS0001','20190626',200,'较差');
insert into ship values ('S00012','观海号','重工运输船',20000,4000,'JZ0001','YS0002','20190627',300,'良');
insert into ship values ('S00013','远征号','食品运输船',15000,3000,'JZ0003','YS0001','20190628',150,'出现事故');
insert into ship values ('S00014','成功号','材料运输船',12000,6000,'JZ0002','YS0002','20190618',120,'良');
insert into ship values ('S00015','德尔号','水产运输船',50000,5500,'JZ0004','YS0004','20190629',400,'良');
insert into ship values ('S00016','康泽号','食品运输船',60000,8000,'JZ0002','YS0003','20190630',400,'中');
insert into ship values ('S00017','速度号','食品运输船',61000,6600,'JZ0004','YS0004','20190630',100,'良');



--创建运输订单表
create table Transport_order( 
    Transport_order_id       Varchar2(20) Primary key,   --运输订单编号 20位
    Ship_id                  Varchar2(16) ,              --船舶编号 6位
    Order_Company_id         Varchar2(16) ,              --购单企业编号 6位
    Transport_Company_id     Varchar2(16) ,              --运输企业编号 6位
    Transport_Order_date     Varchar2(50) ,              --运输订单日期 8位
    Transport_start          Varchar2(50) ,              --运输起点
    Transport_order_state    Varchar2(50) ,              --订单受理状态
    Transport_order_deal     Varchar2(50) ,              --订单交易状态 / 订单评价
    Transport_value          Number,                     --运输总价
    constraint TO_s_id foreign key (Ship_id) references ship(ship_id),
    constraint TO_O_c_id foreign key (Order_Company_id) references Company(Company_id),
    constraint TO_T_c_id foreign key (Transport_Company_id) references Company(Company_id)
);



insert into TRANSPORT_ORDER values('YS2019061111052201','S00001','GY0003','YS0001','20190601','北京','未审理','等待交易',500);
insert into TRANSPORT_ORDER values('YS2019051212112406','S00001','GY0003','YS0001','20190625','上海','未审理','等待交易',522);
insert into TRANSPORT_ORDER values('YS2019041110062103','S00004','GY0001','YS0002','20190602','深圳','未审理','等待交易',500);
insert into TRANSPORT_ORDER values('YS2019041109072204','S00005','GY0002','YS0004','20190103','成都','未审理','等待交易',500);
insert into TRANSPORT_ORDER values('YS2019061108072105','S00001','GY0001','YS0001','20190111','宁波','已审理','交易成功',400);
insert into TRANSPORT_ORDER values('YS2019051207084306','S00001','GY0003','YS0001','20190423','杭州','已审理','交易成功',500);
insert into TRANSPORT_ORDER values('YS2019041106323507','S00004','GY0001','YS0002','20190918','深圳','未审理','等待交易',110);
insert into TRANSPORT_ORDER values('YS2019041105322108','S00005','GY0001','YS0001','20190526','北京','未审理','等待交易',500);
insert into TRANSPORT_ORDER values('YS2019061104212409','S00001','GY0004','YS0001','20190017','上海','已审理','交易成功',500);
insert into TRANSPORT_ORDER values('YS2019051203433210','S00001','GY0002','YS0001','20190319','郑州','已审理','交易成功',500);
insert into TRANSPORT_ORDER values('YS2019041102343511','S00004','GY0003','YS0002','20190620','十堰','已审理','运输故障',520);
insert into TRANSPORT_ORDER values('YS2019041101233412','S00005','GY0004','YS0004','20190602','武汉','未审理','等待交易',500);
insert into TRANSPORT_ORDER values('YS2019061100321213','S00001','GY0003','YS0001','20190601','长沙','已审理','运输故障',300);
insert into TRANSPORT_ORDER values('YS2019051205214314','S00001','GY0001','YS0002','20190606','重庆','未审理','等待交易',500);
insert into TRANSPORT_ORDER values('YS2019041102431215','S00004','GY0001','YS0002','20190605','深圳','已审理','等待交易',333);
insert into TRANSPORT_ORDER values('YS2019041101323256','S00005','GY0002','YS0003','20191111','天津','已审理','交易成功',211);
insert into TRANSPORT_ORDER values('YS2019041107343236','S00003','GY0002','YS0004','20190511','天津','未审理','等待交易',311);
insert into TRANSPORT_ORDER values('YS2019041107312256','S00003','GY0003','YS0004','20190121','天津','已审理','等待交易',911);
insert into TRANSPORT_ORDER values('YS2019041106353206','S00006','GY0001','YS0003','20190510','天津','未审理','等待交易',150);
insert into TRANSPORT_ORDER values('YS2019041107313216','S00007','GY0004','YS0002','20190601','天津','未审理','等待交易',111);


--创建建造订单表 / 维修表
create table Construct_order( 
    Construct_order_id       Varchar2(20) Primary key,   --建造订单编号 12位
    Construct_Company_id     Varchar2(16) ,              --建造企业编号 6位
    Order_Company_id         Varchar2(16) ,              --购单企业编号 6位
    Construct_order_date     Varchar2(50) ,              --建造订单日期 8位
    Ship_type                Varchar2(50) Not null,      --船舶类型
    Construct_value          number       ,              --建造总价 万元
    Construct_order_state    Varchar2(50) ,              --订单受理状态
    Construct_order_deal     Varchar2(50) ,              --订单交易状态 评价
    constraint CO_c_c_id foreign key (Construct_Company_id) references Company(Company_id),
    constraint CO_o_c_id foreign key (Order_Company_id) references Company(Company_id)
);


insert into CONSTRUCT_ORDER values('JZ2018061100213401','JZ0001','YS0002','20190114','轻工业运输船',1000,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018051101324402','JZ0002','YS0003','20190211','重工业运输船',3000,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018041102232403','JZ0001','YS0001','20190312','轻工业运输船',1500,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018061103434304','JZ0001','YS0001','20190115','轻工业运输船',1000,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018051204323202','JZ0002','YS0003','20190212','重工业运输船',3000,'已审理','交易成功');
insert into CONSTRUCT_ORDER values('JZ2018041305323306','JZ0001','YS0002','20190313','轻工业运输船',1500,'已审理','交易成功');
insert into CONSTRUCT_ORDER values('JZ2018062506322201','JZ0002','YS0003','20190116','轻工业运输船',1000,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018051507213302','JZ0002','YS0001','20190213','重工业运输船',3000,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018041608434203','JZ0003','YS0002','20190314','轻工业运输船',1500,'已审理','交易成功');
insert into CONSTRUCT_ORDER values('JZ2018060509212301','JZ0001','YS0004','20190117','轻工业运输船',1000,'已审理','交易成功');
insert into CONSTRUCT_ORDER values('JZ2018051110232508','JZ0004','YS0003','20190214','重工业运输船',3000,'已审理','运输故障');
insert into CONSTRUCT_ORDER values('JZ2018041211324113','JZ0001','YS0002','20190315','轻工业运输船',1050,'未审理','等待交易');
insert into CONSTRUCT_ORDER values('JZ2018060102323211','JZ0004','YS0004','20190118','轻工业运输船',1000,'已审理','建造超时');
insert into CONSTRUCT_ORDER values('JZ2018050106213222','JZ0002','YS0001','20190215','重工业运输船',3000,'已审理','交易成功');
insert into CONSTRUCT_ORDER values('JZ2018042103432113','JZ0004','YS0001','20190316','轻工业运输船',1500,'已审理','等待交易');
    


--创建管理员表
create table Manager(
   Manager_id             Varchar2(16)  Primary key,   --管理员账号
   Manager_password       Varchar2(32)  Not null,      --管理员密码
   Manager_Company_id     Varchar2(16)  ,              --当前账号所属企业编号 6位
   Manager_admin_type     Varchar2(16)  ,              --当前企业管理员类型
   Manager_state          Varchar2(16)                 --账号审理状态
);

insert into manager values('gyjl01','21232f297a57a5a743894a0e4a801fc3','GY0001','经理','通过');
insert into manager values('gyjl02','21232f297a57a5a743894a0e4a801fc3','GY0002','经理','通过');
insert into manager values('gyjl03','21232f297a57a5a743894a0e4a801fc3','GY0003','经理','通过');
insert into manager values('gyzy01','21232f297a57a5a743894a0e4a801fc3','GY0001','职员','通过');
insert into manager values('ysjl01','21232f297a57a5a743894a0e4a801fc3','YS0001','经理','通过');
insert into manager values('ysjl02','21232f297a57a5a743894a0e4a801fc3','YS0002','经理','待审');
insert into manager values('ysjl03','21232f297a57a5a743894a0e4a801fc3','YS0003','经理','通过');
insert into manager values('ysjl04','21232f297a57a5a743894a0e4a801fc3','YS0004','经理','通过');
insert into manager values('jzjl01','21232f297a57a5a743894a0e4a801fc3','JZ0001','经理','通过');
insert into manager values('wzjl01','21232f297a57a5a743894a0e4a801fc3','WZ0001','经理','通过');
insert into manager values('Seacit','21232f297a57a5a743894a0e4a801fc3','WZ0001','经理','通过');
insert into manager values('zfjl01','21232f297a57a5a743894a0e4a801fc3','ZF0001','经理','通过');



--举报/文章表

create table Message(
   Message_id varchar2(33)      primary key ,     --内容 编码 随机32位
   Message_name varchar2(16)    ,                 --内容 撰写人 可以匿名
   Message_type varchar2(16)    ,                 --内容 类型 可以是举报 也可以是资讯
   Message_Date varchar2(16)    ,                 --内容 时间
   Message_title varchar2(99)   ,                 --内容 主题
   Message_detail varchar2(2000),                 --内容 详细内容
   Message_state varchar2(99)                     --内容 审核状态
);


insert into Message values('a123','匿名','举报','2019-05-11','某公司超载','某时期某公司的某艘船在某次订单中超载了','待审');
insert into Message values('a124','思之声','资讯','2019-05-20','阿萨德飒沓','某时期某公司的某艘船在某次订单中超载了','待审');
insert into Message values('a125','匿名','举报','2019-11-21','某公司犯法','某时期某公司的某艘船在某次订单中犯法了','通过');


--从企业类型获取所有相同企业的信息
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
  system.get_company_by_type_proc('运输企业',company);
  loop
    fetch company into company_id_p,company_name_p,company_tel_p,company_address_p,company_type_p,company_state_p;
    exit when company%notfound;
    dbms_output.put_line(company_id_p);
    dbms_output.put_line(company_state_p);
  end loop;
end;


-- 输入企业ID 获取当前企业的所有船舶 需要提前判断企业的类型
create or replace procedure get_ship_by_company_id_proc(
       p_company_id in company.company_id%type, p_ship out sys_refcursor)
as
  p_company_type system.company.company_type%type;
begin 
  select company_type into p_company_type from company where company_id = p_company_id;
  
  if p_company_type = '运输企业' then
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




-- 输入企业ID 判断当前企业类型 获得当前企业类型的建造订单
create or replace procedure get_const_order_by_compId_proc(
       p_company_id in company.company_id%type, p_construct_order out sys_refcursor)
as
  p_company_type system.company.company_type%type;
begin 
  select company_type into p_company_type from company where company_id = p_company_id;
  
  if p_company_type = '运输企业' then
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


-- 输入企业ID 获得当前企业类型的运输订单
create or replace procedure get_trans_order_by_compId_proc(
       p_company_id in company.company_id%type,p_transport_order out sys_refcursor)
as
  p_company_type system.company.company_type%type;
begin 
  select company_type into p_company_type from company where company_id = p_company_id;
  
  if p_company_type = '供应企业' then
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
