# [Platinum III] Job Postings - 7154 

[문제 링크](https://www.acmicpc.net/problem/7154) 

### 성능 요약

메모리: 305524 KB, 시간: 2640 ms

### 분류

최대 유량, 최소 비용 최대 유량, 그래프 이론

### 제출 일자

2025년 5월 21일 06:09:01

### 문제 설명

<p>Your university has jobs available for students, and the administration needs for you to help them assign students to jobs. The goal is to have students select their desired positions, and then allocate each student to one of the positions so that the maximum satisfaction is achieved.</p>

<p>Each student selects four positions, in the order of desirability. First position is most wanted, next position is next most wanted, if the first position is not available for this student, and so on.</p>

<p>Students have seniority, based their year of study. Third-year students’ selections should have more weight than first-year students’ selections..</p>

<p>The administration wants for you to use the following satisfaction matrix:</p>

<table class="table table-bordered table-striped" style="width:40%">
	<thead>
		<tr>
			<th>Job/Position choice:</th>
			<th>1st</th>
			<th>2nd</th>
			<th>3rd</th>
			<th>4th</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<th>1st year student:</th>
			<td>4</td>
			<td>3</td>
			<td>2</td>
			<td>1</td>
		</tr>
		<tr>
			<th>2nd year student:</th>
			<td>8</td>
			<td>7</td>
			<td>6</td>
			<td>5</td>
		</tr>
		<tr>
			<th>3rd year student:</th>
			<td>12</td>
			<td>11</td>
			<td>10</td>
			<td>9</td>
		</tr>
	</tbody>
</table>

<p>Your task is to assign students to positions in a way that maximizes the sum of all students’ satisfaction according to the above matrix. Each student must get a position, but all positions may not be filled. </p>

### 입력 

 <p>There will be multiple test cases in the input. Each test case will begin with two integers, n (4≤n≤140) and m (1≤m≤70), where n is the number of postings and m is the number of students. Each of the next n lines will contain a single integer p (1≤p≤10), indicating the number of positions available for that job posting. The job postings are listed in order, from job 0 to job n-1.</p>

<p>Following the job postings will be m lines describing the students. Each student line will have five integers:</p>

<pre>y c1 c2 c3 c4</pre>

<p>Where y (y=1, 2 or 3) is the student’s year of study, and c1, c2, c2 and c4 (0≤c1,c2,c3,c4<n, all four unique) indicating the student’s choice of job postings, in order of preference.</p>

<p>It is guaranteed for every test case that a solution exists where every student can get one of the positions on their choice list.</p>

<p>The input will end with a line with two 0s.</p>

### 출력 

 <p>For each test case, output a single integer, which indicates the maximum satisfaction achievable. Output no spaces, and do not output a blank line between answers.</p>

