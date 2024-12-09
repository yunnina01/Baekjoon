# [Platinum V] Building the Moat - 6194 

[문제 링크](https://www.acmicpc.net/problem/6194) 

### 성능 요약

메모리: 18296 KB, 시간: 196 ms

### 분류

볼록 껍질, 기하학

### 제출 일자

2024년 12월 9일 10:30:27

### 문제 설명

<p>To repel the invading thirsty aardvarks, Farmer John wants to build a moat around his farm.  He owns N (8 <= N <= 5,000) watering holes, and will be digging the moat in a straight line between pairs of them.  His moat should protect (i.e., surround) all his watering holes; every watering hole must be on or inside the moat, and the moat must form a closed loop.</p>

<p>Digging a moat is expensive work, and frugal FJ wants his moat to have the minimum length possible.  Find the length of the shortest moat he can construct.</p>

<p>The unique holes are each located at integer coordinates in the range (1..45,000, 1..45,000). FJ has noticed that no three watering holes lie along the same line.</p>

<p>Consider this grid where the 20 '*'s represent watering holes:</p>

<pre>...*-----------------*......
../..........*........\.....
./.....................\....
*......................*\...
|..........*........*....\..
|*........................\.
|..........................*
*..........................|
.\*........................|
..\.....................*..|
...\........*............*.|
....\..................*...*
.....\..*..........*....../.
......\................../..
.......*----------------*...</pre>

<p>The enclosing lines are the shortest possible path that can enclose this set of watering holes.</p>

<p>The line displacements, starting with the top line are: (18,0), (6,-6), (0,-5), (-3,-3), (-17,0), (-7,7), (0,4), and (3,3).  This yields a distance of 70.8700576850888(...). Our output will print only two decimal places, so the distance will be reported as 70.87.</p>

### 입력 

 <ul>
	<li>Line 1: A single integer, N</li>
	<li>Lines 2..N+1: Two space-separated integers, X_i and Y_i that specify the location of a watering hole.</li>
</ul>

<p> </p>

### 출력 

 <ul>
	<li>Line 1: A single number D, the shortest possible length of moat. Print this number to two decimal places.</li>
</ul>

