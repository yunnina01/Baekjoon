#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char str[10000][11];

int asc(const void *a, const void *b){
    return strcmp((char*)a, (char*)b);
}

int main(){
    int t, n, i, j, len1, len2, chk;
    scanf("%d", &t);
    while(t--){
        scanf("%d", &n);
        for(i = 0; i < n; i++)
            scanf("%s", str[i]);
        qsort(str, n, sizeof(char) * 11, asc);
        chk = 1;
        for(i = 1; i < n; i++){
            len1 = strlen(str[i - 1]);
            len2 = strlen(str[i]);
            if(len2 >= len1){
                for(j = 0; j < len1; j++){
                    if(str[i - 1][j] != str[i][j])
                        break;
                }
                if(j == len1){
                    chk = 0;
                    break;
                }
            }
        }
        printf("%s\n", chk ? "YES" : "NO");
    }

    return 0;
}