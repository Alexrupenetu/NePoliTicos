#include<stdio.h>
#include<stdlib.h>
#include<string.h>

typedef struct nod_opere{
	char Titlu[50];
	struct nod_opere *next;
}nod_opere;

typedef struct lista_opere{
	nod_opere *first;
	nod_opere *last;
}lista_opere;

typedef struct nod_autori{
	char NumeAutor[40];
	struct lista_opere *opere;
	struct nod_autori *next;
}nod_autori;

typedef struct lista_autori{
	nod_autori *first;
	nod_autori *last;
	char litera;
	int size;
}lista_autori;

typedef struct nod_catalog{
	struct lista_autori *catalog[26];
	int size;
}nod_catalog;

nod_catalog *c=NULL;

void init_catalog()
{
	c = (nod_catalog*)malloc(sizeof(nod_catalog));
	if(c == NULL)
	{
		printf("Eroare la alocarea memoriei!\n");
		exit(1);
	}
	else
	{
		for(int i=0; i<26; i++)
		{
			c->catalog[i] = (lista_autori*)malloc(sizeof(lista_autori));
			if (c->catalog[i] == NULL)
			{
			    printf("Eroare la alocarea memoriei pentru coada de prioritate %d\n", i);
			    exit(1);
			}
			c->catalog[i]->litera = 'A' + i;	
			c->catalog[i]->first = NULL;
			c->catalog[i]->last = NULL;	
		}
	}
}

void adaugare_catalog(nod_catalog *c, char NumeAutor[40])
{
	nod_autori *to_insert = (nod_autori*)malloc(sizeof(nod_autori));
	strcpy(to_insert->NumeAutor, NumeAutor);
	to_insert->next = NULL;
	
	int index = NumeAutor[0] - 65;
	
	if(c->catalog[index]->first==NULL)
	{
		c->catalog[index]->first = to_insert;
		c->catalog[index]->last = to_insert;
	}
	else
	{
		c->catalog[index]->last->next = to_insert;
		c->catalog[index]->last = to_insert;
	}
}

void print_catalog(nod_catalog *c)
{
	for(int i=0; i<26; i++)
		if(c->catalog[i]->first!=NULL)
		{
			printf("%c : ", c->catalog[i]->litera);
			nod_autori *aux = c->catalog[i]->first;
			while(aux)
			{
				printf("%s ", aux->NumeAutor);
				aux = aux->next;
			}
			printf("\n");
		}
}


int main()
{
	char nume[40], nume2[40];
	init_catalog();
	strcpy(nume, "Ion marinescu");
	adaugare_catalog(c,nume );
	strcpy(nume, "Alex tunea");
	adaugare_catalog(c, nume);
	print_catalog(c);
	return 0;
}
