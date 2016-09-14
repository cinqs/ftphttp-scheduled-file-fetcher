# spring-xd-ftphttpclinet-module

Using SpringBoot to retrieve files from FTP server, scheduled, configurable, and proxy friendly

> this project is inspired by the SpringXD module `FTP`, which is using Spring Integration FTP `inbound-apapter`. But the problem for this adapter is:
>
>  - it is using Apache Commons Net FTPClient, and this FTPCllient is not compatible with HTTP Proxy
>  - It is not a standalone application
>  - It is not well done with duplicate files

> while this application:
>
>  - using FTPHTTPClient
>  - record every downloaded file with its name and last modified date
>  - using Spring Boot as deployment tool, thus is compatible with lots circumstances.

## What are used:

- Apache Commons Net FTPClient and FTPHTTPClient
- Spring Boot
- Spring ...
- Maven (if you want, add gradle as you like)

## Start

> this project added maven wrapper inside, but i haven't test it... I suggest you to install Maven locally

    mvn install
    
to list all dependencies

    mvn package
    
to get the runnable `jar` file which is in `target` folder

> whatever... I am not a maven expert...

## import into Eclipse is a better way