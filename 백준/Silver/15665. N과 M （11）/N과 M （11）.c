#include <stdio.h>

int arr[7], bt[7], N, M;

void back(int cnt){
    int i;
    if(cnt == M){
        for(i = 0; i < M; i++)
            printf("%d ", bt[i]);
        puts("");
    }
    else{
        for(i = 0; i < N; i++){
            if(bt[cnt] != arr[i]){
                bt[cnt] = arr[i];
                back(cnt + 1);
            }
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
    back(0);

    return 0;
}