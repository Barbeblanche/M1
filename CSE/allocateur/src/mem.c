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
   variables->ffz->size = get_memory_size() - sizeof(global_s);
   variables->ffz->next = NULL ;
}

struct fb* add_zone(struct fb* new_zone, struct fb* head){
   
   if(head == NULL){
      head = new_zone;
      new_zone->next = NULL;  // indispensable afin de préserver la mémoire
   }
   else if (head > new_zone){ // première zone libre
      new_zone->next = head;
      head = new_zone;
   }
   else {
      struct fb* tmp = head;
      while (tmp->next != NULL && tmp->next < new_zone){
         tmp = tmp->next;
      }
      new_zone->next = tmp->next;
      tmp->next = new_zone; // tmp ne peut pas être nul avant d'avoir trouvé new_alloc_zone
   }
   return head;
}


//head != NULL car old_zone doit en faire parti
struct fb* suppr_zone(struct fb* old_zone, struct fb* head){
   if (head == old_zone){
      head = head->next;
   }
   else {
      struct fb* tmp = head;
      while (tmp->next != NULL && tmp->next != old_zone){
         tmp = tmp->next;
      }
      tmp->next = tmp->next->next; // tmp ne peut pas être nul avant d'avoir trouvé new_alloc_zone
   }
   return head;
}


//-------------------------------------------------------------
// mem_alloc
//-------------------------------------------------------------
void* mem_alloc(size_t size) {
   global_s *variables = (global_s *)get_memory_adr();

   if(size == 0){
    return NULL;
   }

   // la taille à allouer doit être un multiple de l'alignement
   int rest = size % ALIGN;
   if( rest != 0){
      size += ALIGN - rest;
   }

   size += sizeof(struct bb);  // taille totale : size + taille d'une structure bb

   // on récupère l'adresse de la zone libre en fonction de la stratégie d'allocation
   struct fb *free_zone = variables->alloc_fun(variables->ffz, size);

   if(free_zone == NULL){
      return NULL;
   }

   struct bb* new_alloc_zone;
   size_t free_zone_size = free_zone->size; 

   variables->ffz = suppr_zone(free_zone, variables->ffz);  // il est plus facile de supprimer le maillon, et d'en réinsérer un modifé si nécessaire

   if(free_zone_size >= size + sizeof(struct bb) + sizeof(struct fb)){  // si la zone libre est suffisamment grande, le surplus reste libre

      // initialisation de la zone occupée
      new_alloc_zone = (struct bb*)free_zone;
      new_alloc_zone->size = size + sizeof(struct bb);

      // maj de la zone libre
      free_zone = (struct fb*)((char *)new_alloc_zone + new_alloc_zone->size);
      free_zone->size = free_zone_size - new_alloc_zone->size;

      variables->ffz = add_zone(free_zone, variables->ffz);
   }
   else{  // la zone libre devient une zone occupée
      new_alloc_zone = (struct bb*) free_zone;
      new_alloc_zone->size = free_zone_size;
   }

   return (void *)((char *)new_alloc_zone + sizeof(struct bb));
   //return (void *)(new_alloc_zone + 1);
}

//-------------------------------------------------------------
// mem_realloc
//-------------------------------------------------------------
/*void* mem_realloc(void *ptr, size_t size) {
   global_s *variables = (global_s *)get_memory_adr();

   if(ptr == NULL){
      return mem_alloc(size);
   }

   ptr -= sizeof(struct bb);  // on se place au début de la structure de la zone
<<<<<<< HEAD

   size_t too_much_size = (struct bb*)ptr->size - size;
=======
   size_t too_much_size = (size_t)((char*)((struct bb)ptr->size) - size);
>>>>>>> f99aa8c12e10d57d8497d9c7a4d4149d6a9a3683

   if((struct bb*)ptr->size == 0){
      mem_free(ptr);
      ptr = NULL;  
   }
   else if( ((struct bb)ptr->size - sizeof(struct bb) > size) && (too_much_size >= sizeof(struct fb)) ){  // on veut réduire la taille de ptr, et c'est possible
         
      // maj de la zone occupée
      (struct bb*)ptr->size = size + sizeof(struct bb);

      // identification de la zone à libérer
      size_t size_ptr = (struct bb)ptr->size;
      struct fb* free_zone = (struct fb*)((char*)ptr + size_ptr);
      free_zone->size = too_much_size;

      mem_free((void *)free_zone);

   }
   else {   // on veut agrandir la taille de ptr
/*
      Nous voulions séparer les cas suivants :


         si l’espace mémoire peut être agrandi à droite : 
            il faut actualiser la taille de la zone ; 
            supprimer la zone libre après ; 
            réinsérer une zone libre si l’espace tait suffisamment grand
         si c’est insuffisant  mais que l’espace mémoire peut être aussi (ou seulement)  agrandi à gauche :  il faut actualiser l’adresse de la zone et la taille ;
            copier les anciennes valeurs de la zone dans la nouvelle ;
            supprimer les anciennes zones libres et en ajouter une s’il y a trop d’espace ;
*/         

      // sinon, on appelle mem_alloc avec la nouvelle taille :
 /*     if(new_zone == NULL && (new_zone = mem_alloc(size)) != NULL){  // sinon on essaie de faire une allocation normale
         mem_cpy(new_zone, ptr, sizeof((struct fb*)ptr));
         mem_free(ptr); // l'ancienne zone qu'occupait ptr est à nouveau libre
         ptr = (void *)new_zone; // on récupère la nouvelle adresse
      }
      else {
         // la taille ne peut être agrandie, que faire ?
      }
   }
   return ptr;
}*/


//-------------------------------------------------------------
// merge_zone
//-------------------------------------------------------------
struct fb* merge_zone(struct fb* zone, struct fb* head){
   // head ne peut être null car zone est dedans

   struct fb* tmp = head;

   if(tmp != zone){ // il y peut-être besoin d'une fusion avant
      while(tmp->next != NULL && tmp->next < zone){
         tmp = tmp->next;
      }
      // zone est après tmp et tmp->next
      if((struct fb*)((char*)tmp + tmp->size) == zone){  // fusion avec la zone avant
         int new_size = tmp->size + zone->size;
         tmp->size = new_size;
         tmp->next = zone->next;
         zone = tmp; // au cas où la zone suivante doit fusionner aussi
      }
   }

   if(tmp->next != NULL && ((struct fb*)((char*)zone + zone->size) == tmp->next)){  // fusion avec la zone arrière
      int new_size = zone->size + tmp->next->size;
      zone->size = new_size;
      zone->next = tmp->next->next;
   }
   return head; 
}

//-------------------------------------------------------------
// mem_free
//-------------------------------------------------------------
void mem_free(void* zone) {
   
   zone -= sizeof(struct bb); // on se place au début de la structure de la zone

   if(zone == NULL){
      return;
   }

   global_s *variables = (global_s *)get_memory_adr();

   variables->ffz = add_zone((struct fb*)zone, variables->ffz);
   variables->ffz = merge_zone((struct fb*)zone, variables->ffz);

   zone = NULL;  // doit on couper le lien entre le pointeur et l'ancienne zone pointée ?
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
      else {   // si on est au début d'une zone libre
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