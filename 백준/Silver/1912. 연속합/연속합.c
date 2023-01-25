#include <stdio.h>

int main(){
    int N, n, max = -1000, sum = 0;
    scanf("%d", &N);
    while(N--){
        scanf("%d", &n);
        if(n > 0){
            if(sum < 0)
                sum = 0;
            sum += n;
            max = max > sum ? max : sum;
        }
        else{
            if(sum <= 0)
                max = max > n ? max : n;
            else
                sum += n;
        }
    }
    printf("%d\n", max);

    return 0;
}