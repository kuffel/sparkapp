# sparkapp

[![Build Status](https://travis-ci.org/kuffel/sparkapp.svg?branch=master)](https://travis-ci.org/kuffel/sparkapp)

Spark Java micro framework based application. Spark Framework is a simple and lightweight Java web framework built for rapid development.

Spark is built around Java 8's lambda philosophy, which makes a typical Spark application a lot less verbose than most application written in other Java web frameworks.

## Getting started

This project uses the [gradle](https://gradle.org/) build tool. For a quickstart you need to follow these simple steps:

    git clone git@github.com:kuffel/sparkapp.git    
    cd sparkapp
    // To list all available tasks use
    ./gradlew tasks
    // To create a fat jar file with the complete application 
    ./gradlew shadowJar              
        
[Shadow Jar](https://github.com/johnrengelman/shadow) allows an easier distribution of java applictions. 
To start this application you can simply use this command:

    cd [PROJECT_DIR]/build/libs
    java -jar sparkapp.jar

For further instructions on using the Spark framework read the [official documentation](http://sparkjava.com/documentation.html)


## sparkapp.jar

The generated jar file provides some cli options to create and pass a configuriation file for your service instance.

    $ java -jar sparkapp.jar -h
    usage: java -jar sparkapp.jar [-c <config.json>] [-h] [-i] [-v]
     -c,--config <config.json>   Path to config.json
     -h,--help                   Show usage and help information
     -i,--init                   Create empty template config.json
     -v,--version                Display current application version

For production deployments with your own configuration just start the service with your own config.json.

To generate a new config.json run java -jar sparkapp.jar -i

If you do not provide any config this values will be uses as defaults:

    {
        "name" : "sparkapp",
        "version" : [CURRENT VERSION STRING],        
        "secret" : [RANDOM STRING],                
        "port" : 9999
    }


## References

[Spark Java Website](http://sparkjava.com/)

[Gradle](https://docs.gradle.org/current/userguide/userguide.html)

[Shadow Jar](https://github.com/johnrengelman/shadow)