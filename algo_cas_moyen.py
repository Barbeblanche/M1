from sklearn.datasets import load_iris
import random
import numpy as np

def init_centroides(k,ens_x):
    c = []
    for i in range(0,k):
        c.append(ens_x[random.randint(0,len(ens_x))])
    return c


def maj_appartenance(ens_x,c):
    a_tmp = []
    a = []
    for i in range(0,len(ens_x)):
        for j in range(0,len(c)):
            #distance vectorielle entre le point courant et le centroide courant
            a_tmp.append(np.linalg.norm(ens_x[i] - c[j]))
        # la liste a correspond à la liste finale des apparetenance, donc le min entre les 4 distances euclidiennes pour 1 point et 4 centroides
        a.append(a_tmp.index(min(a_tmp)))
        a_tmp.clear()
    return a


def maj_centroides(c, appart, ens_x):
    moy1 = 0
    moy2 = 0
    moy3 = 0
    moy4 = 0
    compt = 0
    for j in range(0, len(c)):
        for i in range(0, len(appart)):
            if j == i:
                moy1 += ens_x[i][0]
                moy2 += ens_x[i][1]
                moy3 += ens_x[i][2]
                moy4 += ens_x[i][3]
                compt += 1
        c[j] = [moy1 / compt, moy2 / compt, moy3 / compt, moy4 / compt]
    return c


def algo_kmoyennes(precision,k,ens_x):
    appart_final = []
    c = init_centroides(k,ens_x)
    boucle = precision + 1
    while (boucle > precision):
        appart = maj_appartenance(ens_x, c)
        c = maj_centroides(c, appart, ens_x)
        nv_appart = maj_appartenance(ens_x, c)
        boucle = 0
        appart_final = nv_appart
        for i in range(1, len(ens_x)):
            boucle += abs(nv_appart[i] - appart[i])
        boucle = boucle / len(ens_x)
    print("centroides : ")
    for cent in c:
        print(cent)
    print("appartenance : ")
    for a in appart_final:
        print(a)


if __name__ == "__main__":
    dataset = load_iris()
    algo_kmoyennes(0,5,dataset.data)
