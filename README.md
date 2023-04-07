[<img src="https://raw.githubusercontent.com/l3r8yJ/sa-tan/8f6e97d0287f4f922b6cd685548490a48e26c496/s8an.svg" width="150"/>](https://www.l3r8y.ru/sa-tan/)


[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/objectionary/eo)](http://www.rultor.com/p/l3r8yJ/sa-tan)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![codecov](https://codecov.io/gh/l3r8yJ/sa-tan/branch/master/graph/badge.svg?token=G1YJ0GTB8W)](https://codecov.io/gh/l3r8yJ/sa-tan)
![](https://maven-badges.herokuapp.com/maven-central/ru.l3r8y/sa-tan/badge.svg)

A plugin for Maven that will let you know you have a problem if it sees setters
in your project. When I talk about setters I don't mean method name signatures,
I mean methods with a changeable object state

## Examples

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

## How to use

The plugin could be run using several approaches but for both of them you need
at least **Maven 3.8.+** and **Java 8+**.

### Add as maven plugin into your `pom.xml`

```xml
<build>
  <plugins>
    <plugin>
      <groupId>ru.l3r8y</groupId>
      <artifactId>sa-tan</artifactId>
      <version>0.1.3</version>
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

### Run plugin directly

```bash
mvn ru.l3r8y:sa-tan:search
``` 

## How to Contribute
Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:
```bash
mvn clean install -Pqulice
```
