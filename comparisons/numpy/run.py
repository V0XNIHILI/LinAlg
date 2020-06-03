import numpy as np
import time

size = 1

while size < 2048:
    a = np.random.rand(size,size)

    start = time.time()

    b = np.matmul(a, a);

    end = time.time()

    print(int(round((end-start) * 1000)))

    size *= 2