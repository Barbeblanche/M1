from operator import itemgetter
import numpy as np


N=9
sim_mat = np.array([[10 ,  6 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0] ,
                    [6 ,  10 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0] ,
                    [0 ,  0 ,  10 ,  5 ,  3 ,  3 ,  1 ,  1 ,  0] ,
                    [0 ,  0 ,  5 ,  10 ,  1 ,  2 ,  1 ,  1 ,  0] ,
                    [0 ,  0 ,  3 ,  1 ,  10 ,  4 ,  1 ,  2 ,  0] ,
                    [0 ,  0 ,  3 ,  2 ,  4 ,  10 ,  1 ,  4 ,  0] ,
                    [0 ,  0 ,  1 ,  1 ,  1 ,  1 ,  10 ,  1 ,  0] ,
                    [0 ,  0 ,  1 ,  1 ,  2 ,  4 ,  1 ,  10 ,  0] ,
                    [0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  0 ,  10]])


def init_sim(n, sim_matrice):
    P = []
    for i in range(0,n):
        sim_max = 0
        sim_ind_max = 0
        for j in range(0,n):
            if i != j and sim_max < sim_matrice[i][j]:
                sim_max = sim_matrice[i][j]
                sim_ind_max = j
        P.append((sim_max, sim_ind_max))
    return P


def init_class_active(n):
    I = []
    for i in range(0, n):
        I.append(i)
    return I


def algo_part_hierarchique(n , sim_matrice):
    P = init_sim(n, sim_matrice)
    I = init_class_active(n)
    L = []
    # BUG : l'algorithme ne met pas correctement à jour les classes fusionnées
    # et fait des fusions en double : on obtient un résultat correct au bout de N+1 itérations
    for k in range(n+1):
        sim_max = 0
        ind_sim_max = 0
        #choix du document qui a la similarité max
        for i in I:
            (sim, ind) = P[i]
            if sim > sim_max:
                sim_max = sim
                ind_sim_max = ind
        #document qui a la similarité max avec un autre doc
        i1 = ind_sim_max
        (sim, ind) = P[i1]
        #document qui a le plus de similarité avec i1
        i2 = I[ind]
        # similarité -1 (ou 10) entre les 2 documents fusionnés
        P[i1] = (-1, ind)
        #fusion ajoutée
        L.append((i1, i2))
        for i in range(n):
            #  mise a jour de la similarité pour les éléments qui n'ont pas été fusionnés dans cette itération
            if (I[i] == i) and (i != i1) and (i != i2):
                sim_matrice[i][i1] = max(sim_matrice[i][i1], sim_matrice[i][i2])
                sim_matrice[i1][i] = max(sim_matrice[i][i1], sim_matrice[i][i2])
            # mise a jour de la classe fusionné lors de cette itération dans les classes actives:
            if I[i] == i2:
                I[i] = i1
            # mise à jour de la matrice P pour la prochaine itération
            (sim, ind) = P[i1]
            if (I[i] == i) and (i != i1) and (sim_mat[i1][i] > sim):
                P[i1] = (sim_mat[i1][i], i)
    return L


l_fusion = algo_part_hierarchique(N, sim_mat)
print(l_fusion)

""" 1ere version non finie en se basant sur le 1er sujet de TP

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
"""



