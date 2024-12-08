#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct nod{
	char id[10];
	char nume_pasager[40];
	struct nod* next;
}nod;

typedef struct nodMare{
	short int index;
	struct nodMare *next;
	nod* zbor[10];
}nodMare;

nodMare *lista=NULL;
nodMare *ultim_lista=NULL;

nod* rezervare_final(nod *zbor, char id[10], char nume_pasager[40])
{
	nod* new = (nod*)malloc(sizeof(nod));
	
	if(new)
	{
		strcpy(new->id, id);
		strcpy(new->nume_pasager, nume_pasager);
		new->next = NULL;
		
		if(zbor == NULL)
			zbor = new;
		else
		{
		nod* aux = zbor;
		while(aux->next)
			aux = aux->next;
			
		aux->next = new;
		}
	}
	return zbor;
}


nod* anulare_rezervare(nod* zbor, char id[10])
{
	nod* aux = zbor;
	nod* aux2 = zbor;
	
	while(aux2->next)
		aux2 = aux2->next;
	nod* ultim = aux2;
	
	if(strcmp(aux->id, id)==0)
	{
		aux = aux->next;
		zbor = aux;
	}
	else
	if(strcmp(ultim->id, id)==0)
	{
		while(aux->next->next!=NULL)
			aux = aux->next;
		aux->next = NULL;
	}
	else
	{
		while(strcmp(aux->next->id, id)!=0)
			aux=aux->next;
			
		aux->next = aux->next->next;	
	}
	return zbor;
}

nodMare* adaugare_zbor(nodMare* lista, short int i)
{
	nodMare *nod_nou = (nodMare*)malloc(sizeof(nodMare));
	
	if(nod_nou)
	{
		nod_nou->index = i;
		nod_nou->next = NULL;
		nod_nou->zbor[i] = NULL;
		
		if(ultim_lista == NULL)
		{
			lista = nod_nou;
		}
		else
		{
			ultim_lista->next = nod_nou;
		}
		ultim_lista = nod_nou;
		
	}
	return lista;
	
}

void print_zbor(nod* zbor)
{
	nod* aux = zbor;
	
	while(aux)
	{
		printf("ID: %s\nNume: %s\n", aux->id, aux->nume_pasager);
		aux = aux->next;
	}
	printf("\n");
}

void print_mare(nodMare* lista)
{
	nodMare* aux = lista;
	while(aux)
	{
		printf("%hd \n", aux->index);
		if(aux->zbor[aux->index])
		{
			nod* aux2 = aux->zbor[aux->index];
			print_zbor(aux2);
		}
		
		aux = aux->next;
	}
	printf("\n");
}

nodMare* adaugare_rezervare_zbor(nodMare *lista, short int i, char id[10], char nume_pasager[40])
{
		nodMare* aux = lista;
		
		while(aux!=NULL)
	{	
		if(aux->index == i)
		aux->zbor[i] = rezervare_final(aux->zbor[i], id, nume_pasager);
		
		aux = aux->next;
	}
		return lista;
}

int main()
{
	char id[10], nume_pasager[40];
	
	strcpy(id, "12345");
	strcpy(nume_pasager, "Popescu Ion");
	/*zbor = rezervare_final(zbor, id, nume_pasager);
	strcpy(id, "23456");
	strcpy(nume_pasager, "Ampu Lamare");
	zbor = rezervare_final(zbor, id, nume_pasager);
	strcpy(id, "34567");
	strcpy(nume_pasager, "Zoltan Maria");
	zbor = rezervare_final(zbor, id, nume_pasager);
	strcpy(id, "45678");
	strcpy(nume_pasager, "Cocean Marius");
	zbor = rezervare_final(zbor, id, nume_pasager);
	print_zbor(zbor);
	strcpy(id, "12345");
	zbor = anulare_rezervare(zbor, id);
	print_zbor(zbor);*/
	
	lista = adaugare_zbor(lista,0);
	lista = adaugare_rezervare_zbor(lista, 0, id, nume_pasager);
	strcpy(id, "23456");
	strcpy(nume_pasager, "Cock Maximus");
	lista = adaugare_zbor(lista, 1);
	lista = adaugare_rezervare_zbor(lista, 1, id, nume_pasager);
	print_mare(lista);
	return 0;
}
