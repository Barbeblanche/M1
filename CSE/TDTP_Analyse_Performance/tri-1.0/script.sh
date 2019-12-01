#!/usr/bin/env bash


for ((i=0; i<100; i++));
do
	for ((j=100; j<10000; j*10));
	do
		./creer_vecteur -s j > vecteur.in
		echo $i $j >> tri_sequence.in
		./tri_sequentiel -t < vecteur.in >>
