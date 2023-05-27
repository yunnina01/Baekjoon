#include <stdio.h>

int arr[100000];

int main(){
    int N, S, i;
    scanf("%d %d", &N, &S);
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    int l = 0, r = 0, val = 0, res = -1;
    while(r <= N && l <= N){
        if(val < S)
            val += arr[r++];
        else{
            if(res == -1 || r - l < res)
                res = r - l;
            val -= arr[l++];
        }
    }
    printf("%d\n", res + (res == -1));

    return 0;
}