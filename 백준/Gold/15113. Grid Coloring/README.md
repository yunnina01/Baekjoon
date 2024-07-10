# [Gold II] Grid Coloring - 15113 

[문제 링크](https://www.acmicpc.net/problem/15113) 

### 성능 요약

메모리: 14144 KB, 시간: 104 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2024년 7월 10일 20:22:58

### 문제 설명

<p>You have an m-by-n grid of squares that you wish to color. You may color each square either red or blue, subject to the following constraints:</p>

<ul>
	<li>Every square must be colored.</li>
	<li>Colors of some squares are already decided (red or blue), and cannot be changed.</li>
	<li>For each blue square, all squares in the rectangle from the top left of the grid to that square must also be blue.</li>
</ul>

<p>Given these constraints, how many distinct colorings of the grid are there? The grid cannot be rotated.</p>

### 입력 

 <p>The first line of input consists of two space-separated integers m and n (1 ≤ m, n ≤ 30).</p>

<p>Each of the next m lines contains n characters, representing the grid. Character ‘B’ indicates squares that are already colored blue. Similarly, ‘R’ indicates red squares. Character ‘.’ indicates squares that are not colored yet.</p>

### 출력 

 <p>Print, on a single line, the number of distinct colorings possible.</p>

