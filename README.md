# Newton Fractal Generator

## Introduction
First and foremost, what is fractal?

In mathematics, a fractal is a detailed, recursive, and infinitely self-similar mathematical set whose Hausdorff dimension strictly exceeds its topological dimension and which is encountered ubiquitously in nature. Fractals exhibit similar patterns at increasingly small scales, also known as expanding symmetry or unfolding symmetry. If this replication is exactly the same at every scale, as in the Menger sponge, it is called a self-similar pattern. (Wikipedia)
More general information can be found [Here](https://en.wikipedia.org/wiki/Fractal)

This fractal generator uses the Newton-Raphson method several times in order to generate the fractal. More backgroud information about this method can be found [Here](https://en.wikipedia.org/wiki/Newton%27s_method). This generator uses the fact that the method works for complex number as well.

## What does it look like? 
<p align="center">
  <img src="./sample.png" alt="fractal example"
       width="700" height="394">
</p>

## Building the fractal
The generated fractal is actually a grid of pixels (9600X5400 in our case). The grid can also be thought as complex plane (z-plane), where each complex nubmer is represented by a different pixel in the fractal image. Since there is a finite amount of pixels in the image, we do a simple complex numbers value quantization in the complex plane. In our case, the quantized range on the plane is [-6.4,6.4]X[-3.6X3.6]. That is to say, the plane's origin is at the center of the image.

The processing session includes m X n executions of the Newton-Raphson method (m,n are the image dimensions). Each execution has an identical goal: finding all the roots (complex and real) of the equation p(z) = z^3 - 1. The difference between all the executions is the initial guess of the method. Each execution's initial guess refers to a different complex value in the image. For each execution we count how many iterations of Newton-Raphson method were needed for convergence to a root of the equation. The different colors in the image refer to a different number of iteration for each initial guess.

For more infomation about this fractal and some variations of it: [Wikipedia](https://en.wikipedia.org/wiki/Newton_fractal)

## Code
The code consists of four main classes: 
* Main - Takes care of initialization, splitting the image to pixels, and run the method's iteration for each initial guess, as explained above
* Pixel - represets the properties of each pixel in the image
* ComplexNumber - represents anything which has to do with complex number in the project
* ImageLib - takes care of translating the generated pixels to an actual image
