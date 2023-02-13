#include <stdio.h>

int ps[1024][1025];

int main(){
    int N, M, i, j, x1, x2, y1, y2, res;
    scanf("%d %d", &N, &M);
    for(i = 0; i < N; i++){
        for(j = 1; j <= N; j++){
            scanf("%d", &ps[i][j]);
            ps[i][j] += ps[i][j - 1];
        }
    }
    while(M--){
        res = 0;
        scanf("%d %d %d %d", &x1, &y1, &x2, &y2);
        for(i = x1 - 1; i < x2; i++)
            res += ps[i][y2] - ps[i][y1 - 1];
        printf("%d\n", res);
    }

    return 0;
}