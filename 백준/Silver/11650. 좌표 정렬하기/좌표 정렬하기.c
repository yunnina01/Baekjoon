#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int x, y;
}Coord;

int compare(const void *a, const void *b){
    Coord *A = (Coord*)a;
    Coord *B = (Coord*)b;
    if(A->x < B->x)
        return -1;
    if(A->x > B->x)
        return 1;
    else{
        return A->y - B->y;
    }
}

int main(){
    int N, i;
    scanf("%d", &N);
    Coord arr[N];
    for(i = 0; i < N; i++)
        scanf("%d %d", &arr[i].x, &arr[i].y);
    qsort(arr, N, sizeof(Coord), compare);
    for(i = 0; i < N; i++)
        printf("%d %d\n", arr[i].x, arr[i].y);

    return 0;
}