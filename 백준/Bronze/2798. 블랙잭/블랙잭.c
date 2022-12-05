#include <stdio.h>

int main(){
    int N, M, i, j, k, temp, max = 0;
    scanf("%d %d", &N, &M);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    for(i = 0; i < N - 2; i++){
        for(j = i + 1; j < N - 1; j++){
            for(k = j + 1; k < N; k++){
                temp = arr[i] + arr[j] + arr[k];
                if(temp <= M && temp > max)
                    max = temp;
            }
        }
    }
    printf("%d\n", max);

    return 0;
}