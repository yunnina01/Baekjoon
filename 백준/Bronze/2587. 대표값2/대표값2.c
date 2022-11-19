#include <stdio.h>

int main(){
    int arr[5], i, j, sum = 0;
    for(i = 0; i < 5; i++){
        scanf("%d", &arr[i]);
        sum += arr[i];
    }
    for(i = 1; i < 5; i++){
        for(j = 0; j < 5 - i; j++){
            if(arr[j] > arr[j + 1]){
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    printf("%d\n%d\n", sum / 5, arr[2]);

    return 0;
}