#include <stdio.h>

int arr[11], op[4], N;
int max = -1000000000, min = 1000000000;

void back(int val, int cnt){
    if(cnt == N - 1){
        if(val > max)
            max = val;
        if(val < min)
            min = val;
    }
    else{
        for(int i = 0; i < 4; i++){
            if(op[i]){
                op[i]--;
                if(!i)
                    back(val + arr[cnt + 1], cnt + 1);
                if(i == 1)
                    back(val - arr[cnt + 1], cnt + 1);
                if(i == 2)
                    back(val * arr[cnt + 1], cnt + 1);
                if(i == 3 && arr[cnt + 1])
                    back(val / arr[cnt + 1], cnt + 1);
                op[i]++;
            }
        }
    }
}

int main(){
    scanf("%d", &N);
    for(int i = 0; i < N; i++)
        scanf("%d", &arr[i]);
    scanf("%d %d %d %d", &op[0], &op[1], &op[2], &op[3]);
    back(arr[0], 0);
    printf("%d\n%d\n", max, min);

    return 0;
}