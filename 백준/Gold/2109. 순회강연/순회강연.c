#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int p, d;
}LEC;

LEC lec[10000];
int heap[10001], size, res;

int desc(const void *a, const void *b){
    return ((LEC*)b)->d - ((LEC*)a)->d;
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
    int n, i, j;
    scanf("%d", &n);
    for(i = 0; i < n; i++)
        scanf("%d %d", &lec[i].p, &lec[i].d);
    qsort(lec, n, sizeof(LEC), desc);
    lec[n] = (LEC){0, 0};
    for(i = 0; i < n; i++){
        push(lec[i].p);
        for(j = lec[i].d; j > lec[i + 1].d; j--){
            if(size)
                res += pop();
        }
    }
    printf("%d\n", res);

    return 0;
}