#include<stdio.h>

int putere(int baza, int exp)
{
	int rez=baza;
	if(exp==0)	
		return 1;
	else
	{
	while(exp!=1)
	{
		rez *= baza;
		exp--;
	}
	return rez;
	}
}

int an_bisect(int an) 
{
    if ((an % 4 == 0 && an % 100 != 0) || (an % 400 == 0)) 
        return 1; 
    else 
        return 0; 
}

unsigned long long factorial(int n)
{
	if(n<0)
	{
	printf("Functia factorial nu e definita pe multimea nr negative!");
	return 0;
	}
	if(n==0)
		return 1;
		
	unsigned long long rezultat = 1;
	for(int i=1; i<=n; i++)
		rezultat *= i;
	
	return rezultat;
}

int exista_duplicate(int v[])
{
	int p = v[0], q = v[0];
	
	while(1)
	{
		p = v[p];
		q = v[v[q]];
		if(p == q)
			return 1;
	}
	
	return 0;
}


int main(){
	int v[] = {2, 4, 5, 6, 7};
	printf("%d\n", putere(2, 4));
	printf("Exista duplicate: %d\n", exista_duplicate(v));
	return 0;
}
