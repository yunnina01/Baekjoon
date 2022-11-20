#include <stdio.h>

int main(){
    int N, i, j, k;
    scanf("%d %d", &N, &k);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    for(i = 1; i < N; i++){
        for(j = 0; j < N - i; j++){
            if(arr[j] < arr[j + 1]){
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    printf("%d\n", arr[k - 1]);

    return 0;
}