DELETE FROM group_event_log;
DELETE FROM group_params;
UPDATE group_members SET calender_id=NULL;
DELETE FROM group_members;
DELETE FROM meeting_calender;
DELETE FROM ln_installments;
DELETE FROM loan_masters;
DELETE FROM group_balances;
DELETE FROM group_masters;