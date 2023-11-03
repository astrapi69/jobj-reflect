## Change log
----------------------

Version 2.4-SNAPSHOT
-------------

ADDED:

- new dependency modjenesis in major version 1
- new method that create new instance with modjenesis
- new required version info for dependency 'org.junit.platform:junit-platform-launcher' in current version 1.10.0

CHANGED:

- update of dependency modjenesis to new minor version 1.1
- update of test dependency test-object to new minor version 8.2

Version 2.3
-------------

ADDED:

- new resolver class BeanMethodResolver for resolve method types in java beans
- new factory methods in InstanceFactory that return a new instance

CHANGED:

- update of test dependency test-object to new minor version 8.1
- rename of factory methods in InstanceFactory that return an optional

Version 2.2
-------------

ADDED:

- new factory class InstanceFactory for creating instances from class objects
- new method in class InstanceFactory that resolves an array class types of the given object array
- new test dependency silly-bean for unit testing

CHANGED:

- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.2.1
- increase code coverage
- moved all factory methods from class ReflectionExtensions to new factory class InstanceFactory
- remove of unused dependency objenesis

Version 2.1
-------------

ADDED:

- new factory method for instantiation of an object with the fully qualified name of the class

CHANGED:

- update of gradle to new version 8.4
- update of gradle plugin dependency io.freefair.gradle:lombok-plugin to new version 8.4
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.49.0
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.22.0
- update of dependency jobj-core to new minor version 8.1
- update of test dependency test-object to new major version 8
- all factory methods for instantiation of an object return now an Optional object

Version 2
-------------

CHANGED:

- update of jdk to version 17
- update of gradle to new version 8.4-rc-1
- update of lombok version to 1.18.30
- update of gradle plugin dependency io.freefair.gradle:lombok-plugin to new version 8.3
- update of gradle plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.48.0
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.21.0
- update of dependency jobj-core to new major version 8
- update of test dependency silly-collection to new major version 27
- update of test dependency junit-jupiter to new minor version 5.10.0

Version 1
-------------

ADDED:

- new CHANGELOG.md file created

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
