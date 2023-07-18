#include <stdio.h>

char word[50][16];
int alpha[26], N, K, cnt, res;

void DFS(int idx, int num){
    int i, j;
    if(num == K){
        cnt = 0;
        for(i = 0; i < N; i++){
            for(j = 0; word[i][j]; j++){
                if(!alpha[word[i][j] - 'a'])
                    break;
            }
            if(!word[i][j])
                cnt++;
        }
        if(cnt > res)
            res = cnt;
        return;
    }
    for(i = idx; i < 26; i++){
        if(!alpha[i]){
            alpha[i] = 1;
            DFS(i, num + 1);
            alpha[i] = 0;
        }
    }
}

int main(){
    alpha[0] = alpha[2] = alpha[8] = alpha[13] = alpha[19] = 1;
    scanf("%d %d", &N, &K);
    for(int i = 0; i < N; i++)
        scanf("%s", word[i]);
    K -= 5;
    if(K >= 0)
        DFS(0, 0);
    printf("%d\n", res);

    return 0;
}