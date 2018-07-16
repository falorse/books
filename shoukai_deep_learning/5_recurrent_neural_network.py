import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt
from tensorflow.examples.tutorials.mnist import input_data
from sklearn.utils import shuffle
from sklearn.model_selection import train_test_split

def sin(x, T = 100):
    return np.sin(2.0 * np.pi * x / T)

def toy_problem(T = 100, ampl = 0.05):
    x = np.arange(0, 2 * T + 1)
    noise = ampl * np.random.uniform(low = -0.1, high = 1.0, size = len(x))
    return sin(x) + noise

def train_test_split(x, y, test_size):
    x_len = len(x)
    y_len = len(y)
    return x[0:x_len - test_size], x[x_len - test_size:], y[:y_len - test_size], y[y_len - test_size:]

def inference(x, n_batch, maxlen=None, n_hidden=None, n_out=None):
    def weight_variable(shape):
        initial = tf.truncated_normal(shape, stddev = 0.01)
        return tf.Variable(initial)

    def bias_variable(shape):
        initial = tf.zeros(shape)
        return tf.Variable(initial)

    cell = tf.contrib.rnn.BasicRNNCell(n_hidden)
    initial_state = cell.zero_state(40, tf.float32)

    state = initial_state

    # 過去の隠れ層の出力を保存しておく
    outputs = []
    cell_output = None
    # 変数に対して共用の名前を付けておく。
    with tf.variable_scope('RNN'):
        for t in range(maxlen):
            # 名前がついた変数を再利用することを明示しているのがこの行
            if t > 0:
                tf.get_variable_scope().reuse_variables()
            # print('t:',t, " state,cell_out, x[:, t,:]",state,"\n", cell_output, '\n', x[:,t,:])
            (cell_output, state) = cell(x[:,t,:] , state)
            outputs.append(cell_output)

    output = outputs[-1]

    v = weight_variable([n_hidden, n_out])
    c = bias_variable([n_out])
    y = tf.matmul(output, v) + c

    return y

def loss(y, t):
    # 2乗平均誤差関数MSE
    mse = tf.reduce_mean(tf.square(y - t))
    return mse

def training(loss):
    optimizer = tf.train.AdamOptimizer(
        learning_rate=0.001, beta1=0.9, beta2=0.999)

    train_step = optimizer.minimize(loss)
    return train_step


T = 100
f = toy_problem(T)

length_of_sequences = 2 * T
maxlen = 25

data = []
target = []

for i in range(0, length_of_sequences - maxlen + 1):
    data.append(f[i:i + maxlen])
    target.append(f[i + maxlen])

X = np.array(data).reshape(len(data), maxlen, 1)
Y = np.array(target).reshape(len(data), 1)

N_train = int(len(data) * 0.9)
N_validation = len(data) - N_train

X_train, X_validation, Y_train, Y_validation =\
  train_test_split(X, Y, test_size = N_validation)

n_in = len(X[0][0])
n_hidden = 20
n_out = len(Y[0])

x = tf.placeholder(tf.float32, shape=[None, maxlen, n_in])
t = tf.placeholder(tf.float32, shape=[None, n_out])
n_batch = tf.placeholder(tf.int32)

y = inference(x, n_batch, maxlen=maxlen, n_hidden=n_hidden, n_out=n_out)
loss = loss(y, t)
train_step = training(loss)

epochs = 500
batch_size = 10

init = tf.global_variables_initializer()
sess = tf.Session()
sess.run(init)

n_batches = N_train // batch_size

for epoch in range(epochs):
    x_, y_ = shuffle(X_train, Y_train)

    for i in range(n_batches):
        start = i * batch_size
        end = start * batch_size

        sess.run(train_step, feed_dict={
                x: x_[start:end],
                t: y_[start:end],
                n_batch: batch_size
        })

    val_loss = loss.eval(session=sess, feed_dict={
        x: X_validation,
        t: Y_validation,
        n_batch: N_validation
    })

    history['val_loss'].append(val_loss)
    print('epoch', epoch, ' validation loss', val_loss)

    # if early_stopping.validate(val_loss):
    #     break
