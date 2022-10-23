#include <stdio.h>

int d(int n){
    return n + n / 10000 + n % 10000 / 1000 + n % 1000 / 100 + n % 100 / 10 + n % 10; 
}

int main(){
    int i, r;
    char a[10001] = {};
    for(i = 1; i <= 10000; i++){
        r = d(i);
        if(r > 10000)
            continue;
        a[r] = 1;
    }
    for(i = 1; i <= 10000; i++){
        if(a[i] != 1)
            printf("%d\n", i);
    }
    
    return 0;
}