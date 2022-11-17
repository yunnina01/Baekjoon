#include <stdio.h>

void bubble(int *arr, int n){
    int i, j;
    for(i = 1; i < n; i++){
        for(j = 0; j < n - i; j++){
            if(arr[j] > arr[j + 1]){
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

int main(){
    int N, i;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    bubble(arr, N);
    for(i = 0; i < N; i++)
        printf("%d\n", arr[i]);
    
    return 0;
}