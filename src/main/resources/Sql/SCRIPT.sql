-- Script tạo ORACLE_QUEUE queue

begin
dbms_aqadm.create_queue_table(
queue_table=>'sync_data_table'
, queue_payload_type=>'SYS.AQ$_JMS_TEXT_MESSAGE'
,multiple_consumers=>false);
end;
/
begin
DBMS_AQADM.CREATE_QUEUE (
queue_name         => 'sync_data_queue',
queue_table        => 'sync_data_table');
end;
/
begin
DBMS_AQADM.START_QUEUE (
queue_name         => 'sync_data_queue');
end;

GRANT EXECUTE ON DBMS_AQ TO BAMC;
GRANT EXECUTE ON DBMS_AQ_BQVIEW TO BAMC;


