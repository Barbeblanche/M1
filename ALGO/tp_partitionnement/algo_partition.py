from operator import itemgetter
import numpy as np

def sort_P(P):
    sortval = P[:, :, 0] * 9 + P[:, :, 1]
    sortval *= -1  # if you want largest first
    sortedIdx = np.argsort(sortval)
    return P[np.arange(len(P))[:, np.newaxis], sortedIdx]
def suppression_sim_max(P):
    return np.delete(P,0,axis=1)
sim_mat = np.array([[10 ,  6 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0] ,
                    [6 ,  10 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0] ,
                    [0 ,  0 ,  10 ,  5 ,  3 ,  3 ,  1 ,  1 ,  0] ,
                    [0 ,  0 ,  5 ,  10 ,  1 ,  2 ,  1 ,  1 ,  0] ,
                    [0 ,  0 ,  3 ,  1 ,  10 ,  4 ,  1 ,  2 ,  0] ,
                    [0 ,  0 ,  3 ,  2 ,  4 ,  10 ,  1 ,  4 ,  0] ,
                    [0 ,  0 ,  1 ,  1 ,  1 ,  1 ,  10 ,  1 ,  0] ,
                    [0 ,  0 ,  1 ,  1 ,  2 ,  4 ,  1 ,  10 ,  0] ,
                    [0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  10]])

#Initialisation de l'indicateur de classes actives
I = []
for i in range(0,7):
    I.append(1);
#Initialisation de la file a priorité des similarités entre docs
P = np.zeros((9, 9, 2))
for i in range(0,9):
    for j in range(0,9):
        P[i][j] = (sim_mat[i][j],j)

#trier la file
P = sort_P(P)
#suppression de la similarité
P = suppression_sim_max(P)

#Construction du dendrograme
L = [] #liste fusions
for i in range(0,8):
    sim_max = P[i][0][0]
    ind_max = P[i][0][1]
    L.append([sim_max,ind_max])
    suppression_sim_max(P)

