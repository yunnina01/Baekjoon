#include <stdio.h>
#include <stdlib.h>

int queue[10000], arr[100];

int desc(const void *a, const void *b){
    return *(int*)b - *(int*)a;
}

int main(){
    int T, N, M, i;
    scanf("%d", &T);
    while(T--){
        scanf("%d %d", &N, &M);
        int front = 0, rear = 0, cnt = 1;
        for(i = 0; i < N; i++){
            scanf("%d", &arr[i]);
            queue[rear++] = arr[i];
        }
        qsort(arr, N, sizeof(int), desc);
        for(i = 0;;){
            if(queue[front] == arr[i]){
                if(front++ == M)
                    break;
                cnt++, i++;
            }
            else{
                if(front == M)
                    M = rear;
                queue[rear++] = queue[front++];
            }
        }
        printf("%d\n", cnt);
    }

    return 0;
}