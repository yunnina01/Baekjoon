#include <stdio.h>
#define ll long long

ll heap[1000001];
int size;

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
    int T, K, n;
    ll s, res;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &K);
        res = size = 0;
        while(K--){
            scanf("%d", &n);
            push(n);
        }
        while(size > 1){
            s = pop() + pop();
            res += s;
            push(s);
        }
        printf("%lld\n", res);
    }

    return 0;
}