#include <stdio.h>
#define MAX 360000

char a[720000], b[360000];
int SP[360000], asize, bsize;

void preset(){
    for(int i = 1, j = 0; i < MAX; i++){
        while(j && b[i] != b[j])
            j = SP[j - 1];
        if(b[i] == b[j])
            SP[i] = ++j;
    }
}

int KMP(){
    for(int i = 0, j = 0; i <= asize; i++){
        while(j && a[i] != b[j])
            j = SP[j - 1];
        if(a[i] == b[j]){
            if(j == bsize)
                return 1;
            else
                j++;
        }
    }
    return 0;
}

int main(){
    int i, j, n;
    scanf("%d", &n);
    for(i = 0; i < n; i++){
        scanf("%d", &j);
        if(j > asize)
            asize = j;
        a[j] = a[j + MAX] = '1';
    }
    asize += MAX;
    for(i = 0; i < n; i++){
        scanf("%d", &j);
        if(j > bsize)
            bsize = j;
        b[j] = '1';
    }
    preset();
    printf("%s\n", KMP() ? "possible" : "impossible");

    return 0;
}