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


"""def init_sim(n, sim_matrice):
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
print(l_fusion)"""

""" 1ere version non finie en se basant sur le 1er sujet de TP """


def sort_list(P):
    for x in range(N):
        P[x].sort(key=itemgetter(0), reverse=True)
    return P


def get_max(P):
    (sim_max, indice_max) = (0, 0)
    argmax=0
    for x in range(N):
        for j in range(N):
            if I[j]==1:
                if (sim_max < P[x][j][0]) and (P[x][j][0]!=-1) :
                    sim_max = P[x][j][0]
                    indice_max = P[x][j][1]
                    argmax = x
    return argmax, indice_max

# Initialisation de l'indicateur de classes actives
I = []
for i in range(N):
    I.append(1);


# Initialisation de la file a priorité des similarités entre docs
P = [[0 for x in range(N)] for y in range(N)]
for i in range(N):
    for j in range(N):
        if (i==j):
            P[i][j] = [-1,j]
        else:
            P[i][j] = [int(sim_mat[i][j]), j]

# trier la file
P = sort_list(P);
# suppression de la similarité
#print(P)

# Construction du dendrograme
L = [] # liste fusions
for k in range(N-1):
    (argmax_sim, ind_m) = get_max(P)
    i1 = argmax_sim
    i2 = ind_m
    print("argmax = " + str(i1) + " index : " + str(i2))
    L.append([i1, i2])
    I[i2]=0
    # supprimer S[i1][i2] de P[i1]
    P[i1][i2][0] = -1
    for i in range(N):
        if (I[i]==1) and (i!=i1):
            P[i][i1][0] = -1
            P[i1][i][0] = -1
            P[i][i2][0] = -1
            sim_mat[i][i1] = max(sim_mat[i][i1], sim_mat[i][i2])
            sim_mat[i1][i] = max(sim_mat[i][i1], sim_mat[i][i2])
            P[i][i1] = [int(sim_mat[i][i1]), int(sim_mat[i][i1])]
            P[i1][i] = [int(sim_mat[i1][i]), int(sim_mat[i1][i])]
            P = sort_list(P)
    print(P)

print(L)
