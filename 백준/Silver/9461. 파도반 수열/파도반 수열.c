#include <stdio.h>

long long arr[100] = {1, 1, 1};

int main(){
    int T, N, i;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &N);
        for(i = 3; i < N; i++){
            if(arr[i])
                continue;
            arr[i] = arr[i - 3] + arr[i - 2];
        }
        printf("%lld\n", arr[N - 1]);
    }

    return 0;
}