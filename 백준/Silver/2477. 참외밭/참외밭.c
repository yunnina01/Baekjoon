#include <stdio.h>

int main(){
    int arr[6][2], cnt[5] = {}, K, i, b = 1, s = 1;
    scanf("%d", &K);
    for(i = 0; i < 6; i++){
        scanf("%d %d", &arr[i][0], &arr[i][1]);
        cnt[arr[i][0]]++;
    }
    for(i = 0; i < 6; i++){
        if(cnt[arr[i][0]] == 1){
            b *= arr[i][1];
            s *= arr[(i + 3) % 6][1];
        }
    }
    printf("%d\n", (b - s) * K);

    return 0;
}