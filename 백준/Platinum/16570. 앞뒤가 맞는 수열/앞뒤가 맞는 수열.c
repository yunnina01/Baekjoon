#include <stdio.h>

int A[1000000], SP[1000000], N, max, cnt;

void preset(){
    for(int i = 1, j = 0; i < N; i++){
        while(j && A[i] != A[j])
            j = SP[j - 1];
        if(A[i] == A[j])
            SP[i] = ++j;
    }
}

void KMP(){
    for(int i = 1; i < N; i++){
        if(max < SP[i]){
            max = SP[i];
            cnt = 1;
        }
        else if(max == SP[i])
            cnt++;
    }
    if(!max)
        puts("-1");
    else
        printf("%d %d\n", max, cnt);
}

int main(){
    scanf("%d", &N);
    for(int i = N - 1; i >= 0; i--)
        scanf("%d", &A[i]);
    preset();
    KMP();

    return 0;
}