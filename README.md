[<img src="https://raw.githubusercontent.com/l3r8yJ/sa-tan/8f6e97d0287f4f922b6cd685548490a48e26c496/s8an.svg" width="150"/>](https://www.l3r8y.ru/sa-tan/)

[![Managed By Self XDSD](https://self-xdsd.com/b/mbself.svg)](https://self-xdsd.com/p/l3r8yJ/sa-tan?provider=github)

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/eo)](http://www.rultor.com/p/l3r8yJ/sa-tan)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![maven central](http://maven-badges.herokuapp.com/maven-central/ru.l3r8y/sa-tan/badge.svg)](https://search.maven.org/artifact/ru.l3r8y/sa-tan)
[![javadoc](https://javadoc.io/badge2/ru.l3r8y/sa-tan/javadoc.svg)](https://javadoc.io/doc/ru.l3r8y/sa-tan)
[![codecov](https://codecov.io/gh/l3r8yJ/sa-tan/branch/master/graph/badge.svg?token=G1YJ0GTB8W)](https://codecov.io/gh/l3r8yJ/sa-tan)

[![Hits-of-Code](https://hitsofcode.com/github/l3r8yJ/sa-tan)](https://hitsofcode.com/view/github/l3r8yJ/sa-tan)
[![Lines-of-Code](https://tokei.rs/b1/github/l3r8yJ/sa-tan)](https://github.com/l3r8yJ/sa-tan)
[![PDD status](http://www.0pdd.com/svg?name=l3r8yJ/sa-tan)](http://www.0pdd.com/p?name=l3r8yJ/sa-tan)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/l3r8yJ/sa-tan/blob/master/LICENSE.txt)

**Motivation**. We don't have such tool that will validate our project OOP practices,
tells exactly where we are doing wrong.

OOPCOP is a static analysis tool and a Maven plugin that will help you
model your objects, classes, methods properly by rejecting your **non-perfect** code.
These things we don't tolerate:
* -ER class names e.g. Parser, Validator, Controller ([why?](https://www.yegor256.com/2015/03/09/objects-end-with-er.html))
* Utility classes ([why?](https://www.yegor256.com/2014/05/05/oop-alternative-to-utility-classes.html))
* Mutable Objects ([why?](https://www.yegor256.com/2014/06/09/objects-should-be-immutable.html))
* Getters ([why?](https://www.yegor256.com/2014/09/16/getters-and-setters-are-evil.html))
* Objects without state ([why?](https://www.yegor256.com/2014/12/15/how-much-your-objects-encapsulate.html))
* Long class names

**How to use**. All you need is this (get the latest version [here](https://search.maven.org/artifact/ru.l3r8y/sa-tan)):

Maven:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>ru.l3r8y</groupId>
      <artifactId>sa-tan</artifactId>
      <executions>
        <execution>
          <goals>
            <goal>search</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

if you want to run plugin directly:
```bash
$ mvn ru.l3r8y:sa-tan:search
```

## Mutable Objects

These classes are valid

```java
import ru.l3r8y.annotations.Mutable;

class MyValidClass {
    private final String name;

    public MyValidClass(final String n) {
        this.name = n;
    }
}

@Mutable
class MarkedClass {
    private String name;

    public void setName(final String name) {
        this.name = name;
    }
}
```

This class is invalid

```java
class MyInvalidClass {
    private String name;

    public MyValidClass(final String n) {
        this.name = n;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
```

## Parsers, Validators, Controllers

TBD..

## Utility classes

TBD..

## Getters

TBD..

## Objects without state

TBD..

## Long class names

TBD..

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.8+ and Java 8+.
