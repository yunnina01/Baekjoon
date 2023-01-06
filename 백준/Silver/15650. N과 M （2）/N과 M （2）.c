#include <stdio.h>

void back(int *arr, int cnt, int N, int M){
    int i, j, check;
    if(cnt == M){
        for(i = 0; i < M; i++)
            printf("%d ", arr[i]);
        puts("");
    }
    else{
        for(i = cnt + 1; i <= N; i++){
            check = 0;
            for(j = 0; j < cnt; j++){
                if(arr[j] >= i)
                    check = 1;
            }
            if(!check){
                arr[cnt] = i;
                back(arr, cnt + 1, N, M);
            }
        }
    }
}

int main(){
    int N, M;
    scanf("%d %d", &N, &M);
    int arr[N];
    back(arr, 0, N, M);

    return 0;
}