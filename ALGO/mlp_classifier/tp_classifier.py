 #!/usr/bin/python2.6
# -*-coding:Latin-1 -*

from sklearn.neural_network import MLPClassifier
from sklearn.datasets import load_digits
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

X = [[0., 0.], [0., 1.], [1., 0.], [1., 1.]]
clf = MLPClassifier(solver='lbfgs', activation='identity', hidden_layer_sizes=())

#1.1
y_and = [0, 0, 0, 1]
clf.fit(X, y_and)
#print(clf.predict([[1., 0.]]))

#1.2
y_or = [0, 1, 1, 1]
clf.fit(X, y_or)
#print(clf.predict([[1., 1.]]))

#1.3.a
y_xor = [0, 1, 1, 0]
clf.fit(X, y_xor)
#print(clf.predict([[0., 0.]]))
#Le classifieur ne marche pas car il cherche une droite qui sépare les points (fonction linéaire) et ce n'est pas possible pour les valeurs d'un XOR

#1.3.b
clf = MLPClassifier(solver='lbfgs', activation='identity', hidden_layer_sizes=(4,2))
clf.fit(X, y_xor)
#print(clf.predict([[1., 1.]]))
#Le classifieur fournit les bons résultats car en rajoutant une couche cachée, nous pouvons créer deux droites pour séparer les points afin de correspondre aux résultats du XOR (une sorte de bande centrale du graphe).

#1.3.c
clf = MLPClassifier(solver='lbfgs', activation='tanh', hidden_layer_sizes=(4,2))
clf.fit(X, y_xor)
#print(clf.predict([[1., 1.]]))
#Le classifieur ne fournit pas toujours des résultats corrects avec 2 couches et la fonction "tan(x)". 
#Cette fonction devrait permettre de créer une zone qui séparerait les résultats du XOR.

#2
dataset = load_digits()
x = dataset.data
y = dataset.target
train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.1)
clf = MLPClassifier(solver='lbfgs', activation='tanh', hidden_layer_sizes=(10000, 5000))
clf.fit(train_x, train_y)
test_y_pred = clf.predict(test_x)
print("Exactitude :", accuracy_score(test_y, test_y_pred))
