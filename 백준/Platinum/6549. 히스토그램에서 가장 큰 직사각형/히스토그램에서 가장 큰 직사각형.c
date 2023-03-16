#include <stdio.h>
#define ll long long

int arr[100000];

ll max(ll a, ll b){
    return a > b ? a : b;
}

ll min(ll a, ll b){
    return a < b ? a : b;
}

ll histo(int left, int right){
    if(left == right)
        return arr[left];
    int mid = (left + right) / 2;
    int low = mid, high = mid; 
    ll height = arr[mid], res = max(histo(left, mid), histo(mid + 1, right));
    while(left < low || high < right){
        if(left < low && (high == right || arr[low - 1] > arr[high + 1]))
            height = min(height, arr[--low]);
        else
            height = min(height, arr[++high]);
        res = max(res, height * (high - low + 1));
    }
    return res;
}

int main(){
    int n, i;
    while(1){
        scanf("%d", &n);
        if(!n)
            break;
        for(i = 0; i < n; i++)
            scanf("%d", &arr[i]);
        printf("%lld\n", histo(0, n - 1));
    }

    return 0;
}