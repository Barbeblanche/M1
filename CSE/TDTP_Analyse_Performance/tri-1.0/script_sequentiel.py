import subprocess

#fichier_seq = open("exec_seq.txt","w")
#fichier_seq.write("Taille;Run_ID;Time\n")

fichier_seq_min = open("min_time_seq.txt","w")
fichier_seq_min.write("Taille;Min_Time\n")
taille = 10000
min_time = -1

while taille <= 100000:
    nom_vecteur = "vecteurs/vecteur_" + str(taille) + ".in"
    subprocess.call("./creer_vecteur -s " + str(taille) + " > " + nom_vecteur, shell=True)
    #for nb_thread in range(2,20):
    for i in range (100):
        out_seq = subprocess.check_output("./tri_sequentiel -t < " + nom_vecteur, shell=True)
        if (min_time < int(out_seq.decode("utf-8"))):
            min_time = int(out_seq.decode("utf-8"))
        #fichier_seq.write(str(taille) + ";" + str(i) + ";" + out_seq.decode("utf-8"))
    fichier_seq_min.write(str(taille) + ";" + str(min_time) +"\n")
    min_time = -1
    taille += 10000
