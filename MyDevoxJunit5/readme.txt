сначала нужно сконфигурировать gitignore, а потом комит, иначе все 
конфиг файлы попадут в git, но если так уж произошло , то удалять это 
нужно только через git например, после каждого запуска билда, генерится modified проперти файл
вот пример его удаления?

 git rm MyDevoxJunit5/myinfrastructure/target/classes/META-INF/maven/com.my/myinfrastructure/pom.properties
rm 'MyDevoxJunit5/myinfrastructure/target/classes/META-INF/maven/com.my/myinfrastructure/pom.properties'

и закомитеть его как обычно

 git commit -m 'del pom prop'
 git push
 