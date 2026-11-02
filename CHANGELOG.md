## Change log
----------------------

Version 3-SNAPSHOT
-------------

BREAKING:

- Java 25 is the minimum, up from 17: the family builds, tests and releases on JDK 25
  (astrapi69/mystic-crypt#234) (#12)
- the module requires `io.github.astrapisixtynine.jobj.core`, the module name jobj-core uses since 9;
  2.4 required `jobj.core.main`, the name of jobj-core 8, so on the module path this version needs
  jobj-core 9 or later (astrapi69/swing-table-components#14)

ADDED:

- new libs.versions.toml file for new automatic catalog versions update
- new method in class ReflectionExtensions for invoke a method from an object

CHANGED:

- built with Gradle 9.8.2; the grgit and hierynomus license plugins are gone: a release is tagged
  through git (`gradle/tagging.gradle`), and Spotless writes the license header from
  `src/main/resources/license-header.txt` (#12)
- `versionCatalogUpdate` chooses versions through a selector of its own: a stable version moves only to
  a stable one, and nothing to a snapshot; meanbean stays at 3.0.0-M9, its latest, a milestone with no
  stable 3.x release (#12)
- dependencies raised by `versionCatalogUpdate`: jobj-core 9 -> 9.1, lombok 1.18.34 -> 1.18.48, and for
  the tests junit-jupiter 5.11.0 -> 6.1.3, junit-platform-launcher 1.11.0 -> 6.1.3, silly-bean 2 -> 3.0,
  silly-collection 28 -> 28.2, test-object 9 -> 9.1; the plugins spotless 7.0.0.BETA2 -> 8.10.4, lombok
  8.10 -> 9.8.3, versions 0.51.0 -> 0.67.0 under its new id `io.github.ben-manes.versions`, and
  version-catalog-update 0.8.4 -> 1.1.2 (#12)
- releases go to Maven Central through the Central Portal, as in the rest of the family: the
  `com.gradleup.nmcp` plugin uploads, the maintainer releases each deployment in the Portal, and a
  `RELEASE-*` tag runs the new `publish.yml`, which signs with the signing subkey that the secret
  `GPG_KEY_ID` names. An upload leaves only from CI, a release only from its tag. The OSSRH staging
  endpoint this project published to is shut down; its settings are gone, among them the switch that
  held the checksums to md5 and sha1, and snapshots resolve from the Central Portal's snapshot
  repository (#11)
- rename of module to new name 'io.github.astrapisixtynine.jobj.reflect'
- update gradle to new version 8.10.1
- update of dependency lombok to new version 1.18.34
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new minor version 7.0.0.BETA2
- update of dependency jobj-core to new major version 9
- update of test dependency test-object to new major version 9
- update of test dependency silly-collection to new major version 9
- update of test dependency junit-jupiter to new version 5.11.0

Version 2.4
-------------

ADDED:

- new dependency modjenesis in minor version 1.2
- new method that create new instance with modjenesis
- new required version info for dependency 'org.junit.platform:junit-platform-launcher' in current version 1.10.0

CHANGED:

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
