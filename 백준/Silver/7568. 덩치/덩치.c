#include <stdio.h>

typedef struct{
    int x, y;
}Person;

int main(){
    int N, i, j;
    scanf("%d", &N);
    Person arr[N];
    char res[N];
    for(i = 0; i < N; i++)
        scanf("%d %d", &arr[i].x, &arr[i].y);
    for(i = 0; i < N; i++){
        res[i] = 1;
        for(j = 0; j < N; j++){
            if(arr[i].x < arr[j].x && arr[i].y < arr[j].y)
                res[i]++;
        }
    }
    for(i = 0; i < N; i++)
        printf("%d ", res[i]);

    return 0;
}