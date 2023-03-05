String log = new File(basedir, 'build.log').text;
[
].each { assert log.contains(it): ("Log doesn't contain ['$it']")}
true
