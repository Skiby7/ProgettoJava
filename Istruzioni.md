--- 
classoption: a4paper
documentclass: article
fontsize: 10pt
geometry: "left=3cm,right=3cm,top=3cm,bottom=3cm"
output: pdf_document

---

# Compilazione ed esecuzione

Dopo aver estratto l'archivio, ci si troverà davanti a tre cartelle: *Source*, *Eseguibili* e *Compilazione_Manuale*. Dentro la prima si torva tutto il codice sorgente con tutte le classi utilizzate per il progetto, in particolare dentro la cartella *Interfaces* si trovano le interfacce delle classi principali dove è scritta la specifica; l'invariante di rappresentazione e la funzione di astrazione di `SocialNetwork`, invece, sono nel file *SocialNetwork.java*.
Dentro la cartella *Eseguibili*, invece, si hanno due pacchetti:

* `TestSet.jar`: contiene una simulazione in cui 10 utenti interagiscono fra loro postando delle frasi celebri. Questa è la batteria di test richiesta.

* `MicroBlogCLI.jar`: contiene un'interfaccia testuale con cui poter provare manualmente tutto ciò che è stato implementato nel progetto. Per accedere al ripristino dei contenuti segnalati (parte 3) è sufficiente eseguire il login come *admin* (password: *admin*), mentre per usare MicroBlog normalmente si può inserire un qualsiasi username. Sono stati precaricati 10 utenti e 10 post. 

I pacchetti si eseguono spostandosi da terminale all'interno della cartella *Eseguibili* e lanciandoli con il comando `java -jar NomePacchetto.jar`. Se si volesse compilare una delle due classi principali (`TestSet.java` o `MicroBlog.java`) è sufficiente spostarsi da terminale all'interno della cartella *Compilazione_Manuale* e compilare col comando `javac NomeClasse.java`, per poi eseguire il programma con `java NomeClasse`.\
**N.B.:** Per una visualizzazione ottimale della simulazione si consiglia di espandere il terminale a tutto schermo.