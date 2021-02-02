# Conseils pour le développement du projet

## Style de codage

1. Tous les noms de classes ne doivent être composés que de caractères alphanumériques explicites.
  **Exemple**: Adder, ShiftRegister, Alu8Bit ...

2. Les noms des classes représentant des entrées/ sorties (I/Os) doivent avoir le suffixe *IO.
  **Exemple**: PortIO, FpuIO ...

3. Les noms des classes représentant des bus doivent avoir le suffixe *Bus.
  **Exemple**: ControlBus, StageBus ...

4. Les noms de registres doivent avoir le préfixe *reg_\**.
  **Exemple**: reg_rs1, reg_source ...

5. Les noms des entrées doivent avoir le préfixe *i_\**.
  **Exemple**: i_bus, i_value ...  

6. Les noms des sorties doivent avoir le préfixe *o_\**.
  **Exemple**: o_bus, o_value ...  

7. Les noms des bus d'I/Os doivent avoir le préfixe *b_\**.
  **Exemple**: b_cpu, b_mem ...  
  
8. Les noms des instances de modules doivent avoir le préfixe *m_\**.
  **Exemple**: m_alu, m_cpu ...
  
9. Séparer les fonctionnalités principales d'un module par des commentaires.

  
## Utilisation des branches

Afin de pouvoir travailler à plusieurs sur des versions différentes du projet, il peut être utile d'utiliser des branches.
Cette fonctionnalité de git permet de développer en parallèle des fonctionnalités et/ou blocs différents dans un même projet mais sans entrer en conflit.
Voici ci-dessous une liste des différentes commandes pour travailler simplement avec les branches.

Premièrement, il est nécessaire de créer une branche pour chaque version du projet.
Ceci ne doit être effectué qu'une seule fois au début:
```
  git checkout -b <nom-de-la-branche>
```

La commande suivante est utile pour savoir à tout moment dans quelle branche on se trouve:
```
  git branch -a
```

Ensuite le fonctionnement est similaire au fonctionnement basique avec la branche **master** par défaut:
```
  git add *                           # Pour ajouter les nouveaux fichiers
  git commit -m "Nouvelle version"    # Pour effectuer le commit
```

En revanche, pour intéragir avec le serveur git (ici GitHub), il est nécessaire d'indiquer la branche à chaque fois:
```
  git pull origin <nom-de-la-branche>   # Permet de mettre à jour localement depuis la branche <nom-de-la-branche> du serveur
  git push origin <nom-de-la-branche>   # Permet de mettre à jour la version de la branche <nom-de-la-branche> sur le serveur
```
