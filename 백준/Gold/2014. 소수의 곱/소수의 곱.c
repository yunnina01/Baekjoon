#include <stdio.h>
#define ll long long

ll heap[1000001];
int prime[100], size;

void push(ll item){
    int i = ++size;
    while(i != 1 && item < heap[i / 2]){
        heap[i] = heap[i / 2];
        i /= 2;
    }
    heap[i] = item;
}

ll pop(){
    int parent = 1, child = 2;
    ll min = heap[1], temp = heap[size--];
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
    int K, N, i, j;
    ll x;
    scanf("%d %d", &K, &N);
    for(i = 0; i < K; i++){
        scanf("%lld", &prime[i]);
        push(prime[i]);
    }
    for(i = 0; i < N; i++){
        x = pop();
        for(j = 0; j < K; j++){
            push(x * prime[j]);
            if(x % prime[j] == 0)
                break;
        }
    }
    printf("%lld\n", x);

    return 0;
}