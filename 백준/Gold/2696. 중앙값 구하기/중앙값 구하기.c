#include <stdio.h>

int max_heap[5001], min_heap[5001], res[5000], max_size, min_size;

void max_push(int item){
    int i = ++max_size;
    while(i != 1 && item > max_heap[i / 2]){
        max_heap[i] = max_heap[i / 2];
        i /= 2;
    }
    max_heap[i] = item;
}

void min_push(int item){
    int i = ++min_size;
    while(i != 1 && item < min_heap[i / 2]){
        min_heap[i] = min_heap[i / 2];
        i /= 2;
    }
    min_heap[i] = item;
}

int max_pop(){
    int parent = 1, child = 2, max = max_heap[1], temp = max_heap[max_size--];
    while(child <= max_size){
        if(child < max_size && max_heap[child] < max_heap[child + 1])
            child++;
        if(temp >= max_heap[child])
            break;
        max_heap[parent] = max_heap[child];
        parent = child;
        child *= 2;
    }
    max_heap[parent] = temp;
    return max;
}

int min_pop(){
    int parent = 1, child = 2, min = min_heap[1], temp = min_heap[min_size--];
    while(child <= min_size){
        if(child < min_size && min_heap[child] > min_heap[child + 1])
            child++;
        if(temp <= min_heap[child])
            break;
        min_heap[parent] = min_heap[child];
        parent = child;
        child *= 2;
    }
    min_heap[parent] = temp;
    return min;
}

int main(){
    int T, M, i, n, max, min, cnt;
    scanf("%d", &T);
    while(T--){
        max_size = min_size = cnt = 0;
        scanf("%d", &M);
        for(i = 1; i <= M; i++){
            scanf("%d", &n);
            if(max_size == min_size)
                max_push(n);
            else
                min_push(n);
            if(max_size && min_size && max_heap[1] > min_heap[1]){
                max = max_pop(), min = min_pop();
                max_push(min);
                min_push(max);
            }
            if(i % 2)
                res[cnt++] = max_heap[1];
        }
        printf("%d\n", cnt);
        for(i = 0; i < cnt; i++){
            if(i && i % 10 == 0)
                puts("");
            printf("%d ", res[i]);
        }
        puts("");
    }

    return 0;
}