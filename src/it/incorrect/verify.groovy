String log = new File(basedir, 'build.log').text;
[
  "Method 'HasSetters#setField' has wrong method signature, because method body contains an assignment, setters violates OOP principles"
].each { assert log.contains(it): "Log doesn't contain ['$it']" }
true