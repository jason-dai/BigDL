/*
 * Copyright 2016 The BigDL Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.analytics.bigdl.nn

import com.intel.analytics.bigdl.nn.abstractnn.TensorCriterion
import com.intel.analytics.bigdl.tensor.Tensor
import com.intel.analytics.bigdl.tensor.TensorNumericMath.TensorNumeric

import scala.reflect.ClassTag

/**
 * Creates a criterion that can be thought of as a smooth version of the AbsCriterion.
 * It uses a squared term if the absolute element-wise error falls below 1.
 * It is less sensitive to outliers than the MSECriterion and in some
 * cases prevents exploding gradients (e.g. see "Fast R-CNN" paper by Ross Girshick).
 *
 *                       | 0.5 * (x_i - y_i)^2^, if |x_i - y_i| < 1
 * loss(x, y) = 1/n \sum |
 *                       | |x_i - y_i| - 0.5,   otherwise
 *
 * If x and y are d-dimensional Tensors with a total of n elements,
 * the sum operation still operates over all the elements, and divides by n.
 * The division by n can be avoided if one sets the internal variable sizeAverage to false
 * @param sizeAverage whether to average the loss
 */
@SerialVersionUID(3385326223989333522L)
class SmoothL1Criterion[@specialized(Float, Double) T: ClassTag](sizeAverage: Boolean = true)
                                    (implicit ev: TensorNumeric[T])
  extends TensorCriterion[T] {
  @transient var buffer: Tensor[T] = null

  override def updateOutput(input: Tensor[T], target: Tensor[T]): T = {
    require(input.nElement() == target.nElement())
    if (buffer == null) {
      buffer = Tensor[T]()
    }
    buffer.resizeAs(input).copy(input)
    buffer.add(ev.fromType(-1), target).abs()
    val data = buffer.storage().array()
    for (i <- 0 until data.length) {
      if (ev.isGreater(ev.fromType(1), data(i))) {
        data(i) = ev.times(ev.fromType[Double](0.5), ev.times(data(i), data(i)))
      }
      else {
        data(i) = ev.minus(data(i), ev.fromType[Double](0.5))
      }
    }
    var sum = buffer.sum()
    if (sizeAverage) {
      sum = ev.divide(sum, ev.fromType(input.nElement()))
    }
    output = sum
    output
  }

  override def updateGradInput(input: Tensor[T], target: Tensor[T]): Tensor[T] = {
    require(input.nElement() == target.nElement())
    val norm = ev.fromType(if (sizeAverage) 1.0 / input.nElement() else 1.0)
    if (gradInput == null) {
      gradInput = Tensor[T]()
    }
    gradInput.resizeAs(input).copy(input)
    gradInput.add(ev.fromType(-1), target)
    val data = gradInput.storage().array()
    for (i <- 0 until data.length) {
      if (ev.isGreater(ev.fromType(-1), data(i))) {
        data(i) = ev.negative(norm)
      }
      else if (ev.isGreater(data(i), ev.fromType(1))) {
        data(i) = norm
      }
      else {
        data(i) = ev.times(norm, data(i))
      }
    }
    gradInput
  }
}

object SmoothL1Criterion {
  def apply[@specialized(Float, Double) T: ClassTag](
      sizeAverage: Boolean = true)(implicit ev: TensorNumeric[T]) : SmoothL1Criterion[T] = {
    new SmoothL1Criterion[T](sizeAverage)
  }
}
