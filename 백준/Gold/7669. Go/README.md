# [Gold V] Go - 7669 

[문제 링크](https://www.acmicpc.net/problem/7669) 

### 성능 요약

메모리: 17260 KB, 시간: 156 ms

### 분류

너비 우선 탐색, 깊이 우선 탐색, 그래프 이론, 그래프 탐색, 구현

### 제출 일자

2024년 9월 21일 13:49:29

### 문제 설명

<p>In the game of Go, two players alternate placing black and white stones on lattice points of an n × n grid, each attempting to surround as much territory (i.e., regions of unfilled lattice points) as possible. At the end of the game, the score for each player is the total area of the territory surrounded by his or her stones. Given the locations of black and white stones on a Go board at the end of a match, your task is to compute the score of each player in order to determine the winner.<sup>1</sup></p>

<p>Formally, two grid lattice points with coordinates (r, c) and (r′, c′) are adjacent if |r − r′| + |c − c′| = 1. A connected region of unfilled lattice points belongs to one player’s territory if all adjacent filled lattice points contain stones belonging to that player (see Figure 1). Finally, a player’s score consists of the number of unfilled lattice points in his or her territory.</p>

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/7669/1.png" style="height:384px; width:374px"></p>

<p style="text-align: center;">Figure 1: Diagram of a 9 × 9 Go board. Unfilled lattice points belonging to black’s territory are marked with B, and unfilled lattice points belonging to white’s territory are marked with W. Neutral unfilled lattice points are unmarked. In the game above, white wins by 21 − 3 = 18.</p>

<p><sup>1</sup>Note that the scoring of Go boards described here does not correspond exactly to the real game of Go: we make the simplifying assumptions that all “disputes” have been settled so that any terri</p>

### 입력 

 <p>The input test file will contain multiple cases, each consisting of three lines. Each test case begins with a line containing three integers, n (where 1 ≤ n ≤ 19), b, and w (where b ≥ 0, w ≥ 0 and 1 ≤ b + w ≤ n 2 ). Here, n denotes the size of the board, b is the number of black pieces placed, and w is the number of white pieces placed. The second line of each test case contains b pairs of integers r<sub>1</sub> c<sub>1</sub> . . . r<sub>b</sub> c<sub>b</sub> (where 1 ≤ r<sub>i</sub> , c<sub>i</sub> ≤ n) indicating the positions of the b black stones. The third line of each test case contains w pairs of integers r′<sub>1</sub> c′<sub>1</sub> . . . r′<sub>w</sub> c′<sub>w</sub> (where 1 ≤ r′<sub>i</sub> , c′<sub>i</sub> ≤ n) indicating the positions of the w white stones. No two stones will be located at the same lattice point. Input is terminated by a single line containing only the number 0; do not process this line.</p>

### 출력 

 <p>For each test case, print either “White wins by ______”, “Black wins by ______”, or “Draw”.</p>

