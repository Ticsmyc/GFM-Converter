# Introduction

- Replace `[TOC]` tag into generated catalogue.
- Use CodeCogs to display Mathjax formula.
- Escape some spacial characters.

# Demo

[demo.md](https://github.com/CyC2018/GFM-Converter/blob/master/demo.md)

```html
[TOC]

# Title-1 MathJax Line

$$ f = \frac{a}{b} $$

# Title-2 MathJax Inline

$\vec{a}$ and $\vec{b}$

# title-3（abc） img

![](https://github.com/CyC2018/Interview-Notebook/blob/master/pics/00d8d345-cd4a-48af-919e-209d2788eca7.jpg)

## title-21、xyz center

<center>abc</center>

## title-22 center img

<center>![](https://github.com/CyC2018/Interview-Notebook/blob/master/pics/00d8d345-cd4a-48af-919e-209d2788eca7.jpg)</center>

### title-221

**abc**def

### title-222

### title-223

# title-3
```

```html
<!-- GFM-TOC -->
* [Title-1 MathJax Line](#title-1-mathjax-line)
* [Title-2 MathJax Inline](#title-2-mathjax-inline)
* [title-3（abc） img](#title-3abc-img)
    * [title-21、xyz center](#title-21xyz-center)
    * [title-22 center img](#title-22-center-img)
        * [title-221](#title-221)
        * [title-222](#title-222)
        * [title-223](#title-223)
* [title-3](#title-3)
<!-- GFM-TOC -->


# Title-1 MathJax Line

<div align="center"><img src="https://latex.codecogs.com/gif.latex?f=\frac{a}{b}"/></div> <br>

# Title-2 MathJax Inline

<img src="https://latex.codecogs.com/gif.latex?\vec{a}"/> and <img src="https://latex.codecogs.com/gif.latex?\vec{b}"/>

# title-3（abc） img

![](https://github.com/CyC2018/Interview-Notebook/blob/master/pics/00d8d345-cd4a-48af-919e-209d2788eca7.jpg)

## title-21、xyz center

<br><div align="center"> abc </div><br>

## title-22 center img

<br><div align="center"> <img src="https://github.com/CyC2018/Interview-Notebook/blob/master/pics/00d8d345-cd4a-48af-919e-209d2788eca7.jpg"/> </div><br>

### title-221

**abc** def

### title-222

### title-223

# title-3

```

# Useage

You can just run App.java, and then specify the path of Markdown document. After that, a new file suffixed with `.gfm` will be created.

```java
javac -encoding UTF-8 App.java
java App
```

You can also use below API in your program.

```java
GFMConverter.convert(srcFilePath, detFilePath);
```
