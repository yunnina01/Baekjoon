#include <stdio.h>

typedef struct{
    int d, p;
}Pair;

Pair heap1[500001];
int heap2[500001], size1, size2;
long long res;

void push1(Pair item){
    int i = ++size1;
    while(i != 1 && item.d < heap1[i / 2].d){
        heap1[i] = heap1[i / 2];
        i /= 2;
    }
    heap1[i] = item;
}

int pop1(){
    Pair temp = heap1[size1--];
    int parent = 1, child = 2, min = heap1[1].p;
    while(child <= size1){
        if(child < size1 && heap1[child].d > heap1[child + 1].d)
            child++;
        if(temp.d <= heap1[child].d)
            break;
        heap1[parent] = heap1[child];
        parent = child;
        child *= 2;
    }
    heap1[parent] = temp;
    return min;
}

void push2(int item){
    int i = ++size2;
    while(i != 1 && item < heap2[i / 2]){
        heap2[i] = heap2[i / 2];
        i /= 2;
    }
    heap2[i] = item;
}

int pop2(){
    int parent = 1, child = 2, min = heap2[1], temp = heap2[size2--];
    while(child <= size2){
        if(child < size2 && heap2[child] > heap2[child + 1])
            child++;
        if(temp <= heap2[child])
            break;
        heap2[parent] = heap2[child];
        parent = child;
        child *= 2;
    }
    heap2[parent] = temp;
    return min;
}

int main(){
    int N, M, D, P, T, E, HD, HP;
    scanf("%d %d", &N, &M);
    while(N--){
        scanf("%d %d %d %d", &D, &P, &T, &E);
        if(T)
            P = 0;
        if(E){
            D = (D + 1) / 2;
            P /= 2;
        }
        push1((Pair){D, P});
    }
    scanf("%d %d", &HD, &HP);
    while(M--){
        while(size1 && heap1[1].d <= HD)
            push2(pop1());
        if(!size2){
            res = -1;
            break;
        }
        P = pop2();
        if(P > HP)
            res += P - HP;
        HD++, HP++;
    }
    printf("%lld\n", res);

    return 0;
}