#include <stdio.h>

int arr[4000001], dec[300000], idx, cnt;

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 2; i <= N; i++){
        if(!arr[i]){
            dec[idx++] = i;
            for(j = i * 2; j <= N; j += i)
                arr[j] = 1;
        }
    }
    int l = -1, r = -1, val = 0;
    while(r < idx){
        if(val == N){
            cnt++;
            val -= dec[++l];
            val += dec[++r];
        }
        else if(val > N)
            val -= dec[++l];
        else
            val += dec[++r];
    }
    printf("%d\n", cnt);

    return 0;
}