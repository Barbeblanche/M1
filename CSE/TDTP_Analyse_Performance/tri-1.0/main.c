#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/time.h>
#include <getopt.h>
#include <sys/resource.h>

#include "algo_principal.h"
#include "temps.h"
#include "commun.h"

void usage(char *commande) {
    fprintf(stderr, "Usage :\n");
    fprintf(stderr, "%s [ --parallelism number ] [ --quiet ] [ --time ] "
                    "[ --rusage ] [ --arg argument ] [ --help ]\n\n",
                    commande);
    fprintf(stderr, "Ce programme lit sur son entree standard un vecteur "
                    "a traiter. Il accepte comme options --parallelism qui "
                    "indique le nombre de threads/processus a creer (un seul "
                    "par defaut), --quiet qui supprime les affichages "
                    "superflus, --time qui affiche le temps total passe "
                    "dans l'algorithme principal, --rusage qui affiche "
                    "le temps d'utilisation des resources attribue aux "
                    "differents threads/processus et --arg qui permet de "
                    "transmettre un argument à l'algorithme execute.\n");
    exit(1);
}

int quiet=0;

int main(int argc, char *argv[]) {
    int opt, parallelism = 1;
    int taille, i, temps = 0, ressources = 0;
    int *tableau;
    char *arg=NULL;

    struct option longopts[] = {
        { "help", required_argument, NULL, 'h' },
        { "parallelism", required_argument, NULL, 'p' },
        { "quiet", no_argument, NULL, 'q' },
        { "time", no_argument, NULL, 't' },
        { "rusage", no_argument, NULL, 'r' },
        { "arg", required_argument, NULL, 'a' },
        { NULL, 0, NULL, 0 }
    };

    while ((opt = getopt_long(argc, argv, "hp:qrta:", longopts, NULL)) != -1) {
        switch (opt) {
          case 'p':
            parallelism = atoi(optarg);
            break;
          case 'q':
            quiet = 1;
            break;
          case 'r':
            ressources = 1;
            break;
          case 't':
            temps = 1;
            break;
          case 'a':
            arg = optarg;
            break;
          case 'h':
          default:
            usage(argv[0]);
        }
    }
    argc -= optind;
    argv += optind;

    //affiche("Saisissez la taille du vecteur\n");
    scanf(" %d", &taille);
    tableau = (int *) malloc(taille*sizeof(int));
    if (tableau == NULL) {
        fprintf(stderr,"Erreur de malloc\n");
        exit(3);
    }
    //affiche("Saisissez tous les elements du vecteur\n");
    for (i=0; i<taille; i++)
        scanf(" %d", &tableau[i]);

    /* Algo */
	struct rusage usage_start, usage_end;
	struct timeval start,end;
	gettimeofday(&start,NULL);
	//getrusage(RUSAGE_SELF, &usage_start);
    algo_principal(parallelism, tableau, taille, arg);
	gettimeofday(&end,NULL);
	//getrusage(RUSAGE_SELF, &usage_end);
	if (temps){
		printf("%lld\n", to_usec(end) - to_usec(start));
	}
	if (ressources){
	    long long int tmp_total_start = to_usec(usage_start.ru_utime) + to_usec(usage_start.ru_stime);
	    long long int tmp_total_end = to_usec(usage_end.ru_utime) + to_usec(usage_end.ru_stime);
	    long long int tmp_total = tmp_total_end-tmp_total_start;

		printf("%lld\n", tmp_total);
	}

    return 0;
}
