import numpy as np
import tensorflow as tf

# モデルの定義
w = tf.Variable(tf.zeros([2,1]))
b = tf.Variable(tf.zeros([1]))

def y(x):
    return sigmoid(np.dot(w, x) + b)

def sigmoid(x):
    return 1 / (1 + np.exp(-x))

x = tf.placeholder(tf.float32, shape=[None, 2])
t = tf.placeholder(tf.float32, shape=[None, 1])
y = tf.nn.sigmoid(tf.matmul(x, w) + b)

# reduce_sum で　tf.placeholderが持つ全部のデータに対して足し算してくれる
# 誤差関数の定義
cross_entropy = - tf.reduce_sum(t * tf.log(y) + (1 - t) * tf.log(1 - y))

# 最適化手法の定義
train_step = tf.train.GradientDescentOptimizer(0.1).minimize(cross_entropy)
correct_prefiction = tf.equal(tf.to_float(tf.greater(y, 0.5)), t)

X = np.array([[0,0],[0,1],[1,0],[1,1]])
Y = np.array([[0],[1],[1],[1]])

# ここではじめてのモデルの定義で宣言した変数・式の初期化が行われる。
# セッションの初期化
init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)

for epoch in range(200):
    # placeholderの値は、この時にfeed_dictで与える。
    # 学習
    sess.run(train_step, feed_dict={
        x: X,
        t: Y
    })

# Variableを含む式を表示させたいときは、変数を与える必要がある。
print(correct_prefiction.eval(session=sess, feed_dict={
    x: X,
    t: Y
}))

print(y.eval(session=sess, feed_dict={
    x: X
}))
