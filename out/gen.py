import random

def generate_matrix(size):
    print(size)
    for i in range(size):
        for j in range(size):
            print(f"{random.uniform(-100, 100):.2f} ", end='')
        print()

size = 10
generate_matrix(size)
