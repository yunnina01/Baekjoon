#include <stdio.h>
#include <string.h>

int max(int a, int b){
    return a > b ? a : b;
}

int main(){
    char a[1001], b[1001];
    scanf("%s %s", a, b);
    int i, j, alen = strlen(a), blen = strlen(b);
    int LCS[alen + 1][blen + 1];
    for(i = 0; i <= alen; i++){
        for(j = 0; j <= blen; j++){
            if(!i || !j)
                LCS[i][j] = 0;
            else if(a[i - 1] == b[j - 1])
                LCS[i][j] = LCS[i - 1][j - 1] + 1;
            else
                LCS[i][j] = max(LCS[i - 1][j], LCS[i][j - 1]);
        }
    }
    printf("%d\n", LCS[alen][blen]);

    return 0;
}