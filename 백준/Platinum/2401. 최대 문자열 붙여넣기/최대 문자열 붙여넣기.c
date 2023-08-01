#include <stdio.h>
#include <string.h>

char l[100001], s[500][10001];
int SP[500][10000], dp[100001], visit[500];
int len[500], N, L;

void preset(int idx){
    for(int i = 1, j = 0; i < len[idx]; i++){
        while(j && s[idx][i] != s[idx][j])
            j = SP[idx][j - 1];
        if(s[idx][i] == s[idx][j])
            SP[idx][i] = ++j;
    }
}

void KMP(){
    int i, j, temp;
    for(i = 0; i < L; i++){
        if(i)
            dp[i] = dp[i - 1];
        for(j = 0; j < N; j++){
            while(visit[j] && l[i] != s[j][visit[j]])
                visit[j] = SP[j][visit[j] - 1];
            if(l[i] == s[j][visit[j]]){
                if(visit[j] == len[j] - 1){
                    temp = i - len[j] >= 0 ? dp[i - len[j]] : 0;
                    temp += len[j];
                    if(temp > dp[i])
                        dp[i] = temp;
                    visit[j] = SP[j][visit[j]];
                }
                else
                    visit[j]++;
            }
        }
    }
}

int main(){
    scanf("%s %d", l, &N);
    L = strlen(l);
    for(int i = 0; i < N; i++){
        scanf("%s", s[i]);
        len[i] = strlen(s[i]);
        preset(i);
    }
    KMP();
    printf("%d\n", dp[L - 1]);

    return 0;
}