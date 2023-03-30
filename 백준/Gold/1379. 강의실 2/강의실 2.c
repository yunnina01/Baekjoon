#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int n, s, e;
}LEC;

typedef struct{
    int e, c;
}IDX;

LEC lec[100000];
IDX heap[100001];
int res[100001], size;

int asc(const void *a, const void *b){
    return ((LEC*)a)->s - ((LEC*)b)->s;
}

void push(IDX item){
    int i = ++size;
    while(i != 1 && item.e < heap[i / 2].e){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

IDX pop(){
    int parent = 1, child = 2;
    IDX min = heap[1], temp = heap[size--];
    while(child <= size){
        if(child < size && heap[child].e > heap[child + 1].e)
            child++;
        if(temp.e <= heap[child].e)
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
    return min;
}

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d %d", &lec[i].n, &lec[i].s, &lec[i].e);
    qsort(lec, N, sizeof(LEC), asc);
    push((IDX){lec[0].e, 1});
    res[lec[0].n] = 1;
    for(i = 1; i < N; i++){
        if(lec[i].s < heap[1].e){
            push((IDX){lec[i].e, size + 1});
            res[lec[i].n] = size;
        }
        else{
            IDX min = pop();
            push((IDX){lec[i].e, min.c});
            res[lec[i].n] = min.c;
        }
    }
    printf("%d\n", size);
    for(i = 1; i <= N; i++)
        printf("%d\n", res[i]);

    return 0;
}