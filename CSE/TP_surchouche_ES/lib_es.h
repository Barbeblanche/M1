#ifndef _LIB_ES_
#define _LIB_ES_

#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

#define SIZE 128

typedef struct {
	int df;
	char *tampon;
	int nb_octets_ecrits;
	int mode;
} FICHIER;


FICHIER *ouvrir(char *nom,char mode);

int fermer(FICHIER *f);

int lire(void *p, unsigned int taille, unsigned int nbelem, FICHIER *f);

int ecrire(void *p, unsigned int taille, unsigned int nbelem, FICHIER *f);

#endif
