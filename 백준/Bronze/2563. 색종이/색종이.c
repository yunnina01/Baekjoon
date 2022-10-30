#include <stdio.h>

int main(){
    char a[102][102] = {}, i, j, k, n;
    int r = 0, x, y;
    scanf("%d", &n);
    for(i = 0; i < n; i++){
        scanf("%d %d", &x, &y);
        for(j = y; j < y + 10; j++){
            for(k = x; k < x + 10; k++)
                a[k][j] = 1;
        }
    }
    for(i = 0; i < 101; i++){
        for(j = 0; j < 101; j++){
            if(a[i][j] != 0)
                r++;
        }
    }
    printf("%d\n", r);
    
    return 0;
}