[![Managed By Self XDSD](https://self-xdsd.com/b/mbself.svg)](https://self-xdsd.com/p/l3r8yJ/oop-cop?provider=github)

[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/eo)](http://www.rultor.com/p/l3r8yJ/oop-cop)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![maven central](http://maven-badges.herokuapp.com/maven-central/ru.l3r8y/oop-cop/badge.svg)](https://search.maven.org/artifact/ru.l3r8y/oop-cop)
[![javadoc](https://javadoc.io/badge2/ru.l3r8y/oop-cop/javadoc.svg)](https://javadoc.io/doc/ru.l3r8y/oop-cop)
[![codecov](https://codecov.io/gh/l3r8yJ/oop-cop/branch/master/graph/badge.svg?token=G1YJ0GTB8W)](https://codecov.io/gh/l3r8yJ/oop-cop)

[![Hits-of-Code](https://hitsofcode.com/github/l3r8yJ/oop-cop)](https://hitsofcode.com/view/github/l3r8yJ/oop-cop)
[![Lines-of-Code](https://tokei.rs/b1/github/l3r8yJ/oop-cop)](https://github.com/l3r8yJ/oop-cop)
[![PDD status](http://www.0pdd.com/svg?name=l3r8yJ/oop-cop)](http://www.0pdd.com/p?name=l3r8yJ/oop-cop)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/l3r8yJ/oop-cop/blob/master/LICENSE.txt)

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

**How to use**. All you need is this (get the latest version [here](https://search.maven.org/artifact/ru.l3r8y/oop-cop)):

Maven:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>ru.l3r8y</groupId>
      <artifactId>oop-cop</artifactId>
      <version>0.1.6</version>
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
$ mvn ru.l3r8y:oop-cop:search
```

## Mutable Objects

These classes are valid:

```java
import ru.l3r8y.annotations.Mutable;

class MyValidClass {
    private final String name;

    public MyValidClass(final String n) {
        this.name = n;
    }
}
```

This class is invalid:

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

check can be suppressed using: `@SuppressedWarnings("OOP.MutableStateCheck")`.

## Parsers, Validators, Controllers

These examples are valid:

```java
class ParsedFile {
  ...
  public String asText() {
      ...
  }   
}
```

While this is invalid:

```java
class FileParser {
    ...
    public String parse() {
        ...
    }
}
```

this check can be suppressed using: `@SupressWarnings("OOP.ErSuffixCheck")`.

## Utility classes

TBD..

## Getters

TBD..

## Objects without state

TBD..

## Long class names

This example is valid:

```java
class PgItem {
    ...
}
```

while this is broken:
```java
class AbstractDatabaseConnection {
    ...
}
```

to configure the maximal reasonable length consider using the following parameter:
```xml
<plugin>
  <groupId>ru.l3r8y</groupId>
  <artifactId>oop-cop</artifactId>
  ...
  <configuration>
    <maxClassNameLen>15</maxClassNameLen>
    <!-- default is 13 -->
  </configuration>
</plugin>
```

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.8+ and Java 8+.
