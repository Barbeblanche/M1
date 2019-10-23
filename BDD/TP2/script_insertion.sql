-- Deja les cages
insert into LesCages values (11 ,  'fauve' , 10,ens_gardiens() );
insert into LesCages values (1 , 'fosse'  , 1,ens_gardiens() );
insert into LesCages values (2 , 'aquarium'  , 1,ens_gardiens() );
insert into LesCages values (3 , 'petits oiseaux' , 2,ens_gardiens() );
insert into LesCages values (4 , 'grand aquarium'  , 1,ens_gardiens() );
insert into LesCages values (12  , 'fauve' , 10,ens_gardiens());

-- Ensuite les employes maintenant que les cages existent
insert into LesEmployes values (Tresponsable('Verdier'  , 'Noumea',Tspecialites('fauve'),10));
insert into LesEmployes values (Tgardien('Spinnard'  , 'Sartene',Tspecialites('fauve'),
	ens_cages(
		(select ref(c) from lescages c where nocage=12),
		(select ref(c) from lescages c where nocage=11)
	)));
insert into LesEmployes values (Tgardien('Labbe' ,    'Calvi',Tspecialites('fauve'),
	ens_cages(
		(select ref(c) from lescages c where nocage=12),
		(select ref(c) from lescages c where nocage=11)
	)));
insert into LesEmployes values (Tgardien('Lachaize' , 'Pointe a Pitre',Tspecialites('fauve','fosse','petis oiseaux'),
	ens_cages(
		(select ref(c) from lescages c where nocage=12),
		(select ref(c) from lescages c where nocage=11),
		(select ref(c) from lescages c where nocage=3),
		(select ref(c) from lescages c where nocage=1)
	)) );
insert into LesEmployes values (Tresponsable('Desmoulins'  , 'Ushuaia',Tspecialites('fauve','fosse','petis oiseaux'),2) );
insert into LesEmployes values (Tresponsable('Jouanot'   , 'Papeete',Tspecialites('fauve','aquarium','grand aquarium'),1) );

-- Mise � jour de liste des gardiens dans chaque cage
insert into Table(select c.liste_gardiens from lescages c where c.nocage=12) value (select TREAT(REF(g) as REF tgardien) FROM LesEmployes g WHERE g.nome in ('Lachaize','Spinnard','Labbe'));
insert into Table(select c.liste_gardiens from lescages c where c.nocage=11) value (select TREAT(REF(g) as REF tgardien) FROM LesEmployes g WHERE g.nome in ('Lachaize','Spinnard','Labbe'));
insert into Table(select c.liste_gardiens from lescages c where c.nocage=1) value (select TREAT(REF(g) as REF tgardien) FROM LesEmployes g WHERE g.nome='Lachaize');
insert into Table(select c.liste_gardiens from lescages c where c.nocage=3) value (select TREAT(REF(g) as REF tgardien) FROM LesEmployes g WHERE g.nome='Lachaize');

-- Et enfin les animaux
insert into LesAnimaux values ('Charly', 'male',   'lion', 'fauve',  'Kenya',  2010,(select ref(c) from lescages c where c.nocage=12),ens_maladies('rage de dents','grippe'));
insert into LesAnimaux values ('Arthur', 'male',   'ours', 'fosse',  'France', 2000,(select ref(c) from lescages c where c.nocage=1),ens_maladies() );
insert into LesAnimaux values ('Chloe',  'femelle', 'pie', 'petits oiseaux' ,  'France', 2011,(select ref(c) from lescages c where c.nocage=3),ens_maladies('grippe'));
insert into LesAnimaux values ('Milou',  'male' ,  'leopard', 'fauve', 'France', 2013,(select ref(c) from lescages c where c.nocage=11),ens_maladies('angine'));
insert into LesAnimaux values ('Tintin', 'male' , 'leopard', 'fauve', 'France', 2013,(select ref(c) from lescages c where c.nocage=11),ens_maladies());
insert into LesAnimaux values ('Charlotte', 'femelle', 'lion',  'fauve',  'Kenya',  2012,(select ref(c) from lescages c where c.nocage=12),ens_maladies());
