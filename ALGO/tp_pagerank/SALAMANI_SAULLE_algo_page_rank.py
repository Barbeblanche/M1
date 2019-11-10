import numpy as np
lmb = 0.1

fich = open('tiny-graph.txt', "r")
N = int(fich.readline())
epsilon=10**(-5)

def init_Matrice_Adj(fichier):
    A = [[0 for i in range(N)] for j in range(N)]
    for i in range(N):
        line = fichier.readline().strip()
        sommets = [int(x) for x in line.split(' ')]
        for s in sommets:
            if (i!= s):
                A[i][s] = 1
    return A


def dist(x,y):
    return np.sqrt(np.sum((x-y)**2))

def init_Matrice_Prob(A):
    P = np.zeros((N,N), dtype=float)
    for i in range(N):
        somme = 0
        for j in range(N):
            somme += A[i][j]
        for j in range(N):
            if (somme != 0):
                P[i,j] = lmb * (A[i][j]/somme) + (1-lmb) * (1/N)
            else :
                P[i,j] = 1/N
    return P


def algo_Page_Rank(A,arret):
    P = init_Matrice_Prob(A)
    R = np.full(N,1/N)
    R_suivant = np.zeros(N)
    R_suivant = np.dot(R,P)
    l = 0
    while (dist(R_suivant,R) >= arret):
        l=l+1
        print(l)
        print(R)
        print(R_suivant)

        R = R_suivant
        R_suivant = np.dot(R,P)

A = init_Matrice_Adj(fich)
algo_Page_Rank(A,epsilon)


"""Réponses:
1. On remarque que plus epsilon diminue et plus le nombre d'itérations augmente, ce qui paraît logique car epsilon détermine l'arrêt de la boucle.
Cependant le ranking de chaque page ne change pas beaucoup (il diminue très légèrement quand epsilon diminue).

2.Les pages qui on beaucoup de liens entrant ont en général le meilleur ranking.

3. Pour améliorer le rang d'une page il faudrait rediriger la sortie des pages les mieux classées vers la page à améliorer.

4. Lorsque lambda tend vers 0, le rang des pages se stabilise a une valeur commune pour toute les pages (une "moyenne"). L'algorithme n'a donc plus vraiment de "sens".
"""
