## Sa-tan

The current version of the project works, but it is not published in Maven Central

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

-TBD
