import numpy as np
import tensorflow as tf
import matplotlib.pyplot as plt
from tensorflow.examples.tutorials.mnist import input_data
from sklearn.utils import shuffle
from sklearn.model_selection import train_test_split


# モデルのクラス化
class DNN(object):
    # 初期化処理
    # モデルの構成を決めてしまうのが望ましいので、引数として各層の次元数を受け取れるようにする
    # 各層の重みとバイアスもモデルの構成を決めるので、定義しておく
    def __init__(self, n_in, n_hiddens, n_out):
        self._n_in = n_in
        self._n_hiddens = n_hiddens
        self._n_out = n_out
        self._weights = []
        self._biases = []
        self._x = None
        self._t = None
        self._sess = None
        self._keep_prob = None
        self._history = {
            'train_acc': [],
            'train_loss': [],
            'test_acc': [],
            'test_loss': []
        }

    def weight_variable(self, shape):
        initial = tf.truncated_normal(shape, stddev = 0.01)
        return tf.Variable(initial)

    def bias_variable(self, shape):
        initial = tf.zeros(shape)
        return tf.Variable(initial)

    # モデルの定義
    def inference(self, x, keep_prob):
        for i, n_hidden in enumerate(self._n_hiddens):
            if i == 0:
                input = x
                input_dim = self._n_in
            else:
                input = output
                input_dim = self._n_hiddens[i]

            weight = self.weight_variable([input_dim, n_hidden])
            bias = self.bias_variable([n_hidden])
            self._weights.append(weight)
            self._biases.append(bias)

            h = tf.nn.relu(
                tf.matmul(input, self._weights[-1]) +
                           self._biases[-1]
                )
            output = tf.nn.dropout(h, keep_prob)

        weight = self.weight_variable([self._n_hiddens[-1], self._n_out])
        bias = self.bias_variable([self._n_out])

        self._weights.append(weight)
        self._biases.append(bias)

        y = tf.nn.softmax(tf.matmul(
            output, self._weights[-1]) + self._biases[-1])

        return y

    # 誤差関数の定義
    def loss(self, y, t):
        loss = -tf.reduce_sum(t * tf.log(tf.clip_by_value(y, 1e-10, 1.0)),
                              reduction_indices=[1])
        cross_entropy = tf.reduce_mean(loss)

        return cross_entropy

    # 学習アルゴリズムの定義
    def training(self, loss):
        learning_rate = 0.01
        optimizer = tf.train.GradientDescentOptimizer(learning_rate)
        train_step = optimizer.minimize(loss)

        return train_step

    def accuracy(self, y, t):
        correct_prefiction = tf.equal(tf.argmax(y, 1), tf.argmax(t, 1))
        accuracy = tf.reduce_mean(tf.cast(correct_prefiction, tf.float32))

        return accuracy

    # 学習の処理
    def fit(self, X_train, Y_train, X_test, Y_test,\
            epochs=100, batch_size=100, p_keep=0.5, verbose=1):
            x = tf.placeholder(tf.float32, shape=[None, self._n_in])
            t = tf.placeholder(tf.float32, shape=[None, self._n_out])
            keep_prob = tf.placeholder(tf.float32)

            self._x = x
            self._t = t
            self._keep_prob = keep_prob

            y = self.inference(x, p_keep)
            loss = self.loss(y, t)
            train = self.training(loss)
            accuracy = self.accuracy(y, t)

            init = tf.global_variables_initializer()
            sess = tf.Session()
            sess.run(init)

            self._sess = sess

            N_train = len(X_train)
            n_batches = N_train // batch_size

            for epoch in range(epochs):
                X_, Y_ = shuffle(X_train, Y_train)

                for i in range(n_batches):
                    start = i * batch_size
                    end = start * batch_size

                    sess.run(train, feed_dict={
                        x: X_[start:end],
                        t: Y_[start:end],
                        keep_prob: p_keep
                    })

                loss_ = loss.eval(session=sess, feed_dict={
                    x: X_train,
                    t: Y_train,
                    keep_prob: 1.0
                })

                accuracy_ = accuracy.eval(session=sess, feed_dict={
                    x: X_train,
                    t: Y_train,
                    keep_prob: 1.0
                })


                self._history['train_loss'].append(loss_)
                self._history['train_acc'].append(accuracy_)


                loss_ = loss.eval(session=sess, feed_dict={
                    x: X_test,
                    t: Y_test,
                    keep_prob: 1.0
                })

                accuracy_ = accuracy.eval(session=sess, feed_dict={
                    x: X_test,
                    t: Y_test,
                    keep_prob: 1.0
                })

                self._history['test_loss'].append(loss_)
                self._history['test_acc'].append(accuracy_)

                if verbose:
                    print('epoch:', epoch, ' loss:', loss_,
                          ' accuracy:', accuracy_)

            return self._history


    # 評価の処理
    def evaluate(self, X_test, Y_test):
        accuracy = self.accuracy
        return accuracy.eval(session=self._sess, feed_dict={
            self._x: X_test,
            self._t: Y_test,
            self._keep_prob: 1.0
        })


if __name__ == '__main__':
    # 1.データの準備
    # 2.モデルの設計
    # 3.モデルの学習
    # 4.モデルの評価

    # 1.データの準備
    mnist = input_data.read_data_sets("/tmp/data/", one_hot=True)
    X_train = mnist.train.images
    Y_train = mnist.train.labels
    X_test = mnist.test.images
    Y_test = mnist.test.labels

    epochs = 50
    model = DNN(n_in=784, n_hiddens=[200], n_out=10)
    history = model.fit(X_train, Y_train, X_test, Y_test,\
                        epochs, batch_size=200, p_keep=0.5, verbose=True)

    # accuracy = model.evaluate(X_test, Y_test)
    # print('accuracy:', accuracy)

    fig = plt.figure()

    plt.plot(range(epochs), history['test_acc'], label='acc')
    plt.xlabel('epochs')
    plt.ylabel('test_accuracy')

    plt.show()
    # plt.savefig('mnist_tensorflow.eps')
