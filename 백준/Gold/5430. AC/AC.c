#include <stdio.h>

int deque[100000];
char p[100001], arr[400002];

int main(){
    int T, n;
    scanf("%d", &T);
    while(T--){
        scanf("%s %d %s", p, &n, arr);
        int i = 1, front = 0, rear = 0, num = 0, flags = 0;
        while(rear < n){
            if(arr[i] == ',' || arr[i] == ']'){
                deque[rear++] = num;
                num = 0;
            }
            else{
                num *= 10;
                num += arr[i] - '0';
            }
            i++;
        }
        for(i = 0; p[i]; i++){
            if(p[i] == 'R')
                flags++;
            else{
                if(!(flags % 2))
                    front++;
                else
                    rear--;
            }
        }
        if(front > rear)
            puts("error");
        else if(front == rear)
            puts("[]");
        else{
            printf("[");
            if(!(flags % 2)){
                for(i = front; i < rear - 1; i++)
                    printf("%d,", deque[i]);
            }
            else{
                for(i = rear - 1; i > front; i--)
                    printf("%d,", deque[i]);
            }
            printf("%d]\n", deque[i]);
        }
    }

    return 0;
}