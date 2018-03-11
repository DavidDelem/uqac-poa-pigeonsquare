# uqac-poa-pigeonsquare

<h2>8INF957 Programmation objet avancée - Hivers 2018 - UQAC</h2>
<h3>Devoir 2</h3>
<p><b>David Delemotte, Rénald Morice</b></p>

---

<h4>Lancement</h4>

Compiler et lancer le projet avec un IDE, ou en ligne de commande de la façon suivante:

Pour lancer ces commandes, se placer à la racine du projet, au même niveau que src:

```
mkdir build
cd build
javac -d . ../src/pigeonsquare/*.java ../src/pigeonsquare/utils/*.java ../src/pigeonsquare/pigeons/*.java
```

Il est nécessaire d'importer les ressources (images) dans build:

```
cp -r ../ressources ./ressources
```

Pour finir, lancer le projet:

```
java pigeonsquare.SquareUI
```

---

<h4>Commandes</h4>

**`Clic gauche`** Ajouter un pigeon (race aléatoire)<br/>
**`Clic droit`** Ajouter des graines<br/>
**`Barre espace`** Lancer une pierre à une position aléatoire<br/>
**`Touche R`** Remettre à zéro<br/>

---

<h4>Ce qui a été réalisé</h4>

- [x] Ajout de pigeon (10 max) et graines (3 max) au clic de la souris, de pierres (2 max) en appuyant sur espace.
- [x] Les 3 races de pigeons apparaissent aléatoirement. Chacune à une vitesse différente.
<p align="center">
<img src="ressources/readme/elems.png" width="450"/>
</p>

- [x] Les pigeons se déplacent vers la nourriture la plus proche. Lorsqu'il n'y a pas de nourriture ils attendent.
- [x] Si pendant son déplacement, une nourriture encore plus proche est ajoutée, le pigeon change d'objectif.
- [x] Seul le premier pigeon qui arrive à la nourriture la mange. Les autres pigeons s'arrêtent alors.
- [x] La nourriture pourrie au bout d'un moment, les pigeons y vont mais ne la mangent pas. Ensuite elle disparaît.

<p align="center">
<img src="ressources/readme/nourriture.png" width="450"/>
</p>

- [x] Lorsqu'on lance une pierre, les pigeons autours prennent peur et s'éloignent à des positions aléatoires.
- [x] La pierre disparaît au bout de quelques secondes.
