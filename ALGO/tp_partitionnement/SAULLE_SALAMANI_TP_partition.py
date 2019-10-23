# -*-coding:Latin-1 -*

from operator import itemgetter
import numpy as np


N = 9
sim_mat = np.array([[10 ,  6 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0] ,
                    [6 ,  10 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0] ,
                    [0 ,  0 ,  10 ,  5 ,  3 ,  3 ,  1 ,  1 ,  0] ,
                    [0 ,  0 ,  5 ,  10 ,  1 ,  2 ,  1 ,  1 ,  0] ,
                    [0 ,  0 ,  3 ,  1 ,  10 ,  4 ,  1 ,  2 ,  0] ,
                    [0 ,  0 ,  3 ,  2 ,  4 ,  10 ,  1 ,  4 ,  0] ,
                    [0 ,  0 ,  1 ,  1 ,  1 ,  1 ,  10 ,  1 ,  0] ,
                    [0 ,  0 ,  1 ,  1 ,  2 ,  4 ,  1 ,  10 ,  0] ,
                    [0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  10]])


def get_max(P):
    similariteMax = -1
    i1 = -1;
    i2 = -1;
    for i in range(N):
        if(I[i] == 1 and similariteMax < P[i][0][0]):
            similariteMax = P[i][0][0]
            i1 = i
            i2 = P[i][0][1]
    return i1, i2

# Initialisation de l'indicateur de classes actives
I = []
for i in range(N):
    I.append(1)


# Initialisation de la file a priorité des similarités entre docs
P = [0] * N
for i in range(N):
    liste = []
    for j in range(N):
        if(i != j): # la diagonale de la matrice est inutile
            liste.append((sim_mat[i][j],j))
            liste.sort(reverse=True)
    P[i] = liste


# Construction du dendrograme
L = [] # liste fusions

for k in range(N-1):
    (i1,i2) = get_max(P)
    L.append([i1, i2])
    I[i2]=0
    # supprimer S[i1][i2] de P[i1]
    P[i1].pop(0)
    for i in range(N):
        if (I[i]==1) and (i!=i1):
            a_suprimer = []
            j = 0
            for similarite, indice in P[i]: # on determine quels sont les éléments à supprimer
                if (indice == i1 or indice == i2) :
                    a_suprimer.append((similarite,indice))
                j = j+1
            for (similarite,indice) in a_suprimer: # on les supprime
                P[i].remove((similarite,indice))
                if(indice == i1):
                    P[i1].remove((similarite,i))

            # on remet P à jour avec les bonnes distances
            similarite = max(sim_mat[i][i1], sim_mat[i][i2])
            P[i].append((similarite,i1))
            P[i].sort(reverse=True)
            P[i1].append((similarite,i))
            P[i1].sort(reverse=True)

print(L)
