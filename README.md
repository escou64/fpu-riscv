# Conception et évaluation d'une unité de calcul en virgule flottante

### Dépendances
Certains outils sont nécessaires afin de pouvoir réaliser et tester des designs en Chisel.
Tout d'abord **Java**:

```
  sudo apt-get install default-jdk
```

Ensuite l'outil **sbt**:

```
  echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
  sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
  sudo apt-get update
  sudo apt-get install sbt
```

Enfin, GtkWave pour visualiser les chronogrammes:

```
  sudo apt-get install gtkwave
```

Plus d'informations sont présentes sur la [page d'installation de Chisel](https://github.com/freechipsproject/chisel3/blob/master/SETUP.md).

### Organisation d'un projet

- **src/main/scala**: contient les fichiers source Chisel des designs.
- **src/test/scala**: contient les fichiers source Chisel des tests.
- build.sbt: fichier de configuration de l'outil sbt.

### Conception
Un exemple de module nommé Adder est présent dans le fichier *src/main/scala/adder/adder.scala*.
Pour générer le Verilog correspondant, le format de la commande du terminal est le suivant:

```
  sbt "runMain <nom-librairie>.<nom-design> --target-dir <destination>"
```

*--target-dir* esy une option permettant de choisir le répertoire où sera généré le module Verilog correspondant.
Dans l'exemple Adder, il est possible de générer le module Verilog avec la commande suivante:

```
  sbt "runMain fpu.tools.Adder --target-dir output"
```

Dans le répertoire *output/*, on trouve alors un fichier *Adder.v* correspondant au module décrit dans le fichier Chisel.

### Simulation
De la même manière qu'en Verilog ou VHDL, le langage contient des commandes dédiées pour réaliser des testbenchs.
Deux exemples existent ici pour le module Adder dans *src/test/scala/adder/test0.scala* et *src/test/scala/adder/test1.scala*.
Il est possible d'exécuter individuellement un test avec la commande suivante:

```
  sbt "test:runMain <nom-librairie>.<nom-test> --target-dir <destination>"
```

Dans l'exemple Adder, on a donc le choix entre deux tests différents:

```
  sbt "test:runMain fpu.tools.AdderTest0 --target-dir output"
  sbt "test:runMain fpu.tools.AdderTest1 --target-dir output"
```

On retouve alors les résultats dans le répertoire *output/*.
Les chronogrammes correspondants à chaque test se trouvent dans des fichiers *.vcd*.
Pour les visualiser, il est alors nécessaire d'utiliser l'outil **GtkWave**:

```
  gtkwave <chemin-vcd>/<fichier>.vcd
```

Le Chisel fournit également une infrastructure pour effectuer des tests unitaires.
Cela permet donc de lancer rapidement l'ensemble des tests et de s'assurer que tout fonctionne correctement après une modification.
Le format de la commande est le suivant:

```
  testOnly <nom-librairie>.<nom-test-unitaire>"
```

Dans le cas de l'exemple Adder, la structure est décrite dans *src/test/scala/adder/unit.scala*.
Pour lancer l'exécution des tests, il faut alors utiliser la commande suivante:

```
  testOnly fpu.tools.UnitTester"
```

Ici, le répertoire de destination *output* est spécifié directement dans le fichier.
On retrouve ensuite dans le terminl le résultat des différents tests.

### Références

- Langage Chisel :
  - [Informations](https://github.com/freechipsproject/chisel3)
  - [Livre tutoriel](https://github.com/schoeberl/chisel-book)
  - [Bootcamp](https://mybinder.org/v2/gh/freechipsproject/chisel-bootcamp/master)
- FPU et RISC-V:
  - [Norme IEEE](https://fr.wikipedia.org/wiki/IEEE_754)
  - [Spécification RISC-V](docs/riscv-spec.pdf)
  - Livre architecture: Computer Organization and Design RISC-V Edition
