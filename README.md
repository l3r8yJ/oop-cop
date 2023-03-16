[<img src="https://raw.githubusercontent.com/l3r8yJ/sa-tan/8f6e97d0287f4f922b6cd685548490a48e26c496/s8an.svg" width="150"/>](https://www.l3r8y.ru/sa-tan/)

A plugin for Maven that will let you know you have a problem if it sees setters in your project. 
When I talk about setters I don't mean method name signatures, I mean methods with a changeable object state

### Examples
This class is valid
```java
class MyValidClass {
    private final String name;
    
    public MyValidClass(final String n) {
        this.name = n;
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

### Add to your project
```xml
<build>
  <plugins>
    <plugin>
      <groupId>ru.l3r8y</groupId>
      <artifactId>sa-tan</artifactId>
      <version>0.1.1</version>
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
