#include <stdio.h>
#include <stdlib.h>

int arr[20][20], check[20], N;
int min = 10000;

void back(int prev, int cnt){
    if(cnt == N / 2){
        int start[N / 2], link[N / 2];
        int i, j, s_cnt = 0, l_cnt = 0, s_sum = 0, l_sum = 0;
        for(i = 0; i < N; i++){
            if(check[i])
                start[s_cnt++] = i;
            else
                link[l_cnt++] = i;
        }
        for(i = 0; i < N / 2; i++){
            for(j = 0; j < N / 2; j++){
                s_sum += arr[start[i]][start[j]];
                l_sum += arr[link[i]][link[j]];
            }
        }
        if(abs(s_sum - l_sum) < min)
            min = abs(s_sum - l_sum);
    }
    else{
        for(int i = prev; i < N; i++){
            if(!check[i]){
                check[i]++;
                back(i + 1, cnt + 1);
                check[i]--;
            }
        }
    }
}

int main(){
    int i, j;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        for(j = 0; j < N; j++)
            scanf("%d", &arr[i][j]);
    }
    check[0] = 1;
    back(1, 1);
    printf("%d\n", min);

    return 0;
}