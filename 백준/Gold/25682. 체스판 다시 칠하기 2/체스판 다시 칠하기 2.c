#include <stdio.h>

char ch[2000][2001];
int ps[2001][2001], N, M, K;

int min(int a, int b){
    return  a < b ? a : b;
}

int count(char color){
    int i, j, chk, cnt = 2e6;
    for(i = 0; i < N; i++){
        for(j = 0; j < M; j++){
            if((i + j) % 2 == 0)
                chk = ch[i][j] != color ? 1 : 0;
            else
                chk = ch[i][j] == color ? 1 : 0;
            ps[i + 1][j + 1] = ps[i + 1][j] + ps[i][j + 1] - ps[i][j] + chk;
        }
    }
    for(i = K; i <= N; i++){
        for(j = K; j <= M; j++)
            cnt = min(cnt, ps[i][j] - ps[i - K][j] - ps[i][j - K] + ps[i - K][j - K]);
    }
    return cnt;
}

int main(){
    int i, cntB, cntW;
    scanf("%d %d %d", &N, &M, &K);
    for(i = 0; i < N; i++)
        scanf("%s", &ch[i]);
    cntB = count('B');
    cntW = count('W');
    printf("%d\n", min(cntB, cntW));

    return 0;
}