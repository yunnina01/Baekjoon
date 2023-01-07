#include <stdio.h>

int arr[7], N, M;

void back(int cnt){
    int i;
    if(cnt == M){
        for(i = 0; i < M; i++)
            printf("%d ", arr[i]);
        puts("");
    }
    else{
        for(i = 1; i <= N; i++){
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