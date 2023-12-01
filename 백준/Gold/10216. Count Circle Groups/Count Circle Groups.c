# include <stdio.h>
# include <math.h>

typedef struct {
	int x, y, r;
}Circle;

int parent[3001];

int find(int x) {
	if(x == parent[x])
        return x;
	return parent[x] = find(parent[x]);
}

void union_set(int a, int b) {
	a = find(a);
	b = find(b);
	if(a < b)
        parent[b] = parent[a];
	else
    	parent[a]=parent[b];
}

double dist(Circle a, Circle b) {
	return sqrt((double)(a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
}

int main(void) {
	int T, N, i, j, res;
	Circle arr[3001];
	scanf("%d", &T);
	while(T--) {
		scanf("%d", &N);
		for(i = 1; i <= N; i++) {
			parent[i] = i;
			scanf("%d %d %d", &arr[i].x, &arr[i].y, &arr[i].r);
		}
		for(i = 1; i <= N - 1; i++)
			for(j = i + 1; j <= N; j++)
				if(dist(arr[i], arr[j]) <= arr[i].r + arr[j].r)
					union_set(i, j);
		res = 0;
		for(i = 1; i <= N; i++)
			if(find(i) == i)
				res++;
		printf("%d\n", res);
	}
    
	return 0;
}