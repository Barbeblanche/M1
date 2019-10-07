import numpy as np
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

P = np.zeros((8, 8, 2))
for i in range(0,7):
    for k in range(0,7):
        for j in range(0,7):
            P[i][k] = [sim_mat[k][j],j]
print(P)