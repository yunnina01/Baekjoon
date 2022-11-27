#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int compare(const void *a, const void *b){
    char *A = (char*)a;
    char *B = (char*)b;
    int lenA = strlen(A), lenB = strlen(B);
    if(lenA < lenB)
        return -1;
    else if (lenA > lenB)
        return 1;
    return strcmp(A, B);
}

int main(){
    int N, i;
    scanf("%d", &N);
    char a[N][51];
    for(i = 0; i < N; i++)
        scanf("%s", a[i]);
    qsort(a, N, sizeof(a[0]), compare);
    puts(a[0]);
    for(i = 1; i < N; i++){
        if(strcmp(a[i - 1], a[i]))
            puts(a[i]);
    }

    return 0;
}