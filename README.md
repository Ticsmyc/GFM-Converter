# Introduction

- Replace `[TOC]` tag into generated catalogue.
- Use CodeCogs to display Mathjax formula.
- Escape some spacial characters.

# Demo

Go to [demo.md](https://github.com/CyC2018/GFM-Converter/blob/master/demo.md) to see the result.

```html
[TOC]

# Title-1 MathJax

$$ f = \frac{a}{b} $$

# title-2（abc）

## title-21、xyz

## title-22   ijk

### title-221

### title-222

### title-223

# title-3
```

```html
<!-- GFM-TOC -->
* [Title-1 MathJax](#title-1-mathjax)
* [title-2（abc）](#title-2abc)
    * [title-21、xyz](#title-21xyz)
    * [title-22   ijk](#title-22---ijk)
        * [title-221](#title-221)
        * [title-222](#title-222)
        * [title-223](#title-223)
* [title-3](#title-3)
<!-- GFM-TOC -->


# Title-1 MathJax

![](http://latex.codecogs.com/gif.latex?\\\\$$f=\frac{a}{b}$)

# title-2（abc）

## title-21、xyz

## title-22   ijk

### title-221

### title-222

### title-223

# title-3


```

# Useage

You can just run App.java, and then specify the path of Markdown document. After that, a new file suffixed with `.gfm`. will be created.

```java
javac -encoding UTF-8 App.java
java App
```

You can also use below API in your program.

```java
GFMConverter.convert(srcFilePath, detFilePath);
```
