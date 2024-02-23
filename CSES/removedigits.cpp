#include <algorithm>
#include <iostream>
#include <vector>

std::vector<int> dp (1000001, -1);
int dfs(int x) {
    if (dp[x] > -1) return dp[x];
    int ans=  1000000;
    int temp = x;
    while (temp > 0) {
        if (x - temp % 10 < 0 || temp % 10 == 0) {
            temp /= 10;
            continue;
        }
        ans = std::min(ans, dfs(x - temp % 10));
        temp /= 10;
    }
    return dp[x] = ans + 1;
}

int main() {
    int n; std::cin >> n;
    dp[0] = 0;
    std::cout << dfs(n) << std::endl;
    return 0;
}
