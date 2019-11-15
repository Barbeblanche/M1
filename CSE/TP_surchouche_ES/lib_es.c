#include lib_es

FICHIER *ouvrir(char *nom,char mode){
	mode_t m;

	if(mode == "r"){
		m = O_RDONLY;
	}
	else if(mode == "w"){
		m = O_WRONLY;
	}
	else {
		return NULL;
	}

	int descriteur = open(nom,m);
	if(descripteur == -1){
		return NULL;
	}
	FICHIER *f = (FICHIER*)malloc(sizeof(FICHIER));
	f->df = descriteur;
	f->mode = mode;
	f->nb_octets_ecrits = 0;

	char *t = (char*)malloc(sizeof(char)*SIZE);
	f->tampon = t;

	return f;
}

int fermer(FICHIER *f){
	if(f->nb_octets_ecrits != 0){
		write(f->df, (void*)f->tampon, nb_octets_ecrits);
	}
	int val_retour = close(f->df);
	free(f->tampon);
	free(f);
	return val_retour;
}

int lire(void *p, unsigned int taille, unsigned int nbelem, FICHIER *f){

}

int ecrire(void *p, unsigned int taille, unsigned int nbelem, FICHIER *f){
	ssize_t taille_ecrite;

	int i = 0;
	if(memcpy(f->tampon, p, ))
	if(nb_octets_ecrits == SIZE){
		taille_ecrite = write(f->df, (void*)f->tampon, nb_octets_ecrits);
		if(taille_ecrite == SIZE){
			f->df = 0;
			return i;
		}
	}
}
