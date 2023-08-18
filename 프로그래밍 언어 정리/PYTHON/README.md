# PYTHON (Python 3)

백준 문제를 풀며 공부한 PYTHON에 대해 정리한 내용들입니다.
<br><br>

***
* **sys.stdin.readline**  
빠른 입출력을 위해 사용되며, 개행문자까지 입력받으므로 .rstrip()을 해주는 것이 좋다.  
다음 코드처럼 사용할 수 있다.

``` Python
import sys

input = sys.stdin.readline
a = input().rstrip()
```
<br>

* **bisect left / bisect right**  
이진 탐색을 쉽게 구현하게 해주는 함수이다.  
bisect_left는 lower bound , bisect_right는 upper bound로 생각하면 된다.  
참고 : [bisect](https://docs.python.org/ko/3/library/bisect.html)

``` Python
from bisect import bisect left, bisect right

lst = [1, 3, 8, 3, 5, 6, 3, 7, 5]
lst.sort()
print(bisect_left(lst, 3), bisect_right(lst, 5), sep = '\n')
```
<br>

* **Counter**  
리스트의 각 요소들이 몇 번 나오는지 딕셔너리 형태로 반환 해준다.

``` Python
from collections import Counter

lst = [1, 3, 5, 10]
dic = Counter(lst)
```
<br>
