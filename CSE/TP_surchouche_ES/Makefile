
#all:

#: hello.o main.o
	#gcc -o hello hello.o main.o

test : test.o lib_es.o
	gcc -o test -g test.o lib_es.o

test.o: test.c lib_es.h
	gcc -c test.c -g -W -Wall

lib_es.o: lib_es.c lib_es.h
	gcc -o lib_es.o -g -c lib_es.c -W -Wall

clean:
	rm -rf *.o
