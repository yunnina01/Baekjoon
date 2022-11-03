#include <stdio.h>

int G(char *a){
    char g[26] = {}, i, t = 0;
    for(i = 0; a[i] != '\0'; i++){
        if(t != a[i]){
            t = a[i];
            if(g[t - 'a'] == 0)
                g[t - 'a'] = 1;
            else
                return 0;
        }
    }
    return 1;
}

int main(){
    char a[100], N, i, cnt = 0;
    scanf("%d", &N);
    for(i = 0; i < N; i++){
        scanf("%s", a);
        cnt += G(a);
    }
    printf("%d\n", cnt);

    return 0;
}