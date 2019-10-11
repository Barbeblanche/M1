#include <stdio.h>

int search(int *vect,int taille,int debut,int x){
	int trouve = 0;
	while ((debut <taille) && (trouve != 1)){
		if (vect[debut]==x){
			trouve = 1;
		}else {
			debut ++;
		}
	}
	return trouve;
}

/*int* fabrique_vect(char* nom_fich){
	FILE* f = fopen(nom_fich, 'r');


	if(f == NULL){
		exit(1);
	}
	int i;
	fscanf(f," %d",&i);
}*/

int main (void){

	int vecteur[10];
	for (int i=0;i<10;i++){
		vecteur[i] = i;
	}
	int val = 5;
	if (search(vecteur, 10, 0,val)){
		printf("La valeur %d a été trouvée\n",val );
	}else {
		printf("La valeur %d n'a pas été trouvée\n",val );
	}
	return 0;


}
