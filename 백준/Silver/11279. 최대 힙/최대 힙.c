#include <stdio.h>

int heap[100000], idx;

void swap(int a, int b){
    int temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
}

void heapify(int idx, int size){
    int left = idx * 2 + 1, right = (idx + 1) * 2, max = idx;
    if(left < size && heap[left] > heap[max])
        max = left;
    if(right < size && heap[right] > heap[max])
        max = right;
    if(idx != max){
        swap(idx, max);
        heapify(max, size);
    }
}

int main(){
    int N, i;
    scanf("%d", &N);
    while(N--){
        scanf("%d", &heap[idx]);
        if(heap[idx]){
            i = idx++;
            do{
                i = (i - 1) / 2;
                heapify(i, idx);
            }while(i);
        }
        else{
            if(!idx)
                puts("0");
            else{
                printf("%d\n", heap[0]);
                swap(0, --idx);
                heapify(0, idx);
            }
        }
    }

    return 0;
}