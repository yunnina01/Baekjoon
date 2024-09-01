# [Platinum III] Move or Block! - 31567 

[문제 링크](https://www.acmicpc.net/problem/31567) 

### 성능 요약

메모리: 20196 KB, 시간: 232 ms

### 분류

많은 조건 분기, 게임 이론

### 제출 일자

2024년 9월 2일 07:48:33

### 문제 설명

<p>「Move or Block!」 게임은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>칸의 일자형 게임보드에서 할 수 있는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>2</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$2$</span></mjx-container>인용 보드게임이다. 게임보드의 각 칸은 비어 있거나 벽이 세워져 있으며, 비어 있는 칸 중 하나에 두 플레이어가 움직일 수 있는 하나의 말을 올려두고 게임을 시작한다. 게임은 두 명의 플레이어가 턴을 번갈아 가면서 진행한다.</p>

<p>각 플레이어는 <strong>자신의 턴이 되었을 때 말의 인접한 칸에 모두 벽이 세워져 있으면 패배</strong>한다. 아니라면 아래 두 가지 행동 중 하나를 선택하여 한 번 플레이하고 턴을 넘긴다.</p>

<ul>
	<li>말을 비어 있는 인접한 칸 중 하나로 움직인다.</li>
	<li>비어 있는 칸을 하나 골라 벽을 세운다.</li>
</ul>

<p>민규와 윤수가 「Move or Block!」 게임을 하려 한다. 주어진 게임보드에서 두 플레이어 모두 최적의 방법으로 게임을 진행했을 때 이기는 플레이어를 출력한다. 게임은 민규가 먼저 시작한다.</p>

### 입력 

 <p>첫 번째 줄에 게임보드의 크기 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 주어진다.</p>

<p>두 번째 줄에 초기 게임보드의 상태로 길이가 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>인 문자열이 주어진다. 게임보드는 <code><span style="color:#e74c3c;">.</span></code>(온점),<code><span style="color:#e74c3c;">X</span></code>,<code><span style="color:#e74c3c;">O</span></code>세 문자로만 구성되어 있고, 각각 비어 있는 칸, 벽이 세워져 있는 칸, 말을 올려둔 칸을 의미한다.</p>

### 출력 

 <p>민규가 이기는 경우에는 <code><span style="color:#e74c3c;">mingyu</span></code>, 윤수가 이기는 경우에는 <code><span style="color:#e74c3c;">yunsu</span></code>, 만약 게임이 영원히 끝나지 않는다면 <code><span style="color:#e74c3c;">draw</span></code>를 출력한다.</p>

