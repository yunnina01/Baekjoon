#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int compare(const void *a, const void *b){
    return strcmp((char*)a, (char*)b);
}

int main(){
    char S[1001];
    scanf("%s", S);
    int i, j, idx = 0, cnt = 0, len = strlen(S), total = len * (len + 1) / 2;
    char sub[total][1001];
    for(i = 0; i < len; i++){
        for(j = 1; j <= len - i; j++){
            strncpy(sub[idx], S + i, j);
            sub[idx++][j] = '\0';
        }
    }
    qsort(sub, total, sizeof(sub[0]), compare);
    for(i = 0; i < total; i++){
        if(strcmp(sub[i], sub[i + 1]))
            cnt++;
    }
    printf("%d\n", cnt);

    return 0;
}