--- 
author: 
  - "Leonardo Scoppitto"
classoption: a4paper
date: "September 2020"
documentclass: article
fontsize: 12pt
geometry: "left=3.5cm,right=3cm,top=3cm,bottom=3cm"
output: pdf_document
title: Primo Progetto Intermedio
---

# Compilazione ed esecuzione

# Struttura del codice

# Scelte implementative

Nel progetto sono presenti due classi principali: `Post` e  `SocialNetwork`.

## Post

Nella classe `Post`, definita dall'interfaccia `PostInterface`, definisco la struttura che avranno i post all'interno di *MicroBlog* e i metodi per recuperare informazioni e visualizzarli. I metodi implementati sono quindi:

* `getAuthor`: restituisce la stringa contenente l'autore del post

* `getText`: restituisce il testo del post

* `getId`: restituisce l'ID del post

* `getTime`: restituisce il Timestamp della creazione del post

* `getFollowers`: restituisce il Set contenente i followers del post

* `addFollow`: riceve in input una stringa contenente il nome del follower e lo aggiunge al Set `Followers`

* `printPost`: stampa a schermo il post formattato

* `compareTo`: ridefinisce la funzione presente nell'interfaccia `comparable` per l'ordinamento secondo l'ID dei post

La scelta dei tipi di dati per salvare l'autore, il testo, l'ID e il timestamp del post è stata ovvia, mentre ho scelto di usare un `Set` per salvare i followers del post poiché a differenza del tipo `List` non devo preoccuparmi di gestire i duplicati, in quanto non verranno proprio inseriti.
Inoltre, ho deciso di usare un `HashSet` perché è solitamente più veloce negli inserimenti rispetto al `TreeSet`, anche se, nel caso di un piccolo blog, non si hanno numeri così alti di interazioni da apprezzarne la differenza.

## SocialNetwork

La classe `SocialNetwork` è il fulcro del progetto e contiene tutto il necessario per creare, gestire e interagire con una rete sociale.
Le strutture dati che ho scelto di usare per l'implementazione di un SocialNetwork sono:

1. `HashMap<String, Set<String>> linkedPeople`: è una hashmap che ha come chiavi gli utenti che hanno postato sul SocialNetwork, e come `Values` i followers di quell'utente.

2. `HashMap<String, Set<String>> followed`: è una hashmap che ha come chiave gli utenti che hanno avuto interazioni con gli altri utenti, anche se non hanno postato. Le `Values`, infatti sono `Set` di utenti che vengono seguiti dall'utente nella chiave, così da avere sia una mappa `utente -> persone che lo seguono` che una mappa `utente -> persone che sono seguite da lui`. Questo mi permette di implementare efficientemente il metodo `influencers`, come vedremo dopo.

I principali metodi sono:

* `addPost`: 


