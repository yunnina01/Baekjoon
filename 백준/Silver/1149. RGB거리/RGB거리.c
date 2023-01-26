#include <stdio.h>

int min(int a, int b){
    return a < b ? a : b;
}

int main(){
    int N, i;
    scanf("%d", &N);
    int rgb[N][3], price[N][3];
    for(i = 0; i < N; i++)
        scanf("%d %d %d", &rgb[i][0], &rgb[i][1], &rgb[i][2]);
    price[0][0] = rgb[0][0], price[0][1] = rgb[0][1], price[0][2] = rgb[0][2];
    for(i = 1; i < N; i++){
        price[i][0] = min(price[i - 1][1], price[i - 1][2]) + rgb[i][0];
        price[i][1] = min(price[i - 1][0], price[i - 1][2]) + rgb[i][1];
        price[i][2] = min(price[i - 1][0], price[i - 1][1]) + rgb[i][2];
    }
    printf("%d\n", min(min(price[N - 1][0], price[N - 1][1]), price[N - 1][2]));

    return 0;
}