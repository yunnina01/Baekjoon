# [Platinum IV] 금화 게임 - 5386 

[문제 링크](https://www.acmicpc.net/problem/5386) 

### 성능 요약

메모리: 14652 KB, 시간: 136 ms

### 분류

게임 이론, 스프라그–그런디 정리

### 제출 일자

2025년 6월 1일 09:12:01

### 문제 설명

<p><img alt="" src="" style="float:right; height:135px; width:141px">해적! 이는 멋진 직업인 것 같지만 큰 고충을 가지는 직업이다. 그 고충 중 하나는 많은 시간을 망망대해에서 지내야 한다는 점이다. 때때로 바람도 불지 않고, 하루종일 아무런 일도 없이 지나가는 날이 있을 수 있는 것이다. 너무 지루하기 때문에 해적들은 금화로 하는 여러 가지 놀이를 알고 있다. 그런 놀이 중 하나로 두 명의 해적이 하나의 금화 더미를 이용해서 하는 놀이가 있다. 이 놀이는 두 사람이 서로 차례를 번갈아 바꿔가면서 하는 놀이인데, 각 해적은 자기 차례에 어떤 주어진 정수 K가 주어질 때 K의 제곱수(1, K, K<sup>2</sup>,...)만큼의 금화를 가져올 수 있다. 마지막 금화를 가져온 해적이 승리한다.</p>

<p>현재 금화 더미에 있는 금화의 개수와 K가 주어질 경우에 어떤 해적이 승리할까?</p>

### 입력 

 <p>첫 번째 줄에는 테스트 케이스의 개수가 주어진다.</p>

<p>각 테스트 케이스는 한 줄로 이루어져 있으며 두 개의 정수 S와 K로 이루어져 있다. 1 ≤ S ≤ 10<sup>9</sup>, 1 ≤ K ≤ 100을 만족한다. S는 금화의 개수를 의미하여 K는 각 차례에 1, K, K<sup>2</sup>,...개의 금화를 가져올 수 있다는 의미이다.</p>

### 출력 

 <p>각 테스트 케이스 마다 한 줄에 처음 금화를 가져가는 해적이 이기기 위해 첫 번째 차례에 가져가야 하는 금화의 개수의 최솟값을 출력한다. 만약 처음 금화를 가져가는 해적이 어떤 개수의 금화를 가져가도 이길 수 없다면 0을 출력하면 된다.</p>

