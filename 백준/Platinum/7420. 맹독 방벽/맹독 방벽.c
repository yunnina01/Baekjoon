#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define ll long long

typedef struct{
    int x, y;
}Pair;

Pair arr[1000], stack[1000];
const double PI = 3.1415926535;
double res;
int top;

int ccw(Pair p1, Pair p2, Pair p3){
    return p1.x * p2.y + p2.x * p3.y + p3.x * p1.y - (p2.x * p1.y + p3.x * p2.y + p1.x * p3.y);
}

int asc(const void *a, const void *b){
    Pair *A = (Pair*)a, *B = (Pair*)b;
    if(A->y == B->y)
        return A->x - B->x;
    return A->y - B->y;
}

int orientation(Pair p1, Pair p2, Pair p3){
    return (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
}

double get_dist(Pair p1, Pair p2){
    return sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
}

int ccw_asc(const void *a, const void *b){
    Pair *A = (Pair*)a, *B = (Pair*)b;
    int o = orientation(arr[0], *A, *B);
    if(!o)
        return get_dist(arr[0], *A) <= get_dist(arr[0], *B) ? -1 : 1;
    if(o > 0)
        return 1;
    return -1;
}

int main(){
    Pair p1, p2;
    int N, L, i;
    scanf("%d %d", &N, &L);
    for(i = 0; i < N; i++)
        scanf("%d %d", &arr[i].x, &arr[i].y);
    qsort(arr, N, sizeof(Pair), asc);
    qsort(arr + 1, N - 1, sizeof(Pair), ccw_asc);
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
    res = get_dist(stack[0], stack[top - 1]);
    for(i = 1; i < top; i++)
        res += get_dist(stack[i], stack[i - 1]);
    res += 2 * PI * L;
    printf("%d\n", (int)(res + 0.5));

    return 0;
}