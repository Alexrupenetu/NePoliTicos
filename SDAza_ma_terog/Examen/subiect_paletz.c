#include<stdio.h>
#include<stdlib.h>
#include<string.h>

//Multilista mea are o lista mare, aceasta continand mai multe liste mici
//Numesc nodMare - nodul dintr-o lista mare
//       nodMic - nodul dintr-o lista mica, continuta de o lista mare 
//In cazul acesta, nodMare -depozit si nodMic - paletii din depozite

typedef struct nodMic{
	int cod_palet;
	struct nodMic *next;
}nodMic;

typedef struct nodMare{
	char nume_depozit[20];
	struct nodMare *next;
	nodMic *sublista;
}nodMare;

nodMare *lista=NULL;

nodMare *adauga_final_lista_mare(nodMare *lista, char nume[20]) //Complexitate O(n)
{ 
	
	nodMare *nodNou = (nodMare*) malloc(sizeof(nodMare));
	
	if(nodNou!=NULL)
	{
		strcpy(nodNou->nume_depozit, nume);
		nodNou->next = NULL;
		nodNou->sublista=NULL;
		
		if(lista==NULL)
		{
			lista = nodNou;
		}
		else
		{
			nodMare *aux =lista;
			
			while(aux->next!=NULL)
				aux = aux->next;
				
			aux->next = nodNou;
		}
	}
	return lista;
}

nodMic *adauga_ordonat_lista_mica(nodMic *lista, int cod) //Complexitate O(n)
{
	nodMic *nod_nou = (nodMic*) malloc(sizeof(nodMic));
	
	if(nod_nou)
	{
		nod_nou->cod_palet = cod;
		nod_nou->next = NULL;
		
		if(lista==NULL)
			lista = nod_nou;
		else
		{
			if(lista->cod_palet > cod)
				{
					nod_nou->next = lista;
					lista = nod_nou;
				}
			else
			{
				nodMic *aux = lista;
				while(aux!=NULL && aux->next!=NULL && aux->next->cod_palet<cod)
					aux=aux->next;
					
				if(aux->next!=NULL)
					{
						nod_nou->next = aux->next;
						aux->next = nod_nou;
					}
				else
					{
						aux->next = nod_nou;
					}
			}
		}
	}
	return lista;
}

nodMare *adauga_mic_mare(nodMare *lista, char nume[20], int cod) // Complexitate O(2*n) = O(n)  functia are o complexitate liniara
{
	nodMare *aux = lista;
	
	while(strcmp(aux->nume_depozit, nume)!=0)
		aux = aux->next;
	
	if(aux)
	{
	aux->sublista = adauga_ordonat_lista_mica(aux->sublista, cod);
	}
	return lista;
}

nodMic* stergere_mica(nodMic *lista, int cod)
{
	nodMic *aux = lista;
	if(aux!=NULL && aux->cod_palet == cod)
	{
		aux = aux->next;
		lista = aux;
	}
	else
	{
		while(aux!=NULL && aux->next!=NULL && aux->next->cod_palet!=cod)
		aux = aux->next;
		
		if(aux->next!=NULL)
		{
			aux->next = aux->next->next;
		}
	}
	return lista;
}

int gasit(nodMic *lista, int cod) //Complexitate O(n)
{
	nodMic *aux = lista;
	
	while(aux)
	{
		if(aux->cod_palet == cod)
			return 1;
		aux = aux->next;
	}
	
	return 0;
}

nodMare* stergere_palet(nodMare *lista, int cod) //Complexitate O(n^2)
{
	nodMare *aux = lista;
	while(aux)
	{
	while(gasit(aux->sublista, cod)==1)
	aux->sublista = stergere_mica(aux->sublista, cod);
	aux = aux->next;
	}
	return lista;
}

void afisare_lista_mare(nodMare *lista)
{
	nodMare *aux = lista;
	while(aux)
	{
		printf("Nume depozit: %s\n", aux->nume_depozit);
		if(aux->sublista!=NULL)
		{
			nodMic *aux2 = aux->sublista;
			while(aux2)
			{
			printf("Cod palet: %d \n", aux2->cod_palet);
			aux2=aux2->next;
			}
		}
		aux = aux->next;
	}
}

void free_liste(nodMare *lista)
{
	nodMare *aux = lista, *prev=NULL;
	
	while(aux)
	{
		prev = aux;
		aux=aux->next;
		if(prev->sublista!=NULL)
		{
			nodMic *aux2 = prev->sublista, *prev2=NULL;
			while(aux2)
			{
				prev2 = aux2;
				aux2 = aux2->next;
				free(prev2);
			}
		}
		free(prev);
	}
}

int main(){
	char nume[4][20]={"Alexia", "Lica", "Mio"};
	lista = adauga_final_lista_mare(lista, nume[0]);
	lista = adauga_final_lista_mare(lista, nume[1]);
	lista = adauga_final_lista_mare(lista, nume[2]);
	lista = adauga_mic_mare(lista, nume[0], 124);
	lista = adauga_mic_mare(lista, nume[0], 124);
	lista = adauga_mic_mare(lista, nume[0], 130);
	lista = adauga_mic_mare(lista, nume[1], 269);
	lista = adauga_mic_mare(lista, nume[2], 300);
	lista = adauga_mic_mare(lista, nume[2], 124);
	lista = stergere_palet(lista, 124);
	afisare_lista_mare(lista);
	free_liste(lista);
	
	return 0;
}
