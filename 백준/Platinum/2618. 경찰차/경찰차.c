#include <stdio.h>
#include <stdlib.h>

typedef struct{
    int x, y;
}Pair;

Pair event[1001];
int dp[1001][1001], N, W;

int get_dis(Pair p1, Pair p2){
    return abs(p2.x - p1.x) + abs(p2.y - p1.y);
}

int get_total_dis(int p1, int p2){
    if(p1 == W || p2 == W)
        return 0;
    if(dp[p1][p2] != -1)
        return dp[p1][p2];
    int next = (p1 > p2 ? p1 : p2) + 1, dis1, dis2;
    if(!p1)
        dis1 = get_dis((Pair){1, 1}, event[next]);
    else
        dis1 = get_dis(event[p1], event[next]);
    if(!p2)
        dis2 = get_dis((Pair){N, N}, event[next]);
    else
        dis2 = get_dis(event[p2], event[next]);
    dis1 += get_total_dis(next, p2);
    dis2 += get_total_dis(p1, next);
    return dp[p1][p2] = dis1 < dis2 ? dis1 : dis2;
}

void route(int p1, int p2){
    if(p1 == W || p2 == W)
        return;
    int next = (p1 > p2 ? p1 : p2) + 1, dis1, dis2;
    if(!p1)
        dis1 = get_dis((Pair){1, 1}, event[next]);
    else
        dis1 = get_dis(event[p1], event[next]);
    if(!p2)
        dis2 = get_dis((Pair){N, N}, event[next]);
    else
        dis2 = get_dis(event[p2], event[next]);
    if(dp[next][p2] + dis1 < dp[p1][next] + dis2){
        puts("1");
        route(next, p2);
    }
    else{
        puts("2");
        route(p1, next);
    }
}

int main(){
    int i, j;
    scanf("%d %d", &N, &W);
    for(i = 1; i <= W; i++)
        scanf("%d %d", &event[i].x, &event[i].y);
    for(i = 0; i <= W; i++){
        for(j = 0; j <= W; j++)
            dp[i][j] = -1;
    }
    printf("%d\n", get_total_dis(0, 0));
    route(0, 0);

    return 0;
}