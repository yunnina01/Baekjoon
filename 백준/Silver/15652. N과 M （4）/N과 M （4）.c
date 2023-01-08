#include <stdio.h>

int arr[8], N, M;

void back(int cnt){
    int i, prev = 1;
    if(cnt == M){
        for(i = 0; i < M; i++)
            printf("%d ", arr[i]);
        puts("");
    }
    else{
        if(cnt)
            prev = arr[cnt - 1];
        for(i = prev; i <= N; i++){
            arr[cnt] = i;
            back(cnt + 1);
        }
    }
}

int main(){
    scanf("%d %d", &N, &M);
    back(0);

    return 0;
}