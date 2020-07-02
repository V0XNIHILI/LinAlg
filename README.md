# LinAlg: Matrix operations in Java

**Written by [Douwe den Blanken](https://nl.linkedin.com/in/douwedenblanken) ([v0xnihili](https://github.com/V0XNIHILI/))**

## Motivation

To write!

## Example usage

### Creating a new Matrix

```java
// Write Matrix constructors like this so they are easy to read
Matrix a = new Matrix(new double[][]{
        {1, 2},
        {3, 4}
});

// You can also specify a 1D array with a certain width and height as constructor input
Matrix b = new Matrix(new double[]{1, 2, 3, 4, 5, 6}, 3, 2);
```

### Performing linear algebra

```java
// Calculate the third power of a matrix
Matrix c = LinAlg.pow(a, 3);
```

## Performance

Tested on a 2014 MacBook Pro with 16 GB of RAM and a 2.5 GHz Quad-Core Intel Core i7. Data is in
milliseconds and is averaged over 3 runs.

| Matrix size (n * n) | Java (using double[][]) | Java (using double[]) | Python 3.7  | Numpy (uses compiled C) | Java (Intstream) | NodeJS |
|---------------------|-------------------------|-----------------------|-------------|-------------------------|------------------|--------|
| 1                   | 0                       | 0                     | 0           | 0                       | 0                | 0      |
| 2                   | 0                       | 0                     | 0           | 0                       | 0                | 0      |
| 4                   | 0                       | 0                     | 0           | 0                       | 1                | 0      |
| 8                   | 0                       | 0                     | 0           | 0                       | 1                | 0      |
| 16                  | 0                       | 0                     | 2           | 0                       | 0                | 1      |
| 32                  | 3                       | 2                     | 17          | 0                       | 1                | 13     |
| 64                  | 9                       | 2                     | 113         | 0                       | 6                | 1      |
| 128                 | 29                      | 13                    | 1048        | 0                       | 28               | 11     |
| 256                 | 85                      | 47                    | 8026        | 2                       | 13               | 103    |
| 512                 | 579                     | 414                   | 64126       | 3                       | 99               | 1144   |
| 1024                | 12908                   | 7312                  | too long... | 29                      | 2317             | 16292  |

## Future improvements

- Parallelize every calculation
