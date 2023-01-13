#include <stdio.h>

int arr[8], bt[8], visit[8], N, M;

void back(int cnt){
    int i, prev = 0;
    if(cnt == M){
        for(i = 0; i < M; i++)
            printf("%d ", bt[i]);
        puts("");
    }
    else{
        for(i = 0; i < N; i++){
            if(!visit[i] && prev != arr[i]){
                prev = arr[i];
                bt[cnt] = arr[i];
                visit[i]++;
                back(cnt + 1);
                visit[i]--;
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