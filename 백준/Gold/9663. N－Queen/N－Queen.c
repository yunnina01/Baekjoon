#include <stdio.h>
#include <stdlib.h>

int arr[14], N;

int back(int num){
    if(num == N)
        return 1;
    else{
        int i, j, cnt = 0, check;
        for(i = 0; i < N; i++){
            check = 0;
            for(j = 0; j < num; j++){
                if(arr[j] == i || abs(num - j) == abs(i - arr[j])){
                    check = 1;
                    break;
                }
            }
            if(!check){
                arr[num] = i;
                cnt += back(num + 1);
            }
        }
        return cnt;
    }
}

int main(){
    scanf("%d", &N);
    printf("%d\n", back(0));

    return 0;
}