
#include "mem.h"
#include "mem_os.h"
#include "common.h"
#include <stdio.h>

#define ALIGN 8


//-------------------------------------------------------------
// mem_init
//-------------------------------------------------------------
void mem_init() {
   global_s *variables = (global_s *)get_memory_adr();

   // Le début de la mémoire est un pointeur vers la fonction de recherche
   mem_fit(mem_first_fit);

   // Le deuxième élément est le pointeur vers la 1ere zone libre
   variables->ffz = (struct fb*)((char *)get_memory_adr() + sizeof(global_s));

   // le reste de la mémoire est une zone libre
   variables->ffz->size = get_memory_size() - sizeof(struct fb);
   variables->ffz->next = NULL ;
}

//-------------------------------------------------------------
// mem_alloc
//-------------------------------------------------------------

/*void modif_chainage(struct fb *ffz, struct bb* new_alloc_zone, struct fb* prochaine_zl){
	struct fb* tmp = ffz;
    struct bb *new_alloc_zone;

	if (ffz == (struct fb*)new_alloc_zone){
		ffz = prochaine_zl;
	}else {
		while (tmp != NULL){
			if (tmp->next == (struct fb*)new_alloc_zone){
				tmp->next = prochaine_zl;
			}
			else {
				tmp = tmp->next;
			}
		}
	}
}*/


void* mem_alloc(size_t size) {
   global_s *variables = (global_s *)get_memory_adr();

   // la taille à allouer doit être un multiple de l'alignement
   int rest = size % ALIGN;
   if( rest != 0){
	   size += ALIGN - rest;
   }

   // on récupère l'adresse de la zone libre en fonction de la stratégie d'allocation
   struct fb *free_zone = variables->alloc_fun(variables->ffz, size);

   if(free_zone == NULL){
	   return NULL;
   }
   size_t free_zone_size = free_zone->size;
   struct fb* tmp = variables->ffz; // pour modifier le chaînage des zones libres
   struct bb* new_alloc_zone;
   
   if(free_zone_size > size + sizeof(struct bb) + sizeof(struct fb)){ 
      // si la zone libre est suffisamment grande, on veut garder le suplus comme zone libre

      // création de la zone occupée
      new_alloc_zone = (struct bb*)free_zone; 
      new_alloc_zone->size = size + sizeof(struct bb*);

      // maj de la zone libre
	   free_zone = (struct fb*)((char *)new_alloc_zone + new_alloc_zone->size);
      free_zone->size = free_zone_size - new_alloc_zone->size;

      // maj du chainage des zones libres
	   if (variables->ffz == (struct fb*)new_alloc_zone){  // si la zone libre était la première de la liste
		   variables->ffz = free_zone;
	   }
   	else {
	      // sinon on cherche l'ancienne zone libre (càd new_alloc_zone)
			while (tmp != NULL && tmp->next == (struct fb*)new_alloc_zone){
	         tmp = tmp->next;
			}
	      tmp->next = free_zone; // tmp ne peut pas être nul avant d'avoir trouvé new_alloc_zone
		   }
   }
   else{  // la zone libre devient une zone occupée
      new_alloc_zone = (struct bb*) free_zone;
      new_alloc_zone->size = size + sizeof(struct bb*);

      // maj chaînage
	   if (variables->ffz == (struct fb*)new_alloc_zone){ // première zone libre
		   variables->ffz = variables->ffz->next;
	   }
      else {
         while (tmp != NULL && tmp->next == (struct fb*)new_alloc_zone){
            tmp = tmp->next;
         }
      tmp->next = tmp->next->next; // tmp ne peut pas être nul avant d'avoir trouvé new_alloc_zone
      }
   }

   return (void *)((char *)new_alloc_zone + sizeof(struct bb));
   //return (void *)(new_alloc_zone + 1);
}

//-------------------------------------------------------------
// mem_free
//-------------------------------------------------------------
void mem_free(void* zone) {

}

//-------------------------------------------------------------
// Itérateur(parcours) sur le contenu de l'allocateur
// mem_show
//-------------------------------------------------------------
void mem_show(void (*print)(void *, size_t, int free)) {
   global_s *variables = (global_s *)get_memory_adr();

   int mem_readed = sizeof(global_s);

   struct fb* tmp = variables->ffz;
   int is_free;

   void *adr_mem = (void *)((char *)get_memory_adr() + sizeof(struct global_s));  
   
   while (mem_readed < get_memory_size()){ // tant qu'on a pas exploré toute la mémoire
	   if(tmp == NULL || (struct fb*)adr_mem != tmp){  // si l'adr courante n'est pas une zone libre
			is_free = 0;

       	// appel de la fonction d'affichage
       	(*print)(adr_mem,((struct bb*)adr_mem)->size,is_free);

       	// maj de la taille de la mémoire lue
       	mem_readed += ((struct bb*)adr_mem)->size;

       	// maj de l'adresse courante
       	adr_mem = (void *)((char *)adr_mem + ((struct bb*)adr_mem)->size);
	   }
	   else {	// si on est au début d'une zone libre
			is_free = 1;
           
       	// appel de la fonction d'affichage
       	(*print)(adr_mem,((struct fb*)adr_mem)->size,is_free);

       	// maj de la taille dela mémoire lue
       	mem_readed += ((struct fb*)adr_mem)->size;

       	// maj de l'adresse courante
       	adr_mem = (char *)adr_mem + ((struct fb*)adr_mem)->size;
       	tmp = tmp->next;
	   }
   }
}

//-------------------------------------------------------------
// mem_fit
//-------------------------------------------------------------
void mem_fit(mem_fit_function_t* mff) {
	global_s *variables = (global_s *)get_memory_adr();
	variables->alloc_fun = mff;
}

//-------------------------------------------------------------
// Stratégies d'allocation
//-------------------------------------------------------------
struct fb* mem_first_fit(struct fb* head, size_t size) {	
   
   size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

   struct fb *temp;
   struct fb* first_fit = NULL;
   temp = head;
   while(temp != NULL){
	   if(temp->size >= size){
	      first_fit = temp;
		  	break;
	   }
	   else{
		  	temp = temp->next;
	   }
   }
   return first_fit;
}

//-------------------------------------------------------------
struct fb* mem_best_fit(struct fb* head, size_t size) {
	
	size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

   struct fb *temp;
	struct fb* best_fit = NULL;

   temp = head;
   while(temp != NULL){
 	   if(temp->size >= size){
 		   if ((best_fit == NULL) || (best_fit->size > temp->size)){
 			   best_fit = temp;
 		   }
		   else if (best_fit->size == size){
				break;
		   }
 	   }
 	   else{
 		   temp = temp->next;
 	   }
   }
   return best_fit;
}

//-------------------------------------------------------------
struct fb* mem_worst_fit(struct fb* head, size_t size) {

   size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

   struct fb *temp;
   struct fb* worst_fit = NULL;

   temp = head;
   while(temp != NULL){
 	  	if(temp->size >= size){
 		  	if ((worst_fit == NULL) || (worst_fit->size < temp->size)){
 			  	worst_fit = temp;
 		  }
 	  	}
 	  	else{
 		  	temp = temp->next;
 	  	}
   }
   return worst_fit;
}
