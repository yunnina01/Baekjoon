#include <stdio.h>

int main(){
    char arr[246913] = {1, 1};
    int i, j, n, cnt;
    for(i = 2; i < 246913 / i; i++){
        if(arr[i])
            continue;
        for(j = i * i; j < 246913; j += i)
            arr[j] = 1;
    }
    while(1){
        scanf("%d", &n);
        if(n == 0)
            break;
        cnt = 0;
        for(i = n + 1; i <= n * 2; i++){
            if(arr[i] == 0)
                cnt++;
        }
        printf("%d\n", cnt);
    }

    return 0;
}