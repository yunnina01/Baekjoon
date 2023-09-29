#include <stdio.h>
#include <stdlib.h>
#define ll long long

typedef struct{
    ll x, y;
}Pair;

Pair arr[200000], stack[200000];
int top;

ll ccw(Pair p1, Pair p2, Pair p3){
    return p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);
}

int asc(const void *a, const void *b){
    Pair *A = (Pair*)a, *B = (Pair*)b;
    if(A->y == B->y)
        return A->x - B->x;
    return A->y - B->y;
}

ll orientation(Pair p1, Pair p2, Pair p3){
    return (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
}

ll get_dist(Pair p1, Pair p2){
    return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
}

int ccw_asc(const void *a, const void *b){
    Pair *A = (Pair*)a, *B = (Pair*)b;
    ll o = orientation(arr[0], *A, *B);
    if(!o)
        return get_dist(arr[0], *A) <= get_dist(arr[0], *B) ? -1 : 1;
    if(o > 0)
        return 1;
    return -1;
}

int main(){
    Pair p1, p2, np;
    ll max, dis;
    int T, N, i, j, ni, nj;
    scanf("%d", &T);
    while(T--){
        scanf("%d", &N);
        for(i = 0; i < N; i++)
            scanf("%lld %lld", &arr[i].x, &arr[i].y);
        qsort(arr, N, sizeof(Pair), asc);
        qsort(arr + 1, N - 1, sizeof(Pair), ccw_asc);
        top = 0;
        stack[top++] = arr[0];
        stack[top++] = arr[1];
        for(i = 2; i < N; i++){
            while(top >= 2){
                p2 = stack[--top];
                p1 = stack[top - 1];
                if(ccw(p1, p2, arr[i]) > 0){
                    stack[top++] = p2;
                    break;
                }
            }
            stack[top++] = arr[i];
        }
        max = 0;
        j = 1;
        for(i = 0; i < top; i++){
            ni = (i + 1) % top;
            while(1){
                nj = (j + 1) % top;
                np.x = stack[ni].x + stack[nj].x - stack[j].x;
                np.y = stack[ni].y + stack[nj].y - stack[j].y;
                if(ccw(stack[i], stack[ni], np) <= 0)
                    break;
                j = nj;
            }
            dis = get_dist(stack[i], stack[j]);
            if(dis > max){
                max = dis;
                p1 = stack[i];
                p2 = stack[j];
            }
        }
        printf("%lld %lld %lld %lld\n", p1.x, p1.y, p2.x, p2.y);
    }

    return 0;
}