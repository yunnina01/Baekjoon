#include <stdio.h>

int ps[100001];

int main(){
    int N, M, i, j;
    scanf("%d %d", &N, &M);
    for(i = 1; i <= N; i++){
        scanf("%d", &ps[i]);
        ps[i] += ps[i - 1];
    }
    while(M--){
        scanf("%d %d", &i, &j);
        printf("%d\n", ps[j] - ps[i - 1]);
    }
    
    return 0;
}