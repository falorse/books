import numpy as np

rng = np.random.RandomState(123)

d = 2
N = 10
mean = 5

x1 = rng.randn(N, d) + np.array([0,0])
x2 = rng.randn(N, d) + np.array([mean,mean])

# x1, x2をひとまとめにしておく
x = np.concatenate((x1, x2), axis = 0)

# 重みベクトル
w = np.zeros(d)
# 重みバイアス
b = 0

# 出力は y = f(w.T * x + b)で表される
def y(x):
    return step_func(np.dot(w, x) + b)

def step_func(x):
    if x > 0:
        return 1
    else:
        return 0

# 正解データ（ラベル）。最初の10個のラベルが0で移行が1なのでこうなる
def t(i):
    if i < N:
        return 0
    else:
        return 1

# 誤り訂正学習法の実装
while True:
    classified = True

    for i in range(N * 2):
        # Δw = (t - y) * x
        delta_w = (t(i) - y(x[i])) * x[i]

        # Δb = t - y
        delta_b = (t(i) - y(x[i]))

        w += delta_w
        b += delta_b

        classified *= all(delta_w == 0) * (delta_b == 0)

    print(w, b)
    if classified:
        break
