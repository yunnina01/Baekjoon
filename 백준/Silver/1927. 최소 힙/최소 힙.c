#include <stdio.h>

int heap[100001], size;

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
    int N, n;
    scanf("%d", &N);
    while(N--){
        scanf("%d", &n);
        if(n)
            push(n);
        else{
            if(size)
                printf("%d\n", pop());
            else
                puts("0");
        }
    }

    return 0;
}