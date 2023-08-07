#include <stdio.h>

int arr[5];

void solve(){
    int i, j;
    for(i = 0; i < 5; i++){
        if(arr[i] == -1)
            continue;
        for(j = i + 1; j < 5; j++){
            if(arr[i] == arr[j]){
                arr[i] = arr[j] = -1;
                break;
            }
        }
        if(j == 5){
            printf("%d\n", arr[i]);
            return;
        }
    }
}

int main(){
    for(int i = 0; i < 5; i++)
        scanf("%d", &arr[i]);
    solve();

    return 0;
}