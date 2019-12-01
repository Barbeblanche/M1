#include <stdio.h>
#include "lib_es.h"

int main (){
	FICHIER *f;
	f = ouvrir("exemple.txt",'r');
	
	printf("%d\n",f->df);
	char * p = malloc(sizeof(char)*50);
	lire(p,)

	//ecrire("bonjour okdfgdfpokog1\n",1,22,f);
	//ecrire("tyuiopi sdfsdfsdfsdf1\n",1,22,f);
	fermer(f);
	return 0;
}
