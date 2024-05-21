# [Platinum II] Wall Making Game - 11717 

[문제 링크](https://www.acmicpc.net/problem/11717) 

### 성능 요약

메모리: 77972 KB, 시간: 1744 ms

### 분류

다이나믹 프로그래밍, 게임 이론, 스프라그–그런디 정리

### 제출 일자

2024년 5월 21일 09:59:20

### 문제 설명

<p>The game Wall Making Game, a two-player board game, is all the rage.</p>

<p>This game is played on an H × W board. Each cell of the board is one of empty, marked, or wall. At the beginning of the game, there is no wall on the board.</p>

<p>In this game, two players alternately move as follows:</p>

<ol>
	<li>A player chooses one of the empty cells (not marked and not wall). If the player can’t choose a cell, he loses.</li>
	<li>Towards each of the four directions (upper, lower, left, and right) from the chosen cell, the player changes cells (including the chosen cell) to walls until the player first reaches a wall or the outside of the board.</li>
</ol>

<p>Note that marked cells cannot be chosen in step 1, but they can be changed to walls in step 2.</p>

<p>Fig.1 shows an example of a move in which a player chooses the cell at the third row and the fourth column.</p>

<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/11717/1.png" style="height:192px; width:327px"></p>

<p style="text-align: center;">fig.1: An example of a move in Wall Making Game.</p>

<p>Your task is to write a program that determines which player wins the game if the two players play optimally from a given initial board.</p>

<p style="text-align: center;"> </p>

### 입력 

 <p>The first line of the input consists of two integers H and W (1 ≤ H, W ≤ 20), where H and W are the height and the width of the board respectively. The following H lines represent the initial board. Each of the H lines consists of W characters.</p>

<p>The j-th character of the i-th line is ‘.’ if the cell at the j-th column of the i-th row is empty, or ‘X’ if the cell is marked.</p>

### 출력 

 <p>Print “First” (without the quotes) in a line if the first player wins the given game. Otherwise, print “Second” (also without the quotes) in a line.</p>

