#include <stdio.h>

int main(){
    int p[15][15], T, k, n, i, a, b;
    for(i = 0; i < 15; i++){
        p[0][i] = i + 1;
        p[i][0] = 1;
    }
    scanf("%d", &T);
    for(i = 0; i < T; i++){
        scanf("%d %d", &k, &n);
        for(a = 1; a <= k; a++){
            for(b = 1; b < n; b++)
                p[a][b] = p[a-1][b] + p[a][b-1];
        }
        printf("%d\n", p[k][n-1]);
    }

    return 0;
}