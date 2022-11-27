#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int age, idx;
    char name[101];
}Mem;

int compare(const void *a, const void *b){
    Mem *A = (Mem*)a;
    Mem *B = (Mem*)b;
    if(A->age == B->age)
        return A->idx - B->idx;
    else
        return A->age - B->age;
}

int main(){
    int N, i;
    scanf("%d", &N);
    Mem arr[N];
    for(i = 0; i < N; i++){
        scanf("%d %s", &arr[i].age, arr[i].name);
        arr[i].idx = i;
    }
    qsort(arr, N, sizeof(Mem), compare);
    for(i = 0; i < N; i++)
        printf("%d %s\n", arr[i].age, arr[i].name);

    return 0;
}