#include <stdio.h>

typedef struct{
    int x, y;
}Pair;

Pair l[3001][2];
int parent[3001], cnt[3001], gcnt, max;

int ccw(Pair p1, Pair p2, Pair p3){
    int cp = p1.x * p2.y + p2.x * p3.y + p3.x * p1.y;
    cp -= p1.y * p2.x + p2.y * p3.x + p3.y * p1.x;
    if(cp < 0)
        return 1;
    if(cp > 0)
        return -1;
    return 0;
}

int iscross(Pair p1, Pair p2, Pair p3, Pair p4){
    if(ccw(p1, p2, p3) * ccw(p1, p2, p4) <= 0 && ccw(p3, p4, p1) * ccw(p3, p4, p2) <= 0){
        if(p1.x > p3.x && p1.x > p4.x && p2.x > p3.x && p2.x > p4.x)
            return 0;
        if(p3.x > p1.x && p3.x > p2.x && p4.x > p1.x && p4.x > p2.x)
            return 0;
        if(p1.y > p3.y && p1.y > p4.y && p2.y > p3.y && p2.y > p4.y)
            return 0;
        if(p3.y > p1.y && p3.y > p2.y && p4.y > p1.y && p4.y > p2.y)
            return 0;
        return 1;
    }
    return 0;
}

int find(int x){
    if(x == parent[x])
        return x;
    return parent[x] = find(parent[x]);
}

int unions(int x, int y){
    parent[find(x)] = find(y);
}

int main(){
    int N, i, j;
    scanf("%d", &N);
    for(i = 1; i <= N; i++){
        scanf("%d %d %d %d", &l[i][0].x, &l[i][0].y, &l[i][1].x, &l[i][1].y);
        parent[i] = i;
    }
    for(i = 1; i <= N; i++){
        for(j = 1; j < i; j++){
            if(iscross(l[i][0], l[i][1], l[j][0], l[j][1]))
                unions(i, j);
        }
    }
    for(i = 1; i <= N; i++)
        cnt[find(i)]++;
    for(i = 1; i <= N; i++){
        if(cnt[i])
            gcnt++;
        if(cnt[i] > max)
            max = cnt[i];
    }
    printf("%d\n%d\n", gcnt, max);

    return 0;
}