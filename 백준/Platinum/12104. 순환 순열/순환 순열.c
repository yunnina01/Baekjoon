#include <stdio.h>
#include <string.h>

char A[200001], B[100001];
int SP[100000], cnt;

void preset(){
    for(int i = 1, j = 0; B[i]; i++){
        while(j && B[i] != B[j])
            j = SP[j - 1];
        if(B[i] == B[j])
            SP[i] = ++j;
    }
}

void KMP(){
    for(int i = 0, j = 0; A[i]; i++){
        while(j && A[i] != B[j])
            j = SP[j - 1];
        if(A[i] == B[j]){
            if(!B[j + 1]){
                cnt++;
                j = SP[j];
            }
            else
                j++;
        }
    }
}

int main(){
    int i, len;
    scanf("%s %s", A, B);
    len = strlen(A);
    for(i = 0; i < len - 1; i++)
        A[i + len] = A[i];
    preset();
    KMP();
    printf("%d\n", cnt);

    return 0;
}