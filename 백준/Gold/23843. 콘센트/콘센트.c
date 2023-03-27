#include <stdio.h>
#include <stdlib.h>

int elec[10000], heap[11], size;

int desc(const void *a, const void *b){
    return *(int*)b - *(int*)a;
}

void push(int item){
    int i = ++size;
    while(i != 1 && item < heap[i / 2]){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

int pop(){
    int parent = 1, child = 2;
    int min = heap[1], temp = heap[size--];
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
    return min;
}

int main(){
    int N, M, i;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++)
        scanf("%d", &elec[i]);
    qsort(elec, N, sizeof(int), desc);
    for(i = 0; i < N; i++){
        if(size == M)
            push(pop() + elec[i]);
        else
            push(elec[i]);
    }
    while(size > 1)
        pop();
    printf("%d\n", heap[1]);

    return 0;
}