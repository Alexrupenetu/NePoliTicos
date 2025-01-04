#include <stdio.h>
#include <time.h>
int cautare_liniara(int v[], int n, int val)
{
    for (int i = 0; i < n; i++) 
        if (v[i] == val) 
            return i; 
    return -1; 
}

int cautare_binara(int v[], int n, int val)
{
    int st, dr, mijloc, ok=0;

    st=0; dr=n-1;

    while(st<=dr && ok==0)
    {
        mijloc=(st+dr)/2;

        if(val==v[mijloc])
            ok=mijloc;
        else
        if(val<v[mijloc])
            dr=mijloc-1;
        else
            st=mijloc+1;
    }

    return ok;
}
int main() {
    int n = 300000; // Dimensiunea tabloului
    int v[n];
    for (int i = 0; i < n; i++) {
        v[i] = i; 
    }

    int val = 29999; 

    // Măsurăm timpul pentru căutare liniară
    clock_t start1 = clock();
    int rez1 = cautare_liniara(v, n, val);
    clock_t end1 = clock();

    // Măsurăm timpul pentru căutare binară
    clock_t start2 = clock();
    int rez2 = cautare_binara(v, n, val);
    clock_t end2 = clock();

    // Afișare rezultate
    if (rez1 != -1) {
        printf("Căutare liniară: Element găsit la poziția %d.\n", rez1);
    } else {
        printf("Căutare liniară: Elementul nu a fost găsit.\n");
    }
    printf("Timp căutare liniară: %.6f secunde.\n", (double)(end1 - start1) / CLOCKS_PER_SEC);

    if (rez2 != -1) {
        printf("Căutare binară: Element găsit la poziția %d.\n", rez2);
    } else {
        printf("Căutare binară: Elementul nu a fost găsit.\n");
    }
    printf("Timp căutare binară: %.6f secunde.\n", (double)(end2 - start2) / CLOCKS_PER_SEC);

    return 0;
}
