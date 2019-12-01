#include "lib_es.h"


FICHIER *ouvrir(char *nom,char mode){
	//TODO : possibilité de mettre plusieurs mode à l'ouverture du fichier
	int descripteur;
	if(mode == 'r'){
		descripteur = open(nom,O_RDONLY);
	}
	else if(mode == 'w'){
		descripteur = open(nom, O_WRONLY | O_TRUNC | O_CREAT);
	}
	else {
		return NULL;
	}

	if(descripteur == -1){
		return NULL;
	}
	FICHIER *f = (FICHIER*)malloc(sizeof(FICHIER));
	f->df = descripteur;
	f->mode = mode;
	f->indice = 0;

	char *t = (char*)malloc(sizeof(char)*SIZE);
	f->tampon = t;

	return f;
}

int fermer(FICHIER *f){
	if(f->indice != 0){
		write(f->df, (void*)f->tampon, f->indice);
	}
	int val_retour = close(f->df);
	free(f->tampon);
	free(f);
	return val_retour;
}

int lire(void *p, unsigned int taille, unsigned int nbelem, FICHIER *f){
	int taille_totale_p = taille*nbelem;
	int taille_restante = SIZE-f->indice;
	if (taille_totale_p < taille_restante){
		memcpy(p,f->tampon+f->indice,taille_totale_p);
		f->indice += taille_totale_p;
		return taille_totale_p;
	}else {
		memcpy(p,f->tampon+f->indice,taille_restante);
		f->indice =0;
		ssize_t res = read(f->df,p,SIZE);
		return taille_restante;
	}
}

int ecrire(void *p, unsigned int taille, unsigned int nbelem, FICHIER *f){
	int taille_totale_p = taille*nbelem;
	if(taille_totale_p < SIZE-f->indice){
		//on ne peut pas tout ecrire dans le buffer
		memcpy(f->tampon+f->indice,p,taille_totale_p);
		f->indice += taille_totale_p;
		return taille_totale_p;
	}else {
		int nbElemEcrire = (SIZE-f->indice)/taille;
		memcpy(f->tampon,p,nbElemEcrire*taille);
		f->indice += nbElemEcrire*taille;
		int res = write(f->df, (void*)f->tampon, f->indice);
		f->indice = 0;
		return res;
	}
}
