#include <stdio.h>
#include <string.h>

int dp[225][1 << 14], N, M;
char str[210], s[15];

int max(int a, int b){
    return a > b ? a : b;
}

int cal(char a, char b){
    if(a > b){
        char temp = a;
        a = b;
        b = temp;
    }
    if(a == 'A'){
        if(b == 'A')
            return 10;
        if(b == 'B')
            return 8;
        if(b == 'C')
            return 7;
        if(b == 'D')
            return 5;
        if(b == 'F')
            return 1;
    }
    else if(a == 'B'){
        if(b == 'B')
            return 6;
        if(b == 'C')
            return 4;
        if(b == 'D')
            return 3;
        if(b == 'F')
            return 1;
    }
    else if(a == 'C'){
        if(b == 'C')
            return 3;
        if(b == 'D')
            return 2;
        if(b == 'F')
            return 1;
    }
    else if(a == 'D'){
        if(b == 'D')
            return 2;
        if(b == 'F')
            return 1;
    }
    else
        return 0;
}

int DP(int x, int bit){
    if(~dp[x][bit])
        return dp[x][bit];
    if(x == N * M && !bit)
        return 0;
    if(x >= N * M && bit)
        return -1e9;
    dp[x][bit] = max(0, DP(x + 1, bit >> 1));
    if(!(bit & 1)){
        if(x % M != M - 1 && !(bit & 2))
            dp[x][bit] = max(dp[x][bit], DP(x + 2, bit >> 2) + cal(str[x], str[x + 1]));
        dp[x][bit] = max(dp[x][bit], DP(x + 1, (bit >> 1) | 1 << (M - 1)) + cal(str[x], str[x + M]));
    }
    return dp[x][bit];
}

int main(){
    scanf("%d %d", &N, &M);
    for(int i = 0; i < N; i++){
        scanf("%s", s);
        strcat(str, s);
    }
    memset(dp, -1, sizeof(dp));
    printf("%d\n", DP(0, 0));

    return 0;
}