import numpy as np
import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data


# モデルのクラス化
class DNN(object):
    # 初期化処理
    # モデルの構成を決めてしまうのが望ましいので、引数として各層の次元数を受け取れるようにする
    # 各層の重みとバイアスもモデルの構成を決めるので、定義しておく
    def __init__(self, n_in, n_hiddens, n_out):
        self._n_in = n_in
        self._n_hiddens = n_hiddens
        self._n_out = n_out
        self.weights = []
        self.biases = []

    def weight_variable(self, shape):
        initial = tf.truncated_normal(shape, stddev = 0.01)
        return tf.Variable(initial)

    def bias_variable(self, shape):
        initial = tf.zeros(shape)
        return tf.Variable(initial)

    # モデルの定義
    def inference(self, x, keep_prob):

        retunt y

    # 誤差関数の定義
    def loss(self, y, t):
        loss = -tf.reduce_sum(t * tf.log(y),
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
    def fit(self, X_train, Y_train):

    # 評価の処理
    def evaluate(self, X_test, Y_test):


if __name__ == '__main__':
    # 1.データの準備
    # 2.モデルの設計
    # 3.モデルの学習
    # 4.モデルの評価

    # 1.データの準備
    mnist = input_data.read_data_sets("/tmp/data/", one_hot=True)

    # モデルの設計
    n_in = 784
    n_hiddens = [200, 200, 200]
    n_out = 10

    x = tf.placeholder(tf.float32, shape=[None, n_in])
    keep_prob = tf.placeholder(tf.float32)

    y = inference(x, keep_prob, n_in, n_hiddens, n_out)
