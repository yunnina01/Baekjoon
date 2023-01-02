#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int compare(const void *a, const void *b){
    return strcmp((char*)a, (char*)b);
}

int main(){
    int T, n;
    char wear[21], arr[31][21];
    scanf("%d", &T);
    while(T--){
        int cnt[31] = {}, i, j = 0, res = 1;
        scanf("%d", &n);
        strcpy(arr[0], "");
        for(i = 1; i <= n; i++)
            scanf("%s %s", wear, arr[i]);
        qsort(arr, n + 1, sizeof(arr[0]), compare);
        for(i = 1; i <= n; i++){
            if(!strcmp(arr[i - 1], arr[i]))
                cnt[j]++;
            else
                cnt[++j]++;
        }
        for(i = 1; i <= j; i++)
            res *= cnt[i] + 1;
        printf("%d\n", res - 1);
    }

    return 0;
}