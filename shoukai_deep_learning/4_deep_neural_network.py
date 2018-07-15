import numpy as np
import tensorflow as tf

from tensorflow.examples.tutorials.mnist import input_data

mnist = input_data.read_data_sets("/tmp/data/", one_hot=True)

#変数定義
x = tf.placeholder(tf.float32, shape=[None, 784])
t = tf.placeholder(tf.float32, shape=[None, 10])

#モデル設定
n_in = 784
n_hidden1 = 400
n_hidden2 = 400
n_hidden3 = 400
n_out = 10

w1 = tf.Variable(tf.truncated_normal([n_in, n_hidden1]))
b1 = tf.Variable(tf.zeros([n_hidden1]))
h1 = tf.nn.relu(tf.matmul(x, w1) + b1)

w2 = tf.Variable(tf.zeros([n_hidden1, n_hidden2]))
b2 = tf.Variable(tf.zeros([n_hidden2]))
h2 = tf.nn.relu(tf.matmul(h1, w2) + b2)

w3 = tf.Variable(tf.zeros([n_hidden2, n_hidden3]))
b3 = tf.Variable(tf.zeros([n_hidden3]))
h3 = tf.nn.relu(tf.matmul(h2, w3) + b3)

w4 = tf.Variable(tf.zeros([n_hidden3, n_out]))
b4 = tf.Variable(tf.zeros([n_out]))
y = tf.nn.softmax(tf.matmul(h3, w4) + b4)

#最小化する誤差関数
loss = -tf.reduce_sum(t * tf.log(y) + (1 - t) * tf.log(1 - y))

#最適化手法の定義
train_step = tf.train.AdamOptimizer(0.5).minimize(loss)

correct_prefiction = tf.equal(tf.argmax(y, 1), tf.argmax(t, 1))
accuracy = tf.reduce_mean(tf.cast(correct_prefiction, tf.float32))

sess = tf.Session()
sess.run(tf.initialize_all_variables())
i = 0
epoch = 10000
test_epoch = 100
for _ in range(epoch):
    batch_xs, batch_ts = mnist.train.next_batch(100)
    sess.run(train_step, feed_dict={
        x: batch_xs, t:batch_ts
    })

    if i % test_epoch == 0:
        loss_val, acc_val = sess.run([loss, accuracy], feed_dict={
            x:batch_xs , t:batch_ts
        })
        print("loss_val:", loss_val, " acc_val:", acc_val)
        loss_val, acc_val = sess.run([loss, accuracy], feed_dict={
            x: mnist.test.images, t:mnist.test.labels
        })
        print("loss_val:", loss_val, " acc_val:", acc_val)

    i+=1
