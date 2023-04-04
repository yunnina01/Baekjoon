#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int p, q;
}COM;

typedef struct{
    int q, n;
}IDX;

COM com[100000];
IDX heap[100001];
int seat[100001], res[100001], heap_size, seat_size, ans;

int asc(const void *a, const void *b){
    return ((COM*)a)->p - ((COM*)b)->p;
}

void heap_push(IDX item){
    int i = ++heap_size;
    while(i != 1 && item.q < heap[i / 2].q){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

int heap_pop(){
    int parent = 1, child = 2, min = heap[1].n;
    IDX temp = heap[heap_size--];
    while(child <= heap_size){
        if(child < heap_size && heap[child].q > heap[child + 1].q)
            child++;
        if(temp.q <= heap[child].q)
            break;
        heap[parent] = heap[child];
        parent = child;
        child *= 2;
    }
    heap[parent] = temp;
    return min;
}

void seat_push(int item){
    int i = ++seat_size;
    while(i != 1 && item < seat[i / 2]){
        seat[i] = seat[i / 2];
        i /= 2;
    }
    seat[i] = item;
}

int seat_pop(){
    int parent = 1, child = 2, min = seat[1], temp = seat[seat_size--];
    while(child <= seat_size){
        if(child < seat_size && seat[child] > seat[child + 1])
            child++;
        if(temp <= seat[child])
            break;
        seat[parent] = seat[child];
        parent = child;
        child *= 2;
    }
    seat[parent] = temp;
    return min;
}

int main(){
    int N, i, cur;
    scanf("%d", &N);
    for(i = 0; i < N; i++)
        scanf("%d %d", &com[i].p, &com[i].q);
    qsort(com, N, sizeof(COM), asc);
    for(i = 0; i < N; i++){
        while(heap_size && com[i].p > heap[1].q)
            seat_push(heap_pop());
        if(seat_size)
            cur = seat_pop();
        else{
            cur = heap_size + 1;
            if(cur > ans)
                ans = cur;
        }
        heap_push((IDX){com[i].q, cur});
        res[cur]++;
    }
    printf("%d\n", ans);
    for(i = 1; i <= ans; i++)
        printf("%d ", res[i]);

    return 0;
}