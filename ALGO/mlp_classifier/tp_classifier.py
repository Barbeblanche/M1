
from sklearn.neural_network import MLPClassifier
from sklearn.datasets import load_digits
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score

X = [[0., 0.], [0., 1.], [1., 0.], [1., 1.]]
clf = MLPClassifier(solver='lbfgs', activation='identity', hidden_layer_sizes=())

y_and = [0, 0, 0, 1]
clf.fit(X, y_and)
#print(clf.predict([[1., 0.]]))

y_or = [0, 1, 1, 1]
clf.fit(X, y_or)
#print(clf.predict([[1., 1.]]))

y_xor = [0, 1, 1, 0]
clf.fit(X, y_xor)
#print(clf.predict([[0., 0.]]))
#Le classifieur ne marche pas car il cherche une droite (fonction linéaire) et ce n'est pas possible pour les valeurs d'un XOR

clf = MLPClassifier(solver='lbfgs', activation='identity', hidden_layer_sizes=(4,2))
clf.fit(X, y_xor)
#print(clf.predict([[1., 1.]]))
#Le classifieur fournit les bons résultats car

clf = MLPClassifier(solver='lbfgs', activation='tanh', hidden_layer_sizes=(4,2))
clf.fit(X, y_xor)
print(clf.predict([[1., 1.]]))

dataset = load_digits()
x = dataset.data
y = dataset.target
train_x, test_x, train_y, test_y = train_test_split(x, y, test_size=0.1)
clf = MLPClassifier(solver='lbfgs', activation='tanh', hidden_layer_sizes=(10000, 5000))
clf.fit(train_x, train_y)
test_y_pred = clf.predict(test_x)
print("Exactitude :", accuracy_score(test_y, test_y_pred))
