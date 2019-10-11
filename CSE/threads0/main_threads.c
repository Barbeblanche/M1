#include <stdio.h>
#include <pthread.h>
#include <sys/types.h>
#include <stdlib.h>

typedef struct arg {
	int *vect;
	int debut;
	int fin;
	int x;
	int *trouve;
}arg;

void *search(void *var){
	int debut = ((arg *)var)->debut;
	int fin = ((arg *)var)->fin;
	int x = ((arg *)var)->x;
	int* vect = ((arg *)var)->vect;

	pthread_t tid = pthread_self();
	while ((debut <=fin) && (*((arg *)var)->trouve != 1)){
		if (vect[debut]==x){
			*((arg *)var)->trouve = 1;
			printf("La valeur %d a été trouvée par le thread %x\n",x,(unsigned int) tid);
		}else {
			debut ++;
		}
	}
	if (debut >fin){
		printf("La valeur %d n'a pas été trouvée par le thread %x\n",x,(unsigned int) tid);
	}
	return (void *)tid;
}

/*int* fabrique_vect(char* nom_fich){
	FILE* f = fopen(nom_fich, 'r');


	if(f == NULL){
		exit(1);
	}
	int i;
	fscanf(f," %d",&i);
}*/

int main (int argc,char* argv[]){

	if (argc != 3){
		printf("USAGE : ./main <nb_threads> <val>");
		exit(1);
	}

	int val = atoi(argv[2]);
	int nb_threads = atoi(argv[1]);
	pthread_t *tids;

	tids = (pthread_t*) malloc(sizeof(pthread_t)*nb_threads);

	int vecteur[10];
	int taille = 10;
	for (int i=0;i<taille;i++){
		vecteur[i] = i;
	}

	int *trouve = (int *)malloc(sizeof(int));
	*trouve=0;

	int sous_partie = taille/nb_threads;
	for (int i=0;i<nb_threads;i++){
		arg * argument = (arg*) malloc(sizeof(arg));
		argument->debut = sous_partie * i;
		argument->vect = vecteur;
		argument->x = val;
		argument->trouve = trouve;
		if(i == nb_threads){
			argument->fin = taille;
		}
		pthread_create(&tids[i], NULL, search,argument);
	}
	for (int i=0;i<nb_threads;i++){
		pthread_join(tids[i],NULL);
	}

	return 0;


}
