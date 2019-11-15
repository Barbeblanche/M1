# script R permettant de comparer les temps moyens de calcul des produits de matrices carrées entre algo classique et algo transposée
# pour des tailles variant de 2000 à 5000

library(ggplot2)
library(plyr)
library(reshape2)

# extraction des données
data_para_acc2 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_acc_2.csv",sep=';', dec='.'))
data_para_acc4 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_acc_4.csv",sep=';', dec='.'))
data_para_acc8 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_acc_8.csv",sep=';', dec='.'))
data_para_acc16 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_acc_16.csv",sep=';', dec='.'))
data_para_acc32 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_acc_32.csv",sep=';', dec='.'))
#data_para_acc <- data.frame(read.csv2(file="fichiers_thread/exec_thread_acc_2.csv",sep=';', dec='.'))

# analyse statistique

stat_acc2<-ddply(data_para_acc2,c("Taille_mat"),summarise,N=length(Acc),mean=mean(Acc),sd=sd(Acc),se=1.96*(sd/sqrt(N)))
stat_acc4<-ddply(data_para_acc4,c("Taille_mat"),summarise,N=length(Acc),mean=mean(Acc),sd=sd(Acc),se=1.96*(sd/sqrt(N)))
stat_acc8<-ddply(data_para_acc8,c("Taille_mat"),summarise,N=length(Acc),mean=mean(Acc),sd=sd(Acc),se=1.96*(sd/sqrt(N)))
stat_acc16<-ddply(data_para_acc16,c("Taille_mat"),summarise,N=length(Acc),mean=mean(Acc),sd=sd(Acc),se=1.96*(sd/sqrt(N)))
stat_acc32<-ddply(data_para_acc32,c("Taille_mat"),summarise,N=length(Acc),mean=mean(Acc),sd=sd(Acc),se=1.96*(sd/sqrt(N)))
#stat_acc<-ddply(data_para_acc,c("Taille_mat"),summarise,N=length(acc),mean=mean(acc),sd=sd(acc),se=1.96*(sd/sqrt(N)))


# tracé des résultats obtenus

p <- ggplot()

p <- p + geom_point(data=stat_acc2, aes(x=Taille_mat, y=mean, colour = "2 threads")) + geom_line(data=stat_acc2, aes(x=Taille_mat, y=mean, colour = "2 threads")) 
p <- p + geom_errorbar(data=stat_acc2,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_acc4, aes(x=Taille_mat, y=mean, colour = "4 threads")) + geom_line(data=stat_acc4, aes(x=Taille_mat, y=mean, colour = "4 threads")) 
p <- p + geom_errorbar(data=stat_acc4,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_acc8, aes(x=Taille_mat, y=mean, colour = "8 threads")) + geom_line(data=stat_acc8, aes(x=Taille_mat, y=mean, colour = "8 threads")) 
p <- p + geom_errorbar(data=stat_acc8,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_acc16, aes(x=Taille_mat, y=mean, colour = "16 threads")) + geom_line(data=stat_acc16, aes(x=Taille_mat, y=mean, colour = "16 threads")) 
p <- p + geom_errorbar(data=stat_acc16,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_acc32, aes(x=Taille_mat, y=mean, colour = "32 threads")) + geom_line(data=stat_acc32, aes(x=Taille_mat, y=mean, colour = "32 threads")) 
p <- p + geom_errorbar(data=stat_acc32,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())

#p <- p + geom_point(data=stat_acc, aes(x=Taille_mat, y=mean, colour = "accicacite")) + geom_line(data=stat_acc, aes(x=Taille_mat, y=mean, colour = "accicacite"))
#p <- p + geom_errorbar(data=stat_acc,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())

p <- p + xlab("Taille des matrices")
p <- p + ylab("Temps (s)")

cols <- c("2 threads"="red","4 threads"="blue","8 threads"="yellow","16 threads"="orange","32 threads"="green")
p <- p + scale_colour_manual("", 
                             values = cols)

p <-  p + theme(panel.background = element_rect(fill = "lightgrey"),
                panel.grid.minor = element_line(colour = "black", linetype="dashed", size = 0.1),
                panel.grid.major = element_line(colour = "black", size = 0.1),
                legend.position="bottom")

show(p)

