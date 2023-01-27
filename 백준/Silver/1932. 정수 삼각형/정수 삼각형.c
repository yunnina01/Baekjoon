#include <stdio.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    int N, i, j, res = 0;
    scanf("%d", &N);
    int tri[N][N];
    scanf("%d", &tri[0][0]);
    for(i = 1; i < N; i++){
        for(j = 0; j <= i; j++){
            scanf("%d", &tri[i][j]);
            if(!j)
                tri[i][j] += tri[i - 1][j];
            else if(j == i)
                tri[i][j] += tri[i - 1][j - 1];
            else
                tri[i][j] += max(tri[i - 1][j - 1], tri[i - 1][j]);
        }
    }
    for(i = 0; i < N; i++){
        if(tri[N - 1][i] > res)
            res = tri[N - 1][i];
    }
    printf("%d\n", res);

    return 0;
}