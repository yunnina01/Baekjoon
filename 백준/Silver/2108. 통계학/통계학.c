#include <stdio.h>
#include <math.h>

int counting(int *arr, int n){
    int cnt[8001] = {}, i, j, idx = 0, max = 0;
    for(i = 0; i < n; i++)
        cnt[arr[i] + 4000]++;
    for(i = 0; i < 8001; i++){
        if(cnt[i] > cnt[max])
            max = i;
        for(j = 0; j < cnt[i]; j++)
            arr[idx++] = i - 4000;
    }
    for(i = 0; i < 8001; i++){
        if(cnt[i] == cnt[max] && i != max){
            max = i;
            break;
        }
    }
    return max - 4000;
}

int main(){
    int N, i, fre;
    double sum = 0;
    scanf("%d", &N);
    int arr[N];
    for(i = 0; i < N; i++){
        scanf("%d", &arr[i]);
        sum += arr[i];
    }
    fre = counting(arr, N);
    printf("%d\n%d\n%d\n%d\n", (int)(round(sum / N)), arr[N / 2], fre, arr[N - 1] - arr[0]);    

    return 0;
}