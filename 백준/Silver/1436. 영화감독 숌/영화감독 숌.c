#include <stdio.h>

int main(){
    int N, i = 666, cnt = 0, temp, cnt666;
    scanf("%d", &N);
    while(cnt < N){
        temp = i++;
        cnt666 = 0;
        while(temp > 0){
            if(temp % 10 == 6)
                cnt666++;
            else
                cnt666 = 0;
            if(cnt666 == 3){
                cnt++;
                break;
            }
            temp /= 10;
        }
    }
    printf("%d\n", --i);
    
    return 0;
}