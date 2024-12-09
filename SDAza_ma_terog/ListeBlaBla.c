#include<stdio.h>
#include<stdlib.h>

typedef struct nodMic{
	char info;
	struct nodMic *next;
	struct nodMic *last;
}nodMic;

typedef struct nodMare{
	int id;
	struct nodMare *next;
	nodMic *sublista[10];
}nodMare;

nodMare *lista=NULL;


nodMare* adauga_final_lista(nodMare *lista, int id)
{
	nodMare *new = (nodMare*)malloc(sizeof(nodMare));
	
	if(new)
	{
		new->id = id;
		new->next = NULL;
		new->sublista[id] = NULL;
		
		if(lista == NULL)
		{
			lista = new;
		}
		else
		{
			nodMare*aux = lista;
				while(aux->next)
					aux = aux->next;
				aux->next = new;
		}
	}
	return lista;
}

nodMare* adauga_final_nodmic(nodMare *lista, int id, char info)
{
	nodMic *new = (nodMic*)malloc(sizeof(nodMic));
	
	if(new)
	{
		new->info = info;
		new->next = NULL;
		
		
		nodMare* caca = lista;
		
		while(caca->id!=id)
			caca=caca->next;
			
		if(caca->sublista[id]==NULL)
		{
			caca->sublista[id] = new;
			
		}
		else
		{
			nodMic *aux = caca->sublista[id];
			while(aux->next)
				aux = aux->next;
			aux->next = new;
		}
	}
	return lista;
}

nodMare *sortare_sublista(nodMare *lista, int id)
{
	nodMare *auxLista = lista;
	while(auxLista->id!=id)
		auxLista = auxLista->next;
		
	if(auxLista->sublista[id]==NULL || auxLista->sublista[id]->next==NULL)
		return auxLista;
	else
	{
		int sortat;
		nodMic *aux = auxLista->sublista[id];
		
		do
		{
			sortat = 1;
			aux = auxLista->sublista[id];
			
			while(aux->next->next)
			{
				if(aux->info > aux->next->info){
					char temp = aux->info;
					aux->info = aux->next->info;
					aux->next->info = temp;
					sortat = 0;
				}
				aux = aux->next;
			}			
		}while(!sortat);
	}
	return lista;
}

void print_mare(nodMare *lista)
{
	nodMare *aux = lista;
	nodMic *aux2 = NULL;
	
	while(aux)
	{
		printf("%d : ", aux->id);
		if(aux->sublista[aux->id])
		{
			aux2 = aux->sublista[aux->id];
			while(aux2)
			{
				printf("%c ", aux2->info);
				aux2 = aux2->next;
			}
			printf("\n");
		}
		aux = aux->next;
		printf("\n");
	}
}

int main()
{
	lista = adauga_final_lista(lista, 0);
	lista = adauga_final_lista(lista, 1);
	lista = adauga_final_nodmic(lista, 0, 'C');
	lista = adauga_final_nodmic(lista, 0, 'B');
	lista = adauga_final_nodmic(lista, 1, 'X');
	lista = adauga_final_nodmic(lista, 1, 'Y');
	lista = adauga_final_nodmic(lista, 0, 'A');
	lista = adauga_final_nodmic(lista, 0, 'D');
	print_mare(lista);
	lista = sortare_sublista(lista, 0);
	print_mare(lista);
	return 0;
	
}
