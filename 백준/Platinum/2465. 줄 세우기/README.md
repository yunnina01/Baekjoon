# [Platinum III] 줄 세우기 - 2465 

[문제 링크](https://www.acmicpc.net/problem/2465) 

### 성능 요약

메모리: 68660 KB, 시간: 872 ms

### 분류

자료 구조, 세그먼트 트리, 이분 탐색

### 제출 일자

2025년 7월 10일 06:04:00

### 문제 설명

<p>N명의 사람들이 어떤 공연장에 입장하기 위해서 한 줄로 서 있다. 줄 서 있는 각 사람은 자기 앞에 서 있는 사람들 중에서 자기보다 키가 작거나 같은 사람들의 수를 알고 있다. 그러면, 이 수들을 표시하는 수열을 S라고 한다.</p>

<p>N명의 키 집합과 수열 S가 주어질 때, 원래 줄 서 있는 키 순서를 정확히 찾아내는 프로그램을 작성하시오. </p>

<p>예를 들어서, 사람들의 키 집합이 다음과 같이 주어진다 (여기서, 같은 키의 사람들이 여러 명 존재할 수 있어서 중복이 포함된다). </p>

<p style="text-align: center;">{120, 167, 163, 172, 145, 134, 182, 155, 167, 120, 119, 156}</p>

<p>또한 각 사람이 자기 앞에 있는 사람들 중에서 자기보다 키가 작거나 같은 사람들의 수를 표시하는 수열 S는 다음과 같이 주어진다. </p>

<p style="text-align: center;">S : 0 1 0 0 3 2 6 7 4 6 9 4</p>

<p>그러면, 실제 줄 서 있는 사람들의 키 순서는 다음과 같다. </p>

<p style="text-align: center;">134 167 120 119 156 120 167 182 155 163  172 145</p>

### 입력 

 <p>첫째 줄에는 전체 사람의 수 N (1 ≤ N ≤ 100,000)이 주어진다. 다음 N개의 줄에 사람들의 키를 나타내는 양의 정수가 하나씩 주어진다. 여기서 모든 키들은 2×10<sup>9</sup>이하이다. 그리고 마지막 줄에 수열 S가 한 줄로 주어진다. 단 그 수열의 수는 하나의 공백을 두고 나타난다. </p>

### 출력 

 <p>출력은 N개의 줄로 구성된다. N개의 줄 각각에 원래 줄 서 있는 사람들의 키를 순서대로 하나씩 출력한다. </p>

