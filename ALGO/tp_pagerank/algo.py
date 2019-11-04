import numpy
lmb = 0.7

fich = open('test.txt', "r")
N = int(fich.readline())


def init_Matrice_Adj(fichier):
    A = [[0 for i in range(N)] for j in range(N)]
    for i in range(N):
        line = fichier.readline().strip()
        sommets = [int(x) for x in line.split(' ')]
        for s in sommets:
            if (i!= s):
                A[i][s] = 1
    return A


def init_Matrice_Prob(A):
    P = [[0 for i in range(N)] for j in range(N)]
    for i in range(N):
        somme = 0
        for j in range(N):
            somme += A[i][j]
        for j in range(N):
            if (somme != 0):
                P[i][j] = lmb * (A[i][j]/somme) + (1-lmb) * (1/N)
            else :
                P[i][j] = 1/N
    return P


def algo_Page_Rank(A,lbd,arret):
    P = init_Matrice_Prob(A)
    R = []
    R_courant = [1/N for i in range(N)]
    R_suivant = [0 for i in range(N)]
    R.append(R_courant)
    l = 0
    while (numpy.linalg.norm((R_suivant - R_courant), ord=1)<= arret):

A = init_Matrice_Adj(fich)


