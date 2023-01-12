#include <stdio.h>

int arr[8], bt[8], N, M;

void back(int prev, int cnt){
    int i;
    if(cnt == M){
        for(i = 0; i < M; i++)
            printf("%d ", bt[i]);
        puts("");
    }
    else{
        for(i = prev; i < N; i++){
            bt[cnt] = arr[i];
            back(i, cnt + 1);
        }
    }
}

int main(){
    int i, j, temp;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    for(i = 1; i <= N; i++){
        for(j = 0; j < N - i; j++){
            if(arr[j] > arr[j + 1]){
                temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    back(0, 0);

    return 0;
}