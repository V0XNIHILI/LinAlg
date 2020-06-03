import time
from random import random

size = 1

while size < 2048:
    a = [[random() for x in range(size)] for y in range(size)] 

    result = [[0.0 for x in range(size)] for y in range(size)] 

    start = time.time()

    for i in range(len(a)):
        for j in range(len(a[0])):
            # iterate through rows of Y
            for k in range(len(a)):
                result[i][j] += a[i][k] * a[k][j]

    end = time.time()

    print(int(round((end-start) * 1000)))

    size *= 2
