# Introduction

- Replace `[TOC]` tag into generated catalogue.
- Use CodeCogs to display Mathjax formula.
- Escape some spacial characters.

# Demo

[Demo.md](https://github.com/CyC2018/GFM-Converter/blob/master/Demo.md)

```text
[TOC]

# Title-1

$$ f = \frac{a}{b} $$

# Title-2

$ \vec{a} $ and $ \vec{b} $

# title-3

## title-21

<center> abc </center>

## title-22

<center> ![](pics/1.png) </center>

### title-221

**abc**def

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
