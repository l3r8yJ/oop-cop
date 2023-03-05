String log = new File(basedir, 'build.log').text;
assert !log.contains("Violations not found")
true