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

| Matrix size (n * n) | Java (using double[][]) | Java (using double[]) | Python 3.7  | Numpy (uses compiled C) |
|---------------------|-------------------------|-----------------------|-------------|-------------------------|
| 1                   | 0                       | 0                     | 0           | 0                       |
| 2                   | 0                       | 0                     | 0           | 0                       |
| 4                   | 0                       | 0                     | 0           | 0                       |
| 8                   | 0                       | 0                     | 0           | 0                       |
| 16                  | 0                       | 0                     | 2           | 0                       |
| 32                  | 3                       | 2                     | 17          | 0                       |
| 64                  | 9                       | 2                     | 113         | 0                       |
| 128                 | 29                      | 13                    | 1048        | 0                       |
| 256                 | 85                      | 47                    | 8026        | 2                       |
| 512                 | 579                     | 414                   | 64126       | 3                       |
| 1024                | 12908                   | 7312                  | too long... | 29                      |

## Future improvements

- Parallelize every calculation