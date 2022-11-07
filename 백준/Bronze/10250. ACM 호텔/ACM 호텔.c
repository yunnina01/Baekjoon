#include <stdio.h>

int main(){
    int T, N, H, W, i, x, y;
    scanf("%d", &T);
    for(i = 0; i < T; i++){
        scanf("%d %d %d", &H, &W, &N);
        y = N % H;
        x = N / H;
        if(y != 0)
            x++;
        else
            y = H;
        printf("%d\n", y * 100 + x);
    }

    return 0;
}