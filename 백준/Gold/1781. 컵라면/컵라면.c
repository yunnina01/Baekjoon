#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int d, c;
}PRO;

PRO pro[200000];
int heap[200001], size, res;

int desc(const void *a, const void *b){
    return ((PRO*)b)->d - ((PRO*)a)->d;
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
    int N, i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d", &pro[i].d, &pro[i].c);
    qsort(pro, N, sizeof(PRO), desc);
    pro[N] = (PRO){0, 0};
    for(i = 0; i < N; i++){
        push(pro[i].c);
        for(j = pro[i].d; j > pro[i + 1].d; j--){
            if(size)
                res += pop();
        }
    }
    printf("%d\n", res);

    return 0;
}