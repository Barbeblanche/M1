# script R permettant de comparer les temps moyens de calcul des produits de matrices carrées entre algo classique et algo transposée
# pour des tailles variant de 2000 à 5000

library(ggplot2)
library(plyr)
library(reshape2)

# extraction des données
data_para_eff2 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_eff_2.csv",sep=';', dec='.'))
data_para_eff4 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_eff_4.csv",sep=';', dec='.'))
data_para_eff8 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_eff_8.csv",sep=';', dec='.'))
data_para_eff16 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_eff_16.csv",sep=';', dec='.'))
data_para_eff32 <- data.frame(read.csv2(file="fichiers_4_execs_threads/exec_thread_eff_32.csv",sep=';', dec='.'))
#data_para_eff <- data.frame(read.csv2(file="fichiers_thread/exec_thread_eff_2.csv",sep=';', dec='.'))

# analyse statistique

stat_eff2<-ddply(data_para_eff2,c("Taille_mat"),summarise,N=length(Eff),mean=mean(Eff),sd=sd(Eff),se=1.96*(sd/sqrt(N)))
stat_eff4<-ddply(data_para_eff4,c("Taille_mat"),summarise,N=length(Eff),mean=mean(Eff),sd=sd(Eff),se=1.96*(sd/sqrt(N)))
stat_eff8<-ddply(data_para_eff8,c("Taille_mat"),summarise,N=length(Eff),mean=mean(Eff),sd=sd(Eff),se=1.96*(sd/sqrt(N)))
stat_eff16<-ddply(data_para_eff16,c("Taille_mat"),summarise,N=length(Eff),mean=mean(Eff),sd=sd(Eff),se=1.96*(sd/sqrt(N)))
stat_eff32<-ddply(data_para_eff32,c("Taille_mat"),summarise,N=length(Eff),mean=mean(Eff),sd=sd(Eff),se=1.96*(sd/sqrt(N)))
#stat_eff<-ddply(data_para_eff,c("Taille_mat"),summarise,N=length(Eff),mean=mean(Eff),sd=sd(Eff),se=1.96*(sd/sqrt(N)))


# tracé des résultats obtenus

p <- ggplot()

p <- p + geom_point(data=stat_eff2, aes(x=Taille_mat, y=mean, colour = "2 threads")) + geom_line(data=stat_eff2, aes(x=Taille_mat, y=mean, colour = "2 threads")) 
p <- p + geom_errorbar(data=stat_eff2,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_eff4, aes(x=Taille_mat, y=mean, colour = "4 threads")) + geom_line(data=stat_eff4, aes(x=Taille_mat, y=mean, colour = "4 threads")) 
p <- p + geom_errorbar(data=stat_eff4,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_eff8, aes(x=Taille_mat, y=mean, colour = "8 threads")) + geom_line(data=stat_eff8, aes(x=Taille_mat, y=mean, colour = "8 threads")) 
p <- p + geom_errorbar(data=stat_eff8,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_eff16, aes(x=Taille_mat, y=mean, colour = "16 threads")) + geom_line(data=stat_eff16, aes(x=Taille_mat, y=mean, colour = "16 threads")) 
p <- p + geom_errorbar(data=stat_eff16,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())


p <- p + geom_point(data=stat_eff32, aes(x=Taille_mat, y=mean, colour = "32 threads")) + geom_line(data=stat_eff32, aes(x=Taille_mat, y=mean, colour = "32 threads")) 
p <- p + geom_errorbar(data=stat_eff32,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())

#p <- p + geom_point(data=stat_eff, aes(x=Taille_mat, y=mean, colour = "efficacite")) + geom_line(data=stat_eff, aes(x=Taille_mat, y=mean, colour = "efficacite"))
#p <- p + geom_errorbar(data=stat_eff,aes(x=Taille_mat,ymin=mean-se,ymax=mean+se),width=.2,position=position_dodge())

p <- p + xlab("Taille des matrices")
p <- p + ylab("Acceleration")

  cols <- c("2 threads"="red","4 threads"="blue","8 threads"="yellow","16 threads"="orange","32 threads"="green")
p <- p + scale_colour_manual("", 
                             values = cols)

p <-  p + theme(panel.background = element_rect(fill = "lightgrey"),
                panel.grid.minor = element_line(colour = "black", linetype="dashed", size = 0.1),
                panel.grid.major = element_line(colour = "black", size = 0.1),
                legend.position="bottom")

show(p)

