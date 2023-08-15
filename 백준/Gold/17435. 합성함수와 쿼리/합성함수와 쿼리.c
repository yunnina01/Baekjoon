#include <stdio.h>

int f[20][200001];

int main(){
    int m, q, n, x, i, j;
    scanf("%d", &m);
    for(i = 1; i <= m; i++)
        scanf("%d", &f[0][i]);
    for(i = 0; i < 19; i++){
        for(j = 1; j <= m; j++)
            f[i + 1][j] = f[i][f[i][j]];
    }
    scanf("%d", &q);
    while(q--){
        scanf("%d %d", &n, &x);
        for(i = 0; n; i++){
            if(n % 2)
                x = f[i][x];
            n >>= 1;
        }
        printf("%d\n", x);
    }

    return 0;
}