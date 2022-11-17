#include <stdio.h>

int main(){
    char arr[10001] = {1, 1};
    int T, i, j, n;
    for(i = 2; i < 10001 / i; i++){
        if(arr[i])
            continue;
        for(j = i * i; j < 10001; j += i)
            arr[j] = 1;
    }
    scanf("%d", &T);
    for(i = 0; i < T; i++){
        scanf("%d", &n);
        for(j = n / 2; j > 1; j--){
            if(arr[j] == 0 && arr[n - j] == 0)
                break;
        }
        printf("%d %d\n", j, n - j);
    }

    return 0;
}