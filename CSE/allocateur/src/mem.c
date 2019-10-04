
#include "mem.h"
#include "mem_os.h"
#include "common.h"
#include <stdio.h>

#define ALIGN 8

//-------------------------------------------------------------
// get_pzl
//-------------------------------------------------------------
/*struct fb *get_pzl(){
   struct fb** pzl = (struct fb**)((char*)get_memory_adr() + sizeof(mem_fit_function_t *));    // adresse du pointeur de la première zone libre
   *pzl = (struct fb*)((char *)get_memory_adr() + sizeof(mem_fit_function_t *) + sizeof(struct fb*));    // on fait pointer pzl vers la première zone libre
   return *pzl;
}*/


//-------------------------------------------------------------
// mem_init
//-------------------------------------------------------------
void mem_init() {
   global_s *variables = (global_s *)get_memory_adr();

   // Le début de la mémoire est un pointeur vers la fonction de recherche
   mem_fit(mem_first_fit);

   // Le deuxième élément est le pointeur vers la 1ere zone libre
   variables->pzl = (struct fb*)((char *)get_memory_adr() + sizeof(global_s));

   // le reste de la mémoire est une zone libre
   variables->pzl->size = get_memory_size() - sizeof(struct fb*);
   variables->pzl->next = NULL ;
}

//-------------------------------------------------------------
// mem_alloc
//-------------------------------------------------------------

/*void modif_chainage(struct fb *pzl, struct bb* debut_adr_alloc, struct fb* prochaine_zl){
	struct fb* tmp = pzl;
    struct bb *debut_adr_alloc;

	if (pzl == (struct fb*)debut_adr_alloc){
		pzl = prochaine_zl;
	}else {
		while (tmp != NULL){
			if (tmp->next == (struct fb*)debut_adr_alloc){
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
   int reste = size % ALIGN;
   if( reste != 0){
	   size += ALIGN - reste;
   }
   struct fb *debut_adr_libre = variables->fonction_alloc(variables->pzl, size);


   if(debut_adr_libre == NULL){
	   return NULL;
   }
   struct fb* tmp = variables->pzl;
   struct bb *debut_adr_alloc;
   debut_adr_alloc = (struct bb*) debut_adr_libre;
   debut_adr_alloc->size = size + sizeof(struct bb*);

   if(debut_adr_libre->size >= size + sizeof(struct fb*)){
	   debut_adr_libre = (struct fb*)((char *)debut_adr_libre + debut_adr_alloc->size);
	   //modif_chainage(variables->pzl, debut_adr_alloc, debut_adr_libre);
	   if (variables->pzl == (struct fb*)debut_adr_alloc){
		   variables->pzl = debut_adr_libre;
	   }else {
		   while (tmp != NULL){
			   if (tmp->next == (struct fb*)debut_adr_alloc){
				   tmp->next = debut_adr_libre;
			   }
			   else {
				   tmp = tmp->next;
			   }
		   }
	   }
   }
   else{
	   //modif_chainage(variables->pzl, debut_adr_alloc, debut_adr_libre);
	   if (variables->pzl == (struct fb*)debut_adr_alloc){
		   variables->pzl = variables->pzl->next;
	   }else {
		   while (tmp != NULL){
			   if (tmp->next == (struct fb*)debut_adr_alloc){
				   tmp->next = tmp->next->next;
			   }
			   else {
				   tmp = tmp->next;
			   }
		   }
	   }
   }


   return (void *)((char *)debut_adr_alloc + sizeof(struct bb));
   //return (void *)(debut_adr_alloc + 1);

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
  /*struct fb *head = get_pzl();
   while (head->next !=NULL);*/
   //global_s *variables = (global_s *)get_memory_adr();
   int est_libre;
   void *adr_mem = (void *)((char *)get_memory_adr() + sizeof(struct global_s));
   //struct fb* tmp = variables->pzl;
   while ((size_t)adr_mem < get_memory_size()){
	   if(tmp == NULL){
		   est_libre = false;
	   }
	   if((struct fb*)adr_mem == tmp){	// zone libre
		   est_libre = true;

	   }
	   else {
		   est_libre = false;

	   }
	   /*if (tmp->next != NULL){
		   struct bb* zone_occup = (struct bb*)((char *)tmp + tmp->size)
	   }*/
   }
   //(*print)(variables->pzl,)
}

//-------------------------------------------------------------
// mem_fit
//-------------------------------------------------------------
void mem_fit(mem_fit_function_t* mff) {
	 global_s *variables = (global_s *)get_memory_adr();
	 variables->fonction_alloc = mff;
}

//-------------------------------------------------------------
// Stratégies d'allocation
//-------------------------------------------------------------
struct fb* mem_first_fit(struct fb* head, size_t size) {
   /* A COMPLETER */

   if(head == NULL) {
	   return NULL;
   }

  // size += sizeof(struct bb*); 		// taille totale : size + taille d'une structure bb

   struct fb *temp;
   struct fb* adresse_allouee = NULL;
   temp = head;
   while(temp != NULL){
	   if(temp->size >= size + sizeof(struct bb*)){
	      adresse_allouee = temp;
		  break;
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
 		   if ((prev_best_fit->next == NULL) || (prev_best_fit->next->size > temp->next->size)){
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
		if(prev_best_fit->next->size - size >= sizeof(struct fb*)){
			adresse_allouee = prev_best_fit->next;
			zone_libre = prev_best_fit->next + size;
			zone_libre->size = temp->next->size - size;
			zone_libre->next = prev_best_fit->next->next;
			prev_best_fit->next = zone_libre;
		}
		else {
			adresse_allouee = prev_best_fit->next;
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
