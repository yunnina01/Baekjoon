#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int s, e;
}LEC;

LEC lec[100000];
int heap[100001], size;

int asc(const void *a, const void *b){
    return ((LEC*)a)->s - ((LEC*)b)->s;
}

void push(int item){
    int i = ++size;
    while(i != 1 && item < heap[i / 2]){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

void pop(){
    int parent = 1, child = 2, temp = heap[size--];
    while(child <= size){
        if(child < size && heap[child] > heap[child + 1])
            child++;
        if(temp <= heap[child])
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
}

int main(){
    int N, i;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d", &lec[i].s, &lec[i].e);
    qsort(lec, N, sizeof(LEC), asc);
    push(lec[0].e);
    for(i = 1; i < N; i++){
        if(lec[i].s >= heap[1])
            pop();
        push(lec[i].e);
    }
    printf("%d\n", size);

    return 0;
}