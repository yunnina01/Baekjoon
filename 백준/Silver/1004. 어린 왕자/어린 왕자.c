#include <stdio.h>
#include <math.h>

int main(){
    int T, x1, y1, x2, y2, n, cx, cy, r, cnt, i, j;
    double Dsc, Dec;
    scanf("%d", &T);
    for(i = 0; i < T; i++){
        cnt = 0;
        scanf("%d %d %d %d %d", &x1, &y1, &x2, &y2, &n);
        for(j = 0; j < n; j++){
            scanf("%d %d %d", &cx, &cy, &r);
            Dsc = sqrt((double)(x1 - cx) * (x1 - cx) + (y1 - cy) * (y1 - cy));
            Dec = sqrt((double)(x2 - cx) * (x2 - cx) + (y2 - cy) * (y2 - cy));
            if(Dsc < r && Dec < r)
                continue;
            if(Dsc < r || Dec < r)
                cnt++;
        }
        printf("%d\n", cnt);
    }

    return 0;
}