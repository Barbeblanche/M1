--A
select a.liste_maladies from lesanimaux a inner join lescages c on a.lacage = ref(c) where c.noAllee=2;

--B
select a.noma, a.type_an from lesanimaux a where liste_maladies = ens_maladies('grippe');


--C
select EP.nome from lesemployes EP where value(EP) is of (tgardien);
select TREAT(VALUE(EP) as tgardien).liste_cages from lesemployes EP where value(EP) is of (tgardien);

--D
select EP.nome 
from lesemployes EP,table(TREAT(VALUE(EP) as tgardien).liste_cages) lc 
where value(EP) is of (tgardien) and deref(VALUE(lc)).nocage=1;

--E
select c.noAllee,c.nocage
from LesCages c, lesEmployes EP, table(TREAT(VALUE(EP) as tgardien).liste_cages) lc
where value(EP) is of (tgardien) 
and deref(VALUE(lc)).nocage = c.nocage
and ep.adresse = 'Calvi';
