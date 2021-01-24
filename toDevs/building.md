### How to build:


while I'm running the release here are some rules, we have CI system that for each commit doing following things:

* test-framework => build/test/deploy to GCP Maven ArtifactStorage
* CodingBootcamp (depends on test-framework) => build/test/deploy to GCP Maven ArtifactStorage
* Zeus (depends on test-framework and CodingBootcamp) => build/test/deploy to Google Cloud Storage with public link

Maven servers allow only one deploy of one version, so if test-framework set to version 1.5 (for example) CI will deploy it to the GCP Maven ArtifactStorage when this version is set for the first time, however each next build will fail with message "version already exist"

so version always have suffix "-SNAPSHOT", in this case Maven repository will allow deploy same version again and again
so while development is happening, version of, for example "test-framework" should be like "1.10-SNAPSHOT", as soon as release is needed next action need to be taken:

* version set to final version "1.10" and pushed
* as soon as CI do the release pom.xml need to be updated to the next SNAPSHOT verison immideatly (1.11-SNAPSHOT)

all package should depend on final versions, so CodingBootcamp should depend ONLY on 1.2 of testing-framework, and never on 1.2-SNAPSHOT
so if everything need to be updated release looks like this:

* for test-framework: update pom.xml to final version, push, wait CI, update pom.xml to next SNAPSHOT version
* for CodingBootcamp: update pom.xml with new dependancies version for test-framework, update verison to final, push, wait CI, update pom.xml to next SNAPSHOT version
* for Zeus: update pom.xml with new dependancies version for test-framework and CodingBootcamp version, push, wait CI, update pom.xml to next SNAPSHOT version

for Zeus you also need to update cloudbuild.yaml with the new version
hopefully one does not need to do release of everything each time:)
so I'm doing the release now

______________________
Этот Зевс имеет две зависимости:
- CodingBootcamp
- testing-framework

Каждую из этих зависимостей нужно собрать на GCB, что положит их в нашу maven-репу.

Если в версии нет слова `SNAPSHOT`, то такой билд может произойти только раз с одним и тем же номером версии.
Если с номером версии идет слово `SNAPSHOT`, то былд может быть произведен множество раз.
Все билды со `SNAPSHOT` доступны в maven-репе и их можно подгружать в проект Зевса для тестирования текущих изменений.