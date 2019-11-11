import subprocess

nb_proc = 4
fichiers_thread = []
i = 0
for nb_thread in [2,4,8,16,32]:
    fichiers_thread.append(open("fichiers_thread/exec_thread_acc_" + str(nb_thread) + ".txt", "w"))
    fichiers_thread.append(open("fichiers_thread/exec_thread_eff_" + str(nb_thread) + ".txt", "w"))
    fichiers_thread[i].write("Taille_mat;Run_ID;Acc\n")
    fichiers_thread[i+1].write("Taille_mat;Run_ID;Eff\n")
    i+=2

taille = 10000
min_time = -1

while taille <= 1000000:
    nom_vecteur = "vecteurs/vecteur_" + str(taille) + ".in"
    #subprocess.call("./creer_vecteur -s " + str(taille) + " > " + nom_vecteur, shell=True)
    min_sequentiel = -1
    l = []
    #Avoir le minimum de l'execution sequentiel pour la même taille de vecteur (voir script_sequentiel.py)
    min_time_fichier = open("min_time_seq.txt",'r')
    min_time_fichier.readline()
    print("taille = " + str(taille))
    for line in min_time_fichier:
        l = line.split(';')
        if (l[0] == str(taille)):
            min_sequentiel = int(l[1])
            break
    min_time_fichier.close()
    ##
    #Calcul de l'accélération pour la version thread
    if (min_sequentiel != -1):
        for i in range(20):
            j = 0
            for nb_thread in [2,4,8,16,32]:
                out_thread = subprocess.check_output("./tri_threads -t -p " + str(nb_thread) + " < " + nom_vecteur, shell=True)
                temps_thread = int(out_thread.decode("utf-8"))
                acc = min_sequentiel / temps_thread
                fichiers_thread[j].write(str(taille) + ";" + str(i) + ";" + str(acc) + "\n")
                eff = acc / nb_proc
                fichiers_thread[j+1].write(str(taille) + ";" + str(i) + ";" + str(eff) + "\n")
                j+=2
    taille+=10000
