#include "mem.h"
#include "mem_os.h"
#include "common.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#define MAX_ALLOC (1<<10)
#define NB_TESTS 5

//adresse par rapport au début de la mémoire
void* relative_adr(void* adr) {
    return (void*)((char*)adr-(char*)get_memory_adr());
}

void my_free(void **mem) {
	if (*mem != NULL) {
		mem_free(*mem);
		debug("Freed %p\n", relative_adr(*mem));
		*mem = NULL;
	}
}

static void *checked_alloc(size_t s) {
	void *result;

	assert((result = mem_alloc(s)) != NULL);
	debug("Alloced %zu bytes at %p\n", s, relative_adr(result));
	return result;
}

void *alloc_max(size_t estimate) {
    void *result;
    static size_t last = 0;

    while ((result = mem_alloc(estimate)) == NULL) {
        estimate--;
    }
    debug("Alloced %zu bytes at %p\n", estimate, relative_adr(result));
    if (last) {
        // Idempotence test
        assert(estimate == last);
    } else
        last = estimate;
    return result;
}

static void alloc5(void **ptr) {
	ptr[0] = checked_alloc(MAX_ALLOC);
	ptr[1] = checked_alloc(MAX_ALLOC);
	ptr[2] = checked_alloc(MAX_ALLOC);
	ptr[3] = checked_alloc(MAX_ALLOC);
	ptr[4] = alloc_max(get_memory_size() - 4*MAX_ALLOC);
}

static void free5(void **ptr) {
	for (int i=0; i<5; i++) {
		my_free(&ptr[i]);
	}
}

static void test_main(){
  void *ptr[5];

  for (int i=0; i<NB_TESTS; i++) {
		debug("Fusion avant\n");
		alloc5(ptr);
		my_free(&ptr[2]);
		my_free(&ptr[1]);
		ptr[1] = checked_alloc(2*MAX_ALLOC);
		free5(ptr);

		debug("Fusion arrière\n");
		alloc5(ptr);
		my_free(&ptr[1]);
		my_free(&ptr[2]);
		ptr[1] = checked_alloc(2*MAX_ALLOC);
		free5(ptr);

		debug("Fusion avant/arrière\n");
		alloc5(ptr);
		my_free(&ptr[1]);
		my_free(&ptr[3]);
		my_free(&ptr[2]);
		ptr[1] = checked_alloc(3*MAX_ALLOC);
		free5(ptr);
	}
}



int main(int argc, char *argv[]) {


	mem_init();
	fprintf(stderr, "Test réalisant divers cas de fusion (avant, arrière et double\n"
			"Définir DEBUG à la compilation pour avoir une sortie un peu plus verbeuse."
 		"\n");

  test_main();
  debug("// \nChangement de stratégie d'allocation : mem_best_fit\n// \n");
  mem_fit(mem_best_fit);
  test_main();
  debug("// \nChangement de stratégie d'allocation : mem_worst_fit\n// \n");
  mem_fit(mem_worst_fit);
  test_main();
	// TEST OK
    printf("TEST OK!!\n");
	return 0;
}
