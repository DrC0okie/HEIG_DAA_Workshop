# Travail théorique sur le Bluetooth Low Energy (DAA)



**Auteurs:** Timothée Van Hove, Léo Zmoos

**Date:** 15.01.2024



## Introduction (Tim)

Brève explication de Bluetooth Low Energy. Comparaison avec le Bluetooth classique.



## Historique (Léo)
Anciennement connu sous le nom de Wibree, puis devenu la marque déposée Bluetooth Smart.

Le Bluetooth Low Energy(BLE) est une technologie de transmission sans fil créée par Nokia en 2006.

Cette technologie vise à répondre aux besoins croissants d'applications sans fil à faible consommation d'énergie.
(Exemples: capteurs, montres connectée, dispositifs médicaux, domotique etc.)

le Bluetooth Low Energy est omniprésent dans de nombreux dispositifs du quotidien. 

Il permet de faciliter la communication entre les appareils de manière économe en énergie.

En conclusion, il joue donc un rôle central dans l'évolution de l'Internet des objets (IoT).

## Concepts de base (Léo)

### GATT (Generic Attribute Profile)

Le GATT (Generic Attribute Profile) est l'un des piliers fondamentaux du Bluetooth Low Energy (BLE). 

Il permet de définir la manière dont les dispositifs communiquent et échangent des données. 

Le GATT repose sur un modèle client-serveur:

#### Client
C'est un périphérique qui initie et contrôle les connexions avec le serveur. (Ex. smartphone)

#### Serveur
Le serveur contient des données regroupées sous le nom de services. 

Chaque service contient des caractéristiques qui sont les informations pouvant être échangées.

## Services

Les services sont des conteneurs qui regroupent des données et fonctions liées entre elle (voir exemple ci-dessous) sur un appareil Bluetooth. 

Par exemple:

> Pour un dispositif de suivi d'activité physique. Il y aurait un service dédié à la surveillance du rythme cardiaque.


Ils aident donc à organiser les données et fonctions de manière ordonnée. 

Chaque conteneur possède un UUID(Identifiant Universel Unique) et peut contenir une ou plusieurs  caractéristiques à l'intérieur.

### Caractéristiques

Les caractéristiques représentent les données échangeables au sein d'un service.

#### Propriétés:
- Associée à un type de données spécifique(int, strings, etc.) 
- Possèdes son propre UUID. 

Les caractéristiques jouent un rôle crucial dans la définition des fonctionnalités offertes par un périphérique BLE.


### Modes de fonctionnement du BLE : Central et Périphérique
#### Mode Central

Dans le mode central, un dispositif joue le rôle de "centrale" et s'occupe d'initier la connexion avec un ou plusieurs périphériques. 


> Généralement, ce sont des dispositifs tels que les smartphones/tablettes ou ordinateurs qui utiliseront ce mode.

Ces derniers contrôlent et collectent des données à partir de périphériques BLE.


#### Mode Périphérique

Le mode périphérique correspond au rôle des dispositifs qui se rendent disponibles pour la connexion. 

Ces dispositifs émettent des données ou fournissent des services à d'autres dispositifs BLE en mode ***central***.

Les périphériques BLE sont par exemple:
- des capteurs
- des objets portables
- Autres appareils émettant des données à des fins spécifiques.

#### Les deux modes:

Ensemble, ces deux modes permettent des interactions flexibles entre les dispositifs BLE.

## BLE vs Bluetooth classic (Léo)

Le Bluetooth Low Energy (BLE) et le Bluetooth classique représentent deux évolutions distinctes de la technologie sans fil Bluetooth, chacune adaptée à des besoins spécifiques.

Nous allons donc comparer ces deux technologies sur quelques points pertinents:

### Consommation d'énergie:

L'une des comparaisons majeures que l'on peut faire entre ces deux technologie est évidemment dans l'utilisation de l'énergie.

Comme vu auparavant, le BLE est conçu pour limiter au maximum la consommation énergétique. C'est donc pour ça qu'il est autant adapté à l'IOT.

A contrario, le bluetooth classique permettra d'effectuer d'autres actions qui nécessiterons plus d'énergie.

### Portée:

**BLE:**
- Offre une portée suffisante pour l'IOT ou domotique par exemple mais plus courte que le bluetooth classique
- Cette portée plus courte s'explique par le fait qu'on essaie de limiter au maximum la consommation d'énergie

**Bluetooth Classic:**
- Offre une plus grande portée que le BLE
- Utile pour des applications nécessitant des communications sur des plus longues distances


### Débit de données:
**BLE:**
- Optimisé pour un débit de données plus bas, toujours en lien avec la consommation énergétique
- Adaptés aux petites quantités de données transmises fréquemment

**Bluetooth Classic:**
- Permet un débit de données plus élevé
- Adapté aux applications nécessitant le transfert rapide de volumes importants de données


### Applications:
Comme vu auparavant, les applications de ces technologie sont donc bien différentes.

**BLE**: IOT, Domotique, etc.
**Bluetooth Classic**: Haut-parleurs, casques, connexions entre smartphones et ordinateurs, etc.

En conclusion, il n'y en un pas un qui serait "mieux" que l'autre, ils ont juste un objectif différent!

## Implémentation dans Android (Tim)


3.1 Configuration de l'environnement
    Autorisations requises.
    Vérification de la compatibilité des appareils.
3.2 Recherche d'appareils BLE
    Mise en œuvre de la découverte des appareils.
    Traitement des résultats de l'analyse.
3.3 Connexion à un appareil BLE
    Établir une connexion.
    Gestion du cycle de vie de la connexion.
3.4 Communication de données
    Lecture et écriture des caractéristiques.
    Notifications et indications.
3.5 Meilleures pratiques dans la mise en œuvre de BLE
    Balayage efficace.
    Gestion des connexions et de l'utilisation de la mémoire.



## Conclusion (Tim)

