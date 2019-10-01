
#include "mem.h"
#include "mem_os.h"
#include "common.h"
#include <stdio.h>


//-------------------------------------------------------------
// mem_init
//-------------------------------------------------------------
void mem_init() {
   /* A COMPLETER */

   // Le début de la mémoire corrrespond à tête de la liste chaînée des zones libres
   struct fb** pfz = (struct fb**)get_memory_adr();
   *pfz = (struct fb*)((char *)get_memory_adr() + sizeof(struct fb*));

   // le reste de la mémoire est une zone libre
   (*pfz)->size = get_memory_size() - sizeof(struct fb*);
   (*pfz)->next = NULL ;
}

//-------------------------------------------------------------
// mem_alloc
//-------------------------------------------------------------
void* mem_alloc(size_t size) {
   struct fb **pfz = (struct fb**)get_memory_adr();
   return mem_first_fit(*pfz, size);
}

//-------------------------------------------------------------
// mem_free
//-------------------------------------------------------------
void mem_free(void* zone) {
   /* A COMPLETER */
}

//-------------------------------------------------------------
// Itérateur(parcours) sur le contenu de l'allocateur
// mem_show
//-------------------------------------------------------------
void mem_show(void (*print)(void *, size_t, int free)) {
   /* A COMPLETER */
}

//-------------------------------------------------------------
// mem_fit
//-------------------------------------------------------------
void mem_fit(mem_fit_function_t* mff) {



}

//-------------------------------------------------------------
// Stratégies d'allocation
//-------------------------------------------------------------
struct fb* mem_first_fit(struct fb* head, size_t size) {
   /* A COMPLETER */

   if(head == NULL) {
	   return NULL;
   }

   size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

   struct fb *temp, *zone_libre;
   struct fb* adresse_allouee = NULL;
   temp = head;
   while(temp->next != NULL){
	   if(temp->next->size >= size){
		   if(temp->next->size - size >= sizeof(struct fb*)){
			   adresse_allouee = temp->next;
			   zone_libre = temp->next + size;
			   zone_libre->size = temp->next->size - size;
			   zone_libre->next = temp->next->next;
			   temp->next = zone_libre;
			   break;
		   }
		   else {
			   adresse_allouee = temp->next;
			   temp->next = temp->next->next;
			   break;
		   }
	   }
	   else{
		   temp = temp->next;
	   }
   }
   return adresse_allouee;
}

//-------------------------------------------------------------
struct fb* mem_best_fit(struct fb* head, size_t size) {
	if(head == NULL) {
 	   return NULL;
    }

    size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

    struct fb *temp, *zone_libre;
    struct fb* adresse_allouee = NULL;
	struct fb* prev_best_fit = NULL;
    temp = head;
    while(temp->next != NULL){
 	   if(temp->next->size >= size){
 		   if ((best_fit == NULL) || (prev_best_fit->next->size > temp->next->size)){
 			   prev_best_fit = temp;
 		   }
		   else if (prev_best_fit->next->size == size){
			   break;
		   }
 	   }
 	   else{
 		   temp = temp->next;
 	   }
    }
	if (prev_best_fit != NULL){
		if(best_fit->size - size >= sizeof(struct fb*)){
			adresse_allouee = prev_best_fit->next;
			zone_libre = prev_best_fit->next + size;
			zone_libre->size = temp->next->size - size;
			zone_libre->next = prev_best_fit->next->next;
			best_fit = zone_libre;
		}
		else {
			adresse_allouee = best_fit;
		}
	}

    return adresse_allouee;

}
//-------------------------------------------------------------
struct fb* mem_worst_fit(struct fb* head, size_t size) {
	if(head == NULL) {
 	  return NULL;
    }

    size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

    struct fb *temp, *zone_libre;
    struct fb* adresse_allouee = NULL;
    struct fb* worst_fit = NULL;
    temp = head;
    while(temp->next != NULL){
 	  if(temp->next->size >= size){
 		  if ((worst_fit == NULL) || (worst_fit->size < temp->next->size)){
 			  worst_fit = temp->next;
 		  }
 	  }
 	  else{
 		  temp = temp->next;
 	  }
    }
    if (worst_fit != NULL){
 	   if(worst_fit->size - size >= sizeof(struct fb*)){
 		   adresse_allouee = worst_fit;
 		   zone_libre = worst_fit + size;
		   zone_libre->size = temp->next->size - size;
 		   zone_libre->next = worst_fit->next;
 		   worst_fit = zone_libre;
 	   }
 	   else {
 		   adresse_allouee = worst_fit;
 	   }
    }

    return adresse_allouee;
}
