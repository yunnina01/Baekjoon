#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int m, v;
}JEW;

JEW jew[300000];
int bag[300000], heap[300001], size;
long long res;

int jew_asc(const void *a, const void *b){
    return ((JEW*)a)->m - ((JEW*)b)->m;
}

int bag_asc(const void *a, const void *b){
    return *(int*)a - *(int*)b;
}

void push(int item){
    int i = ++size;
    while(i != 1 && item > heap[i / 2]){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

int pop(){
    int parent = 1, child = 2, max = heap[1], temp = heap[size--];
    while(child <= size){
        if(child < size && heap[child] < heap[child + 1])
            child++;
        if(temp >= heap[child])
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
    return max;
}

int main(){
    int N, K, i, j;
    scanf("%d %d", &N, &K);
    for(i = 0; i < N; i++)
        scanf("%d %d", &jew[i].m, &jew[i].v);
    for(i = 0; i < K; i++)
        scanf("%d", &bag[i]);
    qsort(jew, N, sizeof(JEW), jew_asc);
    qsort(bag, K, sizeof(int), bag_asc);
    for(i = j = 0; i < K; i++){
        while(j < N && jew[j].m <= bag[i])
            push(jew[j++].v);
        if(size)
            res += pop();
    }
    printf("%lld\n", res);

    return 0;
}