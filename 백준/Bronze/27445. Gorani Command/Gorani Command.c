int main(){
    int N, M, i, x = 0, y = 0;
    scanf("%d %d", &N, &M);
    int row[N], col[M];
    for(i = 0; i < N; i++)
        scanf("%d", &row[i]);
    col[0] = row[N - 1];
    for(i = 1; i < M; i++)
        scanf("%d", &col[i]);
    for(i = 1; i < N; i++){
        if(row[i] < row[x])
            x = i;
    }
    for(i = 1; i < M; i++){
        if(col[i] < col[y])
            y = i;
    }
    printf("%d %d\n", x + 1, y + 1);

    return 0;
}