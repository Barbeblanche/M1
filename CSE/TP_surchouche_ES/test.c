#include <stdio.h>

int main (){
	char tab[40];
	for( int i = 0; i < 40; i += 1){
		tab[i] = i;
	}
	for( int i = 0; i < 40; i += 1){
		printf("%d\n",tab[i] );
	}
	return 0;
}
