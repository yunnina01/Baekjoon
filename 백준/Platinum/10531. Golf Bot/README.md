# [Platinum I] Golf Bot - 10531 

[문제 링크](https://www.acmicpc.net/problem/10531) 

### 성능 요약

메모리: 40416 KB, 시간: 604 ms

### 분류

고속 푸리에 변환, 수학

### 제출 일자

2024년 6월 4일 10:03:13

### 문제 설명

<p><img alt="" src="https://www.acmicpc.net/upload/images2/golf.png" style="float:right; height:392px; width:291px">Do you like golf? I hate it. I hate golf so much that I decided to build the ultimate golf robot, a robot that will never miss a shot. I simply place it over the ball, choose the right direction and distance and, flawlessly, it will strike the ball across the air and into the hole. Golf will never be played again.</p>

<p>Unfortunately, it doesn’t work as planned. So, here I am, standing in the green and preparing my first strike when I realize that the distance-selector knob built-in doesn’t have all the distance options! Not everything is lost, as I have 2 shots.</p>

<p>Given my current robot, how many holes will I be able to complete in 2 strokes or less?</p>

### 입력 

 <p>The first line has one integer: N, the number of different distances the Golf Bot can shoot. Each of the following N lines has one integer, k<sub>i</sub>, the distance marked in position i of the knob.</p>

<p>Next line has one integer: M, the number of holes in this course. Each of the following M lines has one integer, d<sub>j</sub>, the distance from Golf Bot to hole j.</p>

### 출력 

 <p>You should output a single integer, the number of holes Golf Bot will be able to complete. Golf Bot cannot shoot over a hole on purpose and then shoot backwards.</p>

