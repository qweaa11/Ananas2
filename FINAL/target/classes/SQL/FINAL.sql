select count(*)
		from member
		where memberid = 'silano06'
        
select *
from member;
    
delete from member
where idx = 1;

update member set birth = '19860904'
where idx = 2;

commit;

























