# Projeto Base Automação Web - Selenium, Java & Cucumber

## Ferramentas utilizadas:
- [Maven](https://maven.apache.org/ "Maven")
- [Java](https://www.java.com/pt_BR/ "Java")
- [JUnit](https://junit.org/junit4/ "JUnit")
- [Selenium](https://www.seleniumhq.org/ "Selenium")
- [ChromeDriver](https://chromedriver.chromium.org/downloads "ChromeDriver")
- [Cucumber](https://cucumber.io/ "Cucumber")
- [PageFactory](https://github.com/SeleniumHQ/selenium/wiki/PageFactory "PageFactory")
- [PageObject (pattern)](https://martinfowler.com/bliki/PageObject.html "PageObject")

## Arquitetura:

```
├── README.md
├── pom.xml
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   ├── maps            
    │   └── resources
    └── test
        ├── java
        │   ├── maps
        │   │   ├── Classes de mapeamento de elementos
        │   ├── pages
        │   │   ├── Classe page orientação a objeto
        │   ├── steps
        │   │   ├── Steps Definitions
        │   ├── runner
        │   │   └── Arquivo de execução do driver
        │   └── util
        │       ├── Classes utilitárias
        └── resources
            ├── drivers
            │   └── READ.txt
            └── features
                └── Pesquisa.feature
```
