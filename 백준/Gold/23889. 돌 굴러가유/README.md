# [Gold IV] 돌 굴러가유 - 23889 

[문제 링크](https://www.acmicpc.net/problem/23889) 

### 성능 요약

메모리: 42196 KB, 시간: 628 ms

### 분류

그리디 알고리즘, 누적 합, 정렬

### 제출 일자

2024년 11월 5일 20:05:09

### 문제 설명

<p>경기북과학고등학교에 숨겨져 있는 알프스 산맥에는 많은 마을이 있다. 알프스 산맥에 살던 도현이는 충격적인 사실을 듣게 되었다. 바로, 내일 큰 돌들이 굴러가 마을을 덮칠 것이라는 사실이었다.</p>

<p>집은 부서지지 않겠지만, 마을의 아이들이 쌓아놓은 모래성이 부서질 것이므로 매우 심각한 일이었다. 돌은 모두 왼쪽에서 오른쪽으로 계속 굴러가며, 돌은 굴러가기 시작하는 마을의 모래성부터 부수기 시작한다.</p>

<p>다행히도 도현이에게는 굴러오는 돌을 막을 수 있는 벽이 있다. 하지만, 돌의 개수가 도현이가 가지고 있는 벽의 개수 이상인 것이 문제이다. 고민하는 도현이를 도와 어디에 벽을 설치해야 가장 많은 모래성을 지킬 수 있을지 알아보자.</p>

<p>벽은 설치된 마을부터 돌이 굴러가지 못하도록 막는다. 따라서 벽이 설치된 마을부터는 돌이 모래성을 부수지 못한다.</p>

### 입력 

 <p>첫 번째 줄에는 마을의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>, 가지고 있는 벽의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D440 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>M</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$M$</span></mjx-container>, 돌의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43E TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>K</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$K$</span></mjx-container>가 주어진다.</p>

<p>두 번째 줄에는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>i</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$i$</span></mjx-container>번째 마을의 모래성의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-msub><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi><mjx-script style="vertical-align: -0.15em;"><mjx-mi class="mjx-i" size="s"><mjx-c class="mjx-c1D456 TEX-I"></mjx-c></mjx-mi></mjx-script></mjx-msub></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><msub><mi>x</mi><mi>i</mi></msub></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$x_i $</span></mjx-container>가 공백으로 구분되어 주어진다.</p>

<p>세 번째 줄에는 돌이 굴러가기 시작하는 마을의 위치들이 주어진다. 마을의 위치는 맨 왼쪽에 위치한 마을이 1번째 마을, 가장 오른쪽이 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>번째 마을이다. 돌의 위치는 중복되지 않으며, 오름차순으로 주어진다.</p>

### 출력 

 <p><mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"> <mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D440 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>M</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$M$</span></mjx-container>번째 줄에 걸쳐 가장 많은 모래성을 지키기 위해 벽을 설치해야 할 마을의 위치를 오름차순으로 출력한다. 가장 많은 모래성을 지킬 수 있는 경우가 두 가지 이상 존재할 경우, 사전순으로 가장 빠른 답을 출력한다.</p>

