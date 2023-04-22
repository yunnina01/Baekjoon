#include <stdio.h>
#define ll long long

int stack[100001], his[100002], top;
ll res;

ll max(ll a, ll b){
    return a > b ? a : b;
}

int main(){
    int N, i, temp;
    scanf("%d", &N);
    for(i = 1; i <= N; i++)
        scanf("%d", &his[i]);
    for(i = 1; i <= N + 1; i++){
        if(his[i] < his[i - 1]){
            while(top && his[stack[top]] > his[i]){
                temp = stack[top--];
                res = max(res, (ll)his[temp] * (i - stack[top] - 1));
            }
        }
        stack[++top] = i;
    }
    printf("%lld\n", res);

    return 0;
}