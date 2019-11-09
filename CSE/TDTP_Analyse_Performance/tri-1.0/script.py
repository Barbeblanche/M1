import subprocess


fichier_seq = open("exec_seq.txt","w")
fichier_seq.write("Taille;Run_ID;Time\n")

fichier_seq_min = open("min_time_seq.txt","w")
fichier_seq_min.write("Taille;Min_Time")

fichiers_thread = []
for nb_thread in range(2, 20):
    fichiers_thread.append(open("fichiers_thread/exec_thread_" + str(nb_thread) + ".txt", "w"))
    fichiers_thread[nb_thread-2].write("Taille;Run_ID;Time\n")

taille = 10000
min_time = -1
while taille <= 100000000:
    nom_vecteur = "vecteurs/vecteur_" + str(taille) + ".in"
    subprocess.call("./creer_vecteur -s " + str(taille) + " > " + nom_vecteur, shell=True)
    #for nb_thread in range(2,20):
    for i in range(5):
        out_thread = subprocess.check_output("./tri_threads -t -p " + str(nb_thread) + " < " + nom_vecteur, shell=True)
        fichiers_thread[8].write(str(taille) + ";" + str(i) + ";" + out_thread.decode("utf-8"))
    for i in range (5):
        out_seq = subprocess.check_output("./tri_sequentiel -t < " + nom_vecteur, shell=True)
        if (min_time < int(out_seq.decode("utf-8"))):
            min_time = int(out_seq.decode("utf-8"))
        fichier_seq.write(str(taille) + ";" + str(i) + ";" + out_seq.decode("utf-8"))
    fichier_seq_min.write(str(taille) + ";" + str(min_time))
    taille+=50000
