
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
   /* A COMPLETER */
        return NULL;
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
   /* A COMPLETER */

   
}

//-------------------------------------------------------------
// Stratégies d'allocation
//-------------------------------------------------------------
struct fb* mem_first_fit(struct fb* head, size_t size) {
   /* A COMPLETER */
    return NULL;
}
//-------------------------------------------------------------
struct fb* mem_best_fit(struct fb* head, size_t size) {
   /* A COMPLETER */
    return NULL;

}
//-------------------------------------------------------------
struct fb* mem_worst_fit(struct fb* head, size_t size) {
   /* A COMPLETER */
    return NULL;
}
