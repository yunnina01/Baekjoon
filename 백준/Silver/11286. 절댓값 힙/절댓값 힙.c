#include <stdio.h>
#include <stdlib.h>

int heap[100000];

void swap(int a, int b){
    int temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
}

void heapify(int idx, int size){
    int left = idx * 2 + 1, right = (idx + 1) * 2, min = idx;
    if(left < size){
        if(abs(heap[left]) < abs(heap[min]))
            min = left;
        else if(abs(heap[left]) == abs(heap[min]) && heap[left] < heap[min])
            min = left;
    }
    if(right < size){
        if(abs(heap[right]) < abs(heap[min]))
            min = right;
        else if(abs(heap[right]) == abs(heap[min]) && heap[right] < heap[min])
            min = right;
    }
    if(min != idx){
        swap(idx, min);
        heapify(min, size);
    }
}

int main(){
    int N, idx, size = 0;
    scanf("%d", &N);
    while(N--){
        scanf("%d", &heap[size]);
        if(heap[size]){
            idx = size++;
            do{
                idx = (idx - 1) / 2;
                heapify(idx, size);
            }while(idx);
        }
        else{
            if(!size)
                puts("0");
            else{
                printf("%d\n", heap[0]);
                swap(0, --size);
                heapify(0, size);
            }
        }
    }

    return 0;
}