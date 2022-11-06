#include <stdio.h>

void S(int X){
    int i, j;
    for(i = j = 1; i < X; i += j)
        j++;
    int k = X - (i - j);
    if(++j % 2 == 1)
        printf("%d/%d\n", k, j - k);
    else
        printf("%d/%d\n", j - k, k);
}

int main(){
    int X;
    scanf("%d", &X);
    S(X);

    return 0;
}