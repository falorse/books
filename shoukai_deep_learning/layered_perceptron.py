import numpy as np
import tensorflow as tf

X = np.array([[0,0],[0,1],[1,0],[1,1]])
Y = np.array([[0],[1],[1],[0]])

x = tf.placeholder(tf.float32, shape=[None, 2])
t = tf.placeholder(tf.float32, shape=[None, 1])

# 2*2行列
# 切断正規分布に従うデータを生成している
w = tf.Variable(tf.truncated_normal([2,2]))
b = tf.Variable(tf.zeros([2]))
# wが2*2行列なので、hも２つの要素のベクトルになる
h = tf.nn.sigmoid(tf.matmul(x, w) + b)

v = tf.Variable(tf.truncated_normal([2,1]))
c = tf.Variable(tf.zeros([1]))
y = tf.nn.sigmoid(tf.matmul(h, v) + c)

cross_entropy = (-1) * tf.reduce_sum(t * tf.log(y) + (1 - t) * tf.log(1 - y))

train_step = tf.train.GradientDescentOptimizer(0.1).minimize(cross_entropy)
correct_prefiction = tf.equal(tf.to_float(tf.greater(y,0.5)) , t)

init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)

for epoch in range(8001):
    sess.run(train_step, feed_dict={
        x: X,
        t: Y
    })

    if epoch % 1000 == 0:
        print('epoch', epoch)

        classified = correct_prefiction.eval(session = sess, feed_dict={
            x: X,
            t: Y
        })

        prob = y.eval(session= sess, feed_dict={
            x: X
        })
        print('classified', classified)
        print('prob', prob)
