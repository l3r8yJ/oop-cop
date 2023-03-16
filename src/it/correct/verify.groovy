String log = new File(basedir, 'build.log').text;
[
  "has wrong method signature, because method body contains an assignment, setters violates OOP principles"
].each { assert !log.contains(it): "Log do contain ['$it']" }
true