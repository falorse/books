import numpy as np
import tensorflow as tf



def inference(x, keep_prob, n_in, n_hiddens, n_out):
    def weight_variable(shape):
        initial = tf.truncated_normal(shape, stddev = 0.01)
        return tf.Variable(initial)

    def bias_variable(shape):
        initial = tf.zeros(shape)
        return tf.Varialble(initial)

    for i, n_hidden in enumerate(h_hiddens):
        if i == 0:
            input = x
            input_dim = n_in
        else:
            input = output
            input_dim = n_hiddens[i - 1]

        W = weight_variable([input_dim, h_hidden])
        b = bias_variable([n_hidden])

        h = tf.nn.relu(tf.matmul(input, w) + b)
        output = tf.mm.drop(h, keep_prob)

    w_out = weight_variable([n_hiddens[-1], n_out])
    b_out = bias_variable([n_out])

    y = tf.nn.softmax(tf.matmul(output, w_out) + b_out)

    return y


def loss(y, t):
    cross_entropy = tf.reduce_mean(
        -tf.reduce_sum(t * tf.log(y), reduction_indices=[1])
    )
    return cross_entropy

def training(loss):
    optimizer = tf.train.GradientDescentOptimizer(0.01)
    train_step = optimizer.minimize(loss)
    return train_step


if __name__ == '__main__':
    n_in = 784
    n_hiddens = [200, 200, 200]
    n_out = 10

    x = tf.placeholder(tf.float32, shape=[None, n_in])
    keep_prob = tf.placeholder(tf.float32)

    y = inference(x, keep_prob, n_in, n_hiddens, n_out)
