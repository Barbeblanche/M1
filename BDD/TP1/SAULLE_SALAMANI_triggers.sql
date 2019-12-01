Create or replace trigger question1
After update on Lesgardiens
For each row
Begin
	insert into LesHistoiresAff values (Sysdate(),:old.nocage,:old.nome);
End;
/

Create or replace trigger question2
Before insert or update on LesAnimaux
For each row
Declare
	fct varchar2(20);
Begin
	select fonction into fct from LesCages where LesCages.nocage=:new.nocage;
	if fct != :new.fonction_cage then raise_application_error(-20101, 'Fonction incompatible!');
	end if;
End;
/

Create or replace trigger question3
After insert or update on LesAnimaux
Declare
	nb Integer;
Begin
	select count(*) into nb
	from LesAnimaux a inner join LesCages c on (a.nocage=c.nocage)
	where c.nocage not in (select nocage from lesgardiens);
	if nb>0 then raise_application_error(-20101, 'Cage non gardee!');
	end if;
End;
/

Create or replace trigger question4
After update or delete on Lesgardiens
Declare
	nb Integer;
Begin
	select count(*) into nb
	from lescages
	where lescages.nocage in (select nocage from lesanimaux) and lescages.nocage not in (select nocage from lesgardiens);
	if nb>0 then raise_application_error(-20101, 'Cage non vide qui ne peux pas etre non gardee!');
	end if;
End;
/
