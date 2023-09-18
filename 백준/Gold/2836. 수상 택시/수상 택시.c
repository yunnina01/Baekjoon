#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int s, e;
}Pair;

Pair arr[300000];
long long res;
int cnt;

int desc(const void *a, const void *b){
    return ((Pair*)b)->s - ((Pair*)a)->s;
}

int main(){
    int N, M, i, left, right;
    scanf("%d %d", &N, &M);
    while(N--){
        scanf("%d %d", &left, &right);
        if(left > right)
            arr[cnt++] = (Pair){left, right};
    }
    qsort(arr, cnt, sizeof(Pair), desc);
    left = arr[0].e;
    right = arr[0].s;
    res = M;
    for(i = 1; i < cnt; i++){
        if(arr[i].s >= left){
            if(arr[i].e < left)
                left = arr[i].e;
        }
        else{
            res += (right - left) * 2;
            left = arr[i].e;
            right = arr[i].s;
        }
    }
    printf("%lld\n", res + (right - left) * 2);

    return 0;
}