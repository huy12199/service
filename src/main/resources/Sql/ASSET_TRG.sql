CREATE OR REPLACE TRIGGER "TRG_TBPO_AST_T_AFTER"
AFTER INSERT or UPDATE  ON TBPO_AST_T
                           REFERENCING OLD AS OLDVAL NEW AS NEWVAL
                           FOR EACH ROW
declare
eopt   dbms_aq.enqueue_options_t;
  mprop  dbms_aq.message_properties_t;
  message_handle      RAW(200);
  message            SYS.AQ$_JMS_TEXT_MESSAGE;
begin
message := SYS.AQ$_JMS_TEXT_MESSAGE.construct;
    message.set_text( 'ASSET'||':'|| :newval.ID);
       dbms_aq.enqueue(queue_name => 'sync_data_queue',
             enqueue_options      => eopt,
             message_properties   => mprop,
             payload              => message,
             msgid                => message_handle);
exception
  when others then
    DBMS_OUTPUT.PUT_LINE('  --> SQLERRM= ' || SQLERRM);
end;