drop table lesanimaux FORCE;
drop table lesemployes FORCE;
drop table lescages FORCE;
drop type ens_cages FORCE;
drop type ens_maladies FORCE;
drop type ens_gardiens FORCE;
drop type tresponsable;
drop type tgardien;
drop type temploye;
drop type tcage;
drop type tspecialites;

-- ATTENTION: Il est plus prudent d'executer chaque commande de maniere ind�pendante, et pas le script complet
-- => plus facile a debuguer

-- Declarer un type tcage
create type tcage;
/

-- Definir une collection libre (nested table) ens_cages de references sur tcage
create type ens_cages as table of REF tcage;
/

-- Definir une collection statique (Varray) tspecialites de chaines de caracteres (fonction de la cage)
Create type tspecialites as Varray(100) of varchar2(50);
/

-- Definir un type temploye
create type temploye as object(
	nomE varchar2(20),
	adresse varchar2(20),
	fonction_cage tspecialites
)
NOT FINAL;
/

-- Definir les sous-types de temploye: tgardien et trespsonsable
create type tgardien under temploye (liste_cages ens_cages);
/

create type tresponsable under temploye (
	noAllee number(3)
);
/

-- Creation de la table lesemployes (Attention les les collections des classes herites n'ont pas de "container")
create table lesemployes of temploye;

-- Definir une collection libre ens_gardiens de references sur tgardien
create type ens_gardiens as table of REF tgardien;
/

-- Definir le type tcage (dej� declarer au debut)
create type tcage as object (
	noCage number(3),
	fonction varchar2(20),
	noAllee number(3),
	liste_gardiens ens_gardiens
);
/

-- Creation de la table lescages (Les collections de classes "final" possedent des "containers")
create table lescages of tcage
Nested table liste_gardiens Store As lesgardiens;

-- Definir une collection libre ens_maladies de chaine de caracteres (maladie)
create type ens_maladies as table of varchar2(100);
/

-- Creation de la table lesanimaux
create table LesAnimaux (
	nomA varchar2(20),
	sexe varchar2(13),
	type_an varchar2(15),
	fonction_cage varchar2(20),
	pays varchar2(20),
	anNais number(4),
	lacage REF tcage,
	liste_maladies ens_maladies,
	constraint LesAnimaux_C1 primary key (nomA)
)
Nested table liste_maladies Store As lesmaladies;

-- La suite doit etre placee dans un script de peuplement (autre fichier)
purge recyclebin;
commit;
