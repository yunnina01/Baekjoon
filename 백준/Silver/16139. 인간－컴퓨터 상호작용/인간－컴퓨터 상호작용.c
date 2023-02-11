#include <stdio.h>
#include <string.h>

int ps[200001][26];

int main(){
    char S[200001], ch;
    int q, l, r, i, j;
    scanf("%s %d", S, &q);
    for(i = 1; i <= strlen(S); i++){
        for(j = 0; j < 26; j++)
            ps[i][j] = ps[i - 1][j];
        ps[i][S[i - 1] - 'a']++;
    }
    while(q--){
        scanf(" %c %d %d", &ch, &l, &r);        
        printf("%d\n", ps[r + 1][ch - 'a'] - ps[l][ch - 'a']);
    }

    return 0;
}